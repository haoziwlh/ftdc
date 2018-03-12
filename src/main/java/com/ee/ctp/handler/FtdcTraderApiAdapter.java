package com.ee.ctp.handler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.FtdcReq;
import com.ee.ctp.dto.ReqAuth;
import com.ee.ctp.dto.ReqInputOrder;
import com.ee.ctp.dto.ReqUserLogin;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.UserSession;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
import com.ee.ctp.ftdc.FtdcProtocol;
import com.ee.ctp.handler.task.RecieveMessageTimerTask;
import com.ee.ctp.pool.FtdClientPool;
import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;
import com.google.common.base.Verify;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:35:42
 *
 */
public class FtdcTraderApiAdapter implements FtdcTraderApi {

	private static final Logger logger = LoggerFactory.getLogger(FtdcTraderApiAdapter.class);

	private List<ConnectAddrProperty> sas;
	private static final ConcurrentHashMap<String, FtdcTraderSpi> SPIS = new ConcurrentHashMap<>();
	private static final String MAP_SPLIT = ":";
	
	@Override
	public void registerFront(List<ConnectAddrProperty> sas) {
		this.sas = sas;
	}
	
	public List<ConnectAddrProperty> getSas() {
		return sas;
	}
	
	@Override
	public void reqUserLogin(RequestIdentity requestIdentity, FtdcReq userLogin) {
		Verify.verifyNotNull(sas, "pls register front first");
		FtdClientPool pool = FtdClientPool.getPool();
		pool.acquire(sas).addListener(new LoginListener(requestIdentity, TID.UserLoginReq, Sequence.UserLogin, userLogin));
	}

	@Override
	public void reqUserLogout(RequestIdentity requestIdentity, FtdcReq userLogout) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			userLogout.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.UserLogoutReq.id(), Sequence.UserLogout);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}
	}

	private void getRequestSpi(RequestIdentity requestIdentity) {
		FtdcTraderSpi traderSpi = this.spi(requestIdentity);
		RspError connectLostError = RspError.buildConnectionLostError();
		traderSpi.onRspError(connectLostError, requestIdentity);
	}
	
	private FtdcTraderSpi getSpi(Channel ftdcChannel) {
		FtdcTraderSpi ftdcTraderSpi = null;
		Verify.verifyNotNull(ftdcChannel, "ftdcChannel is null");
		if(ftdcChannel.hasAttr(FtdcTraderSpi.TRADER_API)) {
			Attribute<FtdcTraderSpi> attr = ftdcChannel.attr(FtdcTraderSpi.TRADER_API);
			ftdcTraderSpi =  attr.get();
		}
		Verify.verifyNotNull(ftdcTraderSpi, "FtdcTraderSpi not register, pls register it first");
		return ftdcTraderSpi;
	}

	private void fireRspError(FtdcTraderSpi ftdcTraderSpi, RequestIdentity requestIdentity) {
		RspError connectLostError = RspError.buildConnectionLostError();
		ftdcTraderSpi.onRspError(connectLostError, requestIdentity.getReqId());
	}

	@Override
	public void reqSettlementInfo(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.SettlementInfoReq.id(), Sequence.SettlementInfo);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}
	}

	@Override
	public void reqSettlementInfoConfirm(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.SettlementInfoConfirmReq.id(), Sequence.SettlementInfoConfirm);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}
	}

	@Override
	public void reqQrySettlementInfoConfirm(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QrySettlementInfoConfirmReq.id(), Sequence.QrySettlementInfoConfirm);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}	
	}

	@Override
	public void reqTradingAccount(RequestIdentity requestIdentity, FtdcReq tradingAccount) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			tradingAccount.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.TradingAccountReq.id(), Sequence.TradingAccount);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqOrderInput(RequestIdentity requestIdentity, FtdcReq inputOrder) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			logger.info("{} send to ctp with error of ftdc channel is null", requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			UserSession session = session(ftdcChannel);
			if(session != null) {
				((ReqInputOrder)inputOrder).setOrderRef(String.valueOf(session.orderRef()));
			}
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			inputOrder.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.OrderInsertReq.id(), Sequence.OrderInsert);
			ftdcChannel.writeAndFlush(ftdc);
			logger.info("{} send to ctp", requestIdentity);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
			logger.warn("{} send to ctp with error of channel not active", requestIdentity);
		}		
	}
	
	protected UserSession session(Channel ftdcChannel) {
		UserSession session = null;
		Attribute<UserSession> attr = ftdcChannel.attr(FtdcTraderSpi.USER_SESSION);
		if (attr != null) {
			session = attr.get();
		}
		return session;
	}

	@Override
	public void reqInvestorPosition(RequestIdentity requestIdentity, FtdcReq investorPisition) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			investorPisition.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.InvestorPositionReq.id(), Sequence.InvestorPosition);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryMarginRate(RequestIdentity requestIdentity, FtdcReq qryMarginRate) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			qryMarginRate.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryMarginRateReq.id(), Sequence.QryMarginRate);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}	
	}

	@Override
	public void reqQryCommissionRate(RequestIdentity requestIdentity, FtdcReq qryCommissionRate) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			qryCommissionRate.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryCommissionRateReq.id(), Sequence.QryCommissionRate);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryAccountRegister(RequestIdentity requestIdentity, FtdcReq accountRegister) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			accountRegister.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.AccountRegisterReq.id(), Sequence.AccountRegister);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryContractBank(RequestIdentity requestIdentity, FtdcReq contractBank) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			contractBank.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.ContractbankReq.id(), Sequence.ContractBank);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqOrderAction(RequestIdentity requestIdentity, FtdcReq reqOrderAction) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			reqOrderAction.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.OrderActionReq.id(), Sequence.OrderAction);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryTransferSerial(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryTransferSerialReq.id(), Sequence.QryTransferSerial);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryInstrument(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryInstrumentReq.id(), Sequence.QryInstrument);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}	
	}

	@Override
	public void reqFromBankToFuture(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.FromBankToFutureReq.id(), Sequence.FromBankToFuture);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqFromFutureToBank(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.FromFutureToBankReq.id(), Sequence.FromFutureToBank);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqUserPasswordUpdate(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.UserPasswordUpdateReq.id(), Sequence.UserPasswordUpdate);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}		
	}

	@Override
	public void reqQryOrder(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryOrderReq.id(), Sequence.QryOrder);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}				
	}

	@Override
	public void reqQryTrade(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.QryTradeReq.id(), Sequence.QryTrade);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}
	}

	@Override
	public void registerSpi(RequestIdentity requestIdentity, FtdcTraderSpi spi) {
		String key = requestIdentity.getBrokerId() + MAP_SPLIT + requestIdentity.getUserId();
		SPIS.putIfAbsent(key, spi);
	}
	
	public FtdcTraderSpi spi(RequestIdentity requestIdentity) {
		String key = requestIdentity.getBrokerId() + MAP_SPLIT + requestIdentity.getUserId();
		return SPIS.get(key);
	}

	@Override
	public void reqFundPasswordUpdate(RequestIdentity requestIdentity, FtdcReq info) {
		ApplicationRuntime.bindRequestIdentiity(requestIdentity);
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(requestIdentity.getBrokerId(), requestIdentity.getUserId());
		if(ftdcChannel == null) {
			getRequestSpi(requestIdentity);
			return;
		}
		FtdcTraderSpi ftdcTraderSpi = getSpi(ftdcChannel);
		if(ftdcChannel.isActive()) {
			ByteBuf buffer = ftdcChannel.alloc().buffer();
			info.write(buffer.retain());
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(), TID.FundPasswordUpdateReq.id(), Sequence.FundPassworeUpdate);
			ftdcChannel.writeAndFlush(ftdc);
		}else {
			fireRspError(ftdcTraderSpi, requestIdentity);
		}
	}

	@Override
	public void reqAuthAndLogin(RequestIdentity requestIdentity, FtdcReq userLogin) {
		Verify.verifyNotNull(sas, "pls register front first");
		FtdClientPool pool = FtdClientPool.getPool();
		pool.acquire(sas).addListener(new AuthListener(requestIdentity, TID.Auth, Sequence.Auth, userLogin));
	}
	
	abstract class AbstractFtdcFutureListener implements GenericFutureListener<Future<Channel>> {
		
		protected FtdcTraderSpi spi;
		protected RequestIdentity requestIdentity;
		private int retry = 3;
		protected TID tid;
		protected Sequence sequence;

		public AbstractFtdcFutureListener(RequestIdentity requestIdentity, TID tid, Sequence sequence) {
			this.spi = spi(requestIdentity);
			this.requestIdentity = requestIdentity;
			this.tid = tid;
			this.sequence = sequence;
		}

		@Override
		public void operationComplete(Future<Channel> future) throws Exception {
			ApplicationRuntime.bindRequestIdentiity(requestIdentity);
			if (future.isSuccess()) {
				Channel channel = future.getNow();
				ByteBuf buffer = sendMessage(channel);
				spi.reqister(channel);
				channel.writeAndFlush(new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), requestIdentity.getReqId(),
						tid.id(), sequence));
				startTask(new RecieveMessageTimerTask(requestIdentity, spi));
			} else {
				logger.error(ExceptionUtils.getStackTrace(future.cause()));
				if(this.retry-- > 0) {
					FtdClientPool pool = FtdClientPool.getPool();
					pool.acquire(FtdcTraderApiAdapter.this.getSas()).addListener(this);
				}else {
					RspError connectError = RspError.buildConnectError();
					((BaseFtdcTraderSpiAdapter)spi).doRspError(requestIdentity, connectError);
				}
			}
		}
		
		/**
		 * 发送消息
		 * @param channel
		 * @return
		 */
		protected abstract ByteBuf sendMessage(Channel channel);
		
		/**
		 * 启动任务
		 * @param task
		 */
		protected abstract void startTask(RecieveMessageTimerTask task);
	}
	
	class AuthListener extends AbstractFtdcFutureListener {
		private ReqAuth reqAuth;
		private ReqUserLogin reqUserLogin;
		public AuthListener(RequestIdentity requestIdentity, TID tid,
				Sequence sequence, FtdcReq reqUserLogin) {
			super(requestIdentity, tid, sequence);
			this.reqUserLogin = (ReqUserLogin)reqUserLogin;
			this.reqAuth = this.reqUserLogin.parseReqAuth(requestIdentity);
		}
		@Override
		protected ByteBuf sendMessage(Channel channel) {
			channel.attr(FtdcTraderSpi.USER_LOGIN).set(reqUserLogin);
			ByteBuf buffer = channel.alloc().buffer();
			reqAuth.writeFirst(buffer);
			return buffer;
		}
		@Override
		protected void startTask(RecieveMessageTimerTask task) {
			//nop
		}
	}
	
	class LoginListener extends AbstractFtdcFutureListener {
		protected FtdcReq reqUserLogin;
		public LoginListener(RequestIdentity requestIdentity, TID tid,
				Sequence sequence, FtdcReq reqUserLogin) {
			super(requestIdentity, tid, sequence);
			this.reqUserLogin = reqUserLogin;
		}
		@Override
		protected ByteBuf sendMessage(Channel channel) {
			ByteBuf buffer = channel.alloc().buffer();
			reqUserLogin.write(buffer.retain());
			return buffer;
		}
		@Override
		protected void startTask(RecieveMessageTimerTask task) {
			ApplicationRuntime.newTimeout(task, ApplicationRuntime.conf().getMessageTimeout());
		}
	}
}
