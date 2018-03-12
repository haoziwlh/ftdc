package com.ee.ctp.handler;

import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ErrRtnOrderAction;
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

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:35:49
 *
 */
public interface FtdcTraderSpi {
	AttributeKey<FtdcTraderSpi> TRADER_API = AttributeKey.newInstance("TRADER_API");
	AttributeKey<UserSession> USER_SESSION = AttributeKey.newInstance("USER_SESSION");
	AttributeKey<ReqUserLogin> USER_LOGIN = AttributeKey.newInstance("USER_LOGIN");
	/**
	 * 注册channel
	 * @param channel
	 */
	void reqister(Channel channel);
	/**
	 * 用户登录应答
	 * @param rspUserLogin
	 * @param reqId
	 * @param isLast
	 */
	void onRspUserLogin(RspUserLogin rspUserLogin, int reqId, boolean isLast);
	/**
	 * 无数据
	 * @param reqId
	 */
	void onNodata(int reqId);
	/**
	 * 错误应答
	 * @param error
	 * @param reqId
	 */
	void onRspError(RspError error, int reqId);
	/**
	 * 源于请求端的错误
	 * @param error
	 * @param ri
	 */
	void onRspError(RspError error, RequestIdentity ri);
	/**
	 * 登出应答
	 * @param lout
	 * @param reqId
	 * @param isLast
	 */
	void onRspUserLogout(RspUserLogout lout, int reqId, boolean isLast);
	/**
	 * 结算信息应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspSettlementInfo(RspSettlementInfo info, int reqId, boolean isLast);
	/**
	 * 结算单确认应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspSettlementInfoConfirm(RspSettlementInfoConfirm info, int reqId, boolean isLast);
	/**
	 * 结算单确认查询应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspQrySettlementInfoConfirm(RspQrySettlementInfoConfirm info, int reqId, boolean isLast);
	/**
	 * 账户资金应答
	 * @param tradingAccount
	 * @param reqId
	 * @param isLast
	 */
	void onRspTradingAccount(RspTradingAccount tradingAccount, int reqId, boolean isLast);
	/**
	 * 报单应答
	 * @param inputOrder
	 * @param reqId
	 * @param isLast
	 */
	void onRspOrderInput(RspInputOrder inputOrder, int reqId, boolean isLast);
	/**
	 * 报单回报
	 * @param rtnOrder
	 * @param reqId
	 * @param isLast
	 */
	void onRtnOrder(RtnOrder rtnOrder, int reqId, boolean isLast);
	/**
	 * 成交回报
	 * @param rtnTrade
	 * @param reqId
	 * @param isLast
	 */
	void onRtnTrade(RtnTrade rtnTrade, int reqId, boolean isLast);
	/**
	 * 收到未识别的TID
	 * @param tid
	 * @param reqId
	 */
	void onRecieveUnUsedTid(String tid, int reqId);
	/**
	 * 持仓查询应答
	 * @param investorPisition
	 * @param reqId
	 * @param isLast
	 */
	void onRspInvestorPosition(RspInvestorPosition investorPisition, int reqId, boolean isLast);
	/**
	 * 保证金比例查询应答
	 * @param qryMarginRate
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryMarginRate(RspQryMarginRate qryMarginRate, int reqId, boolean isLast);
	/**
	 * 手续费查询应答
	 * @param qryCommissionRate
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryCommissionRate(RspQryCommissionRate qryCommissionRate, int reqId, boolean isLast);
	/**
	 * 报单操作错误应答
	 * @param errRtnOrderAction
	 * @param reqId
	 * @param isLast
	 */
	void onErrRtnOrderAction(ErrRtnOrderAction errRtnOrderAction, int reqId, boolean isLast);
	/**
	 * 签约关系查询应答
	 * @param accountRegister
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryAccountRegister(RspAccountRegister accountRegister, int reqId, boolean isLast);
	/**
	 * 签约银行查询应答
	 * @param contractBank
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryContractBank(RspContractBank contractBank, int reqId, boolean isLast);
	/**
	 * 报单操作应答
	 * @param rspOrderAction
	 * @param reqId
	 * @param isLast
	 */
	void onRspOrderAction(RspOrderAction rspOrderAction, int reqId, boolean isLast);
	/**
	 * 转账流水查询应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryTransferSerial(RspQryTransferSerial info, int reqId, boolean isLast);
	/**
	 * 合约查询应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryInstrument(RspQryInstrument info, int reqId, boolean isLast);
	/**
	 * 期货转银行应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspFutureOrBank(RspFutureOrBank info, int reqId, boolean isLast);
	/**
	 * 银转期 或者 期转银回报
	 * @param rtnBankOrFuture
	 * @param reqId
	 * @param isLast
	 */
	void onRtnBankOrFuture(RtnBankOrFuture rtnBankOrFuture, int reqId, boolean isLast);
	/**
	 * 交易密码修改应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspUserPasswordUpdate(RspUserPasswordUpdate info, int reqId, boolean isLast);
	/**
	 * 收到未识别的Sequence
	 * @param reqId
	 * @param tid
	 * @param sequence
	 */
	void unRecieveUnusedSequence(int reqId, int tid, int sequence);
	/**
	 * 报单查询应答
	 * @param order
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryOrder(RtnOrder order, int reqId, boolean isLast);
	/**
	 * 成交查询应答
	 * @param trade
	 * @param reqId
	 * @param isLast
	 */
	void onRspQryTrade(RtnTrade trade, int reqId, boolean isLast);
	/**
	 * 资金密码修改应答
	 * @param info
	 * @param reqId
	 * @param isLast
	 */
	void onRspFundPasswordUpdate(RspFundPasswordUpdate info, int reqId, boolean isLast);
	/**
	 * 客户认证挑战响应
	 * @param rspAuth
	 * @param reqId
	 * @param isLast
	 */
	void onRspUserAuthFirst(RspAuth rspAuth, int reqId, boolean isLast);
	/**
	 * 客户认证挑战结果响应
	 * @param rspAuth
	 * @param reqId
	 * @param isLast
	 */
	void onRspUserAuthSecond(RspAuth rspAuth, int reqId, boolean isLast);
}
