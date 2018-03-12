package com.ee.ctp.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ErrRtnOrderAction;
import com.ee.ctp.dto.ReqAuth;
import com.ee.ctp.dto.ReqUserLogin;
import com.ee.ctp.dto.RspAccountRegister;
import com.ee.ctp.dto.RspAuth;
import com.ee.ctp.dto.RspContractBank;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspFundPasswordUpdate;
import com.ee.ctp.dto.RspFutureOrBank;
import com.ee.ctp.dto.RspInputOrder;
import com.ee.ctp.dto.RspInvestorPosition;
import com.ee.ctp.dto.RspOrderAction;
import com.ee.ctp.dto.RspQryCommissionRate;
import com.ee.ctp.dto.RspQryInstrument;
import com.ee.ctp.dto.RspQryMarginRate;
import com.ee.ctp.dto.RspQrySettlementInfoConfirm;
import com.ee.ctp.dto.RspQryTransferSerial;
import com.ee.ctp.dto.RspSettlementInfo;
import com.ee.ctp.dto.RspSettlementInfoConfirm;
import com.ee.ctp.dto.RspTradingAccount;
import com.ee.ctp.dto.RspUserLogin;
import com.ee.ctp.dto.RspUserLogout;
import com.ee.ctp.dto.RspUserPasswordUpdate;
import com.ee.ctp.dto.RtnBankOrFuture;
import com.ee.ctp.dto.RtnOrder;
import com.ee.ctp.dto.RtnTrade;
import com.ee.ctp.dto.UserSession;
import com.ee.ctp.enums.CTPErrorCode;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
import com.ee.ctp.ftdc.FtdcProtocol;
import com.ee.ctp.handler.task.RecieveMessageTimerTask;
import com.google.common.base.Verify;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * 
 * @author ee 2017年10月17日 下午8:43:00
 *
 */
public abstract class BaseFtdcTraderSpiAdapter implements FtdcTraderSpi {
	private static final Logger logger = LoggerFactory.getLogger(PrintResultHandler.class);
	private static final String LOGGR_TEMPLATE_ERROR = "reqId {}: session validate";
	private static final String VERIFY_TEMPLATE = "RequestIdentity %s  of reqid:%s,brokerid:%s,userid:%s could not find";
	private Channel channel;

	public Channel userFtdcChannel() {
		return this.channel;
	}

	@Override
	public void reqister(Channel channel) {
		this.channel = channel;
		channel.attr(FtdcTraderSpi.TRADER_API).set(this);
	}

	protected UserSession session() {
		UserSession session = null;
		Attribute<UserSession> attr = this.userFtdcChannel().attr(FtdcTraderSpi.USER_SESSION);
		if (attr != null) {
			session = attr.get();
		}
		return session;
	}

	@Override
	public void onRspUserLogin(RspUserLogin rspUserLogin, int reqId, boolean isLast) {
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, rspUserLogin.getBrokerID(),
				rspUserLogin.getUserID());
		// 认证成功，绑定用户与channel
		CTPErrorCode ctpErrorCode = CTPErrorCode.parseFrom(rspUserLogin.getErrorCode());
		if (CTPErrorCode.isChannelAuthPassed(ctpErrorCode)) {
			final Channel oldFtdcChannel = ApplicationRuntime.putFtdcChannel(rspUserLogin.getBrokerID(),
					rspUserLogin.getUserID(), this.userFtdcChannel());
			if (oldFtdcChannel != null) {
				delayCloseFtdcChannel(oldFtdcChannel);
			}
			//绑定session
			UserSession session = new UserSession(rspUserLogin);
			this.userFtdcChannel().attr(FtdcTraderSpi.USER_SESSION).set(session);
			doRspUserLogin(rspUserLogin, requestIdentity, true);
		} else {
			delayCloseFtdcChannel(this.userFtdcChannel());
			doRspUserLogin(rspUserLogin, requestIdentity, false);
		}
		
	}

	private void delayCloseFtdcChannel(final Channel oldFtdcChannel) {
		ApplicationRuntime.newTimeout(new TimerTask() {

			@Override
			public void run(Timeout timeout) throws Exception {
				oldFtdcChannel.close();
			}
		}, 1);
	}

	/**
	 * 登录应答
	 * @param rspUserLogin
	 * @param ri
	 * @param authPassed
	 */
	protected abstract void doRspUserLogin(RspUserLogin rspUserLogin, RequestIdentity ri, boolean authPassed);

	@Override
	public void onRspUserAuthFirst(RspAuth rspAuth, int reqId, boolean isLast) {
		// 收到挑战
		RequestIdentity requestIdentity = getRequestIdentity(reqId, false, rspAuth.getBrokerID(), rspAuth.getUserID());
		ByteBuf buffer = this.userFtdcChannel().alloc().buffer();
		ReqAuth reqAuth = ReqAuth.buildSecondFrom(rspAuth, requestIdentity.getAuthCode());
		reqAuth.writeSecond(buffer.retain());
		this.userFtdcChannel().writeAndFlush(new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), reqId,
				TID.Auth.id(), Sequence.Auth));
	}

	@Override
	public void onRspUserAuthSecond(RspAuth rspAuth, int reqId, boolean isLast) {
		int errorCode = rspAuth.getErrorCode();
		if(errorCode == 0) {
			RequestIdentity requestIdentity = getRequestIdentity(reqId, false, rspAuth.getBrokerID(), rspAuth.getUserID());
			ReqUserLogin reqUserLogin = this.userFtdcChannel().attr(FtdcTraderSpi.USER_LOGIN).get();
			ByteBuf buffer = this.userFtdcChannel().alloc().buffer();
			reqUserLogin.write(buffer.retain());
			this.userFtdcChannel().writeAndFlush(new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), reqId,
					TID.UserLoginReq.id(), Sequence.UserLogin));
			ApplicationRuntime.newTimeout(new RecieveMessageTimerTask(requestIdentity, this), ApplicationRuntime.conf().getMessageTimeout());
		}else {
			RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, rspAuth.getBrokerID(), rspAuth.getUserID());
			doRspUserAuth(requestIdentity, rspAuth, true);
		}
	}
	
	/**
	 * 客户端应答
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspUserAuth(RequestIdentity requestIdentity, RspAuth info, boolean isLast);

	@Override
	public void onNodata(int reqId) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR);
			return;
		}
		RequestIdentity requestIdentity = ApplicationRuntime.unbindRequestIdentity(session.getBrokerId(),
				session.getUserID(), reqId);
		// 预防有多个错误应答
		if (requestIdentity != null) {
			doNodata(requestIdentity);
		}
	}

	/**
	 * 无数据应答
	 * 
	 * @param requestIdentity
	 */
	protected abstract void doNodata(RequestIdentity requestIdentity);

	@Override
	public void onRspUserLogout(RspUserLogout lout, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		// 登出成功，注销用户与channel
		if (lout.getErrorCode() == 0) {
			ApplicationRuntime.removeFtdcChannel(lout.getBrokerID(), lout.getUserID());
			this.userFtdcChannel().attr(FtdcTraderSpi.USER_SESSION).set(null);
		}
		doRspUserLogout(requestIdentity, lout);
	}

	/**
	 * 登出应答
	 * 
	 * @param requestIdentity
	 * @param lout
	 */
	protected abstract void doRspUserLogout(RequestIdentity requestIdentity, RspUserLogout lout);

	@Override
	public void onRspSettlementInfo(RspSettlementInfo info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspSettlementInfo(requestIdentity, info, isLast);
	}

	/**
	 * 结算单信息查询应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspSettlementInfo(RequestIdentity requestIdentity, RspSettlementInfo info,
			boolean isLast);

	@Override
	public void onRspSettlementInfoConfirm(RspSettlementInfoConfirm info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspSettlementInfoConfirm(requestIdentity, info, isLast);
	}

	/**
	 * 结算单确认应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspSettlementInfoConfirm(RequestIdentity requestIdentity, RspSettlementInfoConfirm info,
			boolean isLast);

	@Override
	public void onRspQrySettlementInfoConfirm(RspQrySettlementInfoConfirm info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQrySettlementInfoConfirm(requestIdentity, info, isLast);
	}

	/**
	 * 结算单确认查询应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspQrySettlementInfoConfirm(RequestIdentity requestIdentity,
			RspQrySettlementInfoConfirm info, boolean isLast);

	@Override
	public void onRspTradingAccount(RspTradingAccount tradingAccount, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspTradingAccount(requestIdentity, tradingAccount, isLast);
	}

	/**
	 * 资金账户查询应答
	 * 
	 * @param requestIdentity
	 * @param tradingAccount
	 * @param isLast
	 */
	protected abstract void doRspTradingAccount(RequestIdentity requestIdentity, RspTradingAccount tradingAccount,
			boolean isLast);

	@Override
	public void onRspOrderInput(RspInputOrder inputOrder, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspOrderInput(requestIdentity, inputOrder, isLast);
	}

	/**
	 * 报单应答
	 * 
	 * @param requestIdentity
	 * @param inputOrder
	 * @param isLast
	 */
	protected abstract void doRspOrderInput(RequestIdentity requestIdentity, RspInputOrder inputOrder, boolean isLast);

	@Override
	public void onRtnOrder(RtnOrder rtnOrder, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRtnRequestIdentity(session);
		doRtnOrder(requestIdentity, rtnOrder, isLast);
	}

	private RequestIdentity getRtnRequestIdentity(UserSession session) {
		Channel rtnChannel = ApplicationRuntime.rtnChannel(session.getBrokerId(), session.getUserID());
		RequestIdentity requestIdentity = new RequestIdentity();
		requestIdentity.setBrokerId(session.getBrokerId());
		requestIdentity.setClientChannel(rtnChannel);
		requestIdentity.setUserId(session.getUserID());
		return requestIdentity;
	}

	/**
	 * 报单回报
	 * 
	 * @param requestIdentity
	 * @param rtnOrder
	 * @param isLast
	 */
	protected abstract void doRtnOrder(RequestIdentity requestIdentity, RtnOrder rtnOrder, boolean isLast);

	@Override
	public void onRtnTrade(RtnTrade rtnTrade, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRtnRequestIdentity(session);
		doRtnTrade(requestIdentity, rtnTrade, isLast);
	}

	/**
	 * 成交回报
	 * 
	 * @param requestIdentity
	 * @param rtnTrade
	 * @param isLast
	 */
	protected abstract void doRtnTrade(RequestIdentity requestIdentity, RtnTrade rtnTrade, boolean isLast);

	@Override
	public void onRecieveUnUsedTid(String tid, int reqId) {
		logger.debug("reveive unknown tid: {} of reqId {}", tid, reqId);
	}

	@Override
	public void onRspInvestorPosition(RspInvestorPosition investorPisition, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspInvestorPosition(requestIdentity, investorPisition, isLast);
	}

	/**
	 * 持仓查询应答
	 * 
	 * @param requestIdentity
	 * @param investorPisition
	 * @param isLast
	 */
	protected abstract void doRspInvestorPosition(RequestIdentity requestIdentity, RspInvestorPosition investorPisition,
			boolean isLast);

	@Override
	public void onRspError(RspError error, int reqId) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = ApplicationRuntime.unbindRequestIdentity(session.getBrokerId(),
				session.getUserID(), reqId);
		Verify.verifyNotNull(requestIdentity, VERIFY_TEMPLATE, requestIdentity);
		doRspError(requestIdentity, error);
	}

	/**
	 * 错误应答
	 * 
	 * @param requestIdentity
	 * @param error
	 */
	protected abstract void doRspError(RequestIdentity requestIdentity, RspError error);

	@Override
	public void onRspError(RspError error, RequestIdentity ri) {
		doRspError(ri, error);
	}

	@Override
	public void onRspQryMarginRate(RspQryMarginRate qryMarginRate, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryMarginRate(requestIdentity, qryMarginRate, isLast);
	}

	/**
	 * 保证金比例查询应答
	 * 
	 * @param requestIdentity
	 * @param qryMarginRate
	 * @param isLast
	 */
	protected abstract void doRspQryMarginRate(RequestIdentity requestIdentity, RspQryMarginRate qryMarginRate,
			boolean isLast);

	@Override
	public void onRspQryCommissionRate(RspQryCommissionRate qryCommissionRate, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryCommissionRate(requestIdentity, qryCommissionRate, isLast);
	}

	/**
	 * 手续费查询应答
	 * 
	 * @param requestIdentity
	 * @param qryCommissionRate
	 * @param isLast
	 */
	protected abstract void doRspQryCommissionRate(RequestIdentity requestIdentity,
			RspQryCommissionRate qryCommissionRate, boolean isLast);

	@Override
	public void onErrRtnOrderAction(ErrRtnOrderAction errRtnOrderAction, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRtnRequestIdentity(session);
		doErrRtnOrderAction(requestIdentity, errRtnOrderAction, isLast);
	}

	/**
	 * 报单操作错误回报
	 * 
	 * @param requestIdentity
	 * @param errRtnOrderAction
	 * @param isLast
	 */
	protected abstract void doErrRtnOrderAction(RequestIdentity requestIdentity, ErrRtnOrderAction errRtnOrderAction,
			boolean isLast);

	@Override
	public void onRspQryAccountRegister(RspAccountRegister accountRegister, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryAccountRegister(requestIdentity, accountRegister, isLast);
	}

	/**
	 * 签约关系查询应答
	 * 
	 * @param requestIdentity
	 * @param accountRegister
	 * @param isLast
	 */
	protected abstract void doRspQryAccountRegister(RequestIdentity requestIdentity, RspAccountRegister accountRegister,
			boolean isLast);

	@Override
	public void onRspQryContractBank(RspContractBank contractBank, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryContractBank(requestIdentity, contractBank, isLast);
	}

	/**
	 * 签约银行查询应答
	 * 
	 * @param requestIdentity
	 * @param contractBank
	 * @param isLast
	 */
	protected abstract void doRspQryContractBank(RequestIdentity requestIdentity, RspContractBank contractBank,
			boolean isLast);

	@Override
	public void onRspOrderAction(RspOrderAction rspOrderAction, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspOrderAction(requestIdentity, rspOrderAction, isLast);
	}

	/**
	 * 报单操作应答
	 * 
	 * @param requestIdentity
	 * @param rspOrderAction
	 * @param isLast
	 */
	protected abstract void doRspOrderAction(RequestIdentity requestIdentity, RspOrderAction rspOrderAction,
			boolean isLast);

	@Override
	public void onRspQryTransferSerial(RspQryTransferSerial info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryTransferSerial(requestIdentity, info, isLast);
	}

	/**
	 * 转账流水查询应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspQryTransferSerial(RequestIdentity requestIdentity, RspQryTransferSerial info,
			boolean isLast);

	@Override
	public void onRspQryInstrument(RspQryInstrument info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryInstrument(requestIdentity, info, isLast);
	}

	/**
	 * 合约查询应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspQryInstrument(RequestIdentity requestIdentity, RspQryInstrument info, boolean isLast);

	@Override
	public void onRspFutureOrBank(RspFutureOrBank info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspFutureOrBank(requestIdentity, info, isLast);
	}

	/**
	 * 期转银应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspFutureOrBank(RequestIdentity requestIdentity, RspFutureOrBank info,
			boolean isLast);

	@Override
	public void unRecieveUnusedSequence(int reqId, int tid, int sequence) {
		logger.debug("recieve unknown tid:sequence {}:{} of reqid {}", tid, sequence, reqId);
	}

	@Override
	public void onRtnBankOrFuture(RtnBankOrFuture rtnBankOrFuture, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRtnRequestIdentity(session);
		doRtnBankOrFuture(requestIdentity, rtnBankOrFuture, isLast);
	}

	/**
	 * 银转期或者期转银 回报
	 * 
	 * @param requestIdentity
	 * @param rtnBankOrFuture
	 * @param isLast
	 */
	protected abstract void doRtnBankOrFuture(RequestIdentity requestIdentity, RtnBankOrFuture rtnBankOrFuture,
			boolean isLast);

	@Override
	public void onRspUserPasswordUpdate(RspUserPasswordUpdate info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspUserPasswordUpdate(requestIdentity, info, isLast);
	}

	private RequestIdentity getRequestIdentity(int reqId, boolean isLast, UserSession session) {
		return getRequestIdentity(reqId, isLast, session.getBrokerId(), session.getUserID());
	}

	private RequestIdentity getRequestIdentity(int reqId, boolean isLast, String brokerId, String userid) {
		RequestIdentity requestIdentity;
		if (isLast) {
			requestIdentity = ApplicationRuntime.unbindRequestIdentity(brokerId, userid, reqId);
		} else {
			requestIdentity = ApplicationRuntime.getRequestIdentity(brokerId, userid, reqId);
		}
		Verify.verifyNotNull(requestIdentity, VERIFY_TEMPLATE, requestIdentity, reqId, brokerId, userid);
		return requestIdentity;
	}

	/**
	 * 交易密码修改应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspUserPasswordUpdate(RequestIdentity requestIdentity, RspUserPasswordUpdate info,
			boolean isLast);

	@Override
	public void onRspQryOrder(RtnOrder order, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryOrder(requestIdentity, order, isLast);
	}

	/**
	 * 报单查询应答
	 * 
	 * @param requestIdentity
	 * @param order
	 * @param isLast
	 */
	protected abstract void doRspQryOrder(RequestIdentity requestIdentity, RtnOrder order, boolean isLast);

	@Override
	public void onRspQryTrade(RtnTrade trade, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspQryTrade(requestIdentity, trade, isLast);
	}

	/**
	 * 成交查询应答
	 * 
	 * @param requestIdentity
	 * @param trade
	 * @param isLast
	 */
	protected abstract void doRspQryTrade(RequestIdentity requestIdentity, RtnTrade trade, boolean isLast);

	@Override
	public void onRspFundPasswordUpdate(RspFundPasswordUpdate info, int reqId, boolean isLast) {
		UserSession session = session();
		if (session == null) {
			logger.error(LOGGR_TEMPLATE_ERROR, reqId);
			return;
		}
		RequestIdentity requestIdentity = getRequestIdentity(reqId, isLast, session);
		doRspFundPwdUpdtae(requestIdentity, info, isLast);
	}

	/**
	 * 资金密码修改应答
	 * 
	 * @param requestIdentity
	 * @param info
	 * @param isLast
	 */
	protected abstract void doRspFundPwdUpdtae(RequestIdentity requestIdentity, RspFundPasswordUpdate info,
			boolean isLast);

}
