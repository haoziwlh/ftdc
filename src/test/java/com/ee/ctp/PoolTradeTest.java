package com.ee.ctp;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ReqAccountRegister;
import com.ee.ctp.dto.ReqContractBank;
import com.ee.ctp.dto.ReqFromBankToFuture;
import com.ee.ctp.dto.ReqFromFutureToBank;
import com.ee.ctp.dto.ReqInputOrder;
import com.ee.ctp.dto.ReqInvestorPosition;
import com.ee.ctp.dto.ReqOrderAction;
import com.ee.ctp.dto.ReqQryCommissionRate;
import com.ee.ctp.dto.ReqQryInstrument;
import com.ee.ctp.dto.ReqQryMarginRate;
import com.ee.ctp.dto.ReqQryOrder;
import com.ee.ctp.dto.ReqQrySettlementInfoConfirm;
import com.ee.ctp.dto.ReqQryTrade;
import com.ee.ctp.dto.ReqQryTransferSerial;
import com.ee.ctp.dto.ReqSettlementInfo;
import com.ee.ctp.dto.ReqSettlementInfoConfirm;
import com.ee.ctp.dto.ReqTradingAccount;
import com.ee.ctp.dto.ReqUserLogin;
import com.ee.ctp.dto.ReqUserLogout;
import com.ee.ctp.dto.ReqUserPasswordUpdate;
import com.ee.ctp.dto.UserSession;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
import com.ee.ctp.enums.business.FtdcActionFlagType;
import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcContingentCondition;
import com.ee.ctp.enums.business.FtdcCurrencyID;
import com.ee.ctp.enums.business.FtdcDirection;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.enums.business.FtdcForceCLoseReson;
import com.ee.ctp.enums.business.FtdcOffsetFlagType;
import com.ee.ctp.enums.business.FtdcOrderPriceType;
import com.ee.ctp.enums.business.FtdcPwdFlag;
import com.ee.ctp.enums.business.FtdcTimeCondition;
import com.ee.ctp.enums.business.FtdcTradeCode;
import com.ee.ctp.enums.business.FtdcVolumeCondition;
import com.ee.ctp.enums.business.FtdcYesNoIndicator;
import com.ee.ctp.ftdc.FtdcProtocol;
import com.ee.ctp.handler.FtdcTraderSpi;
import com.ee.ctp.handler.PrintResultHandler;
import com.ee.ctp.pool.FtdClientPool;
import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import junit.framework.TestCase;

/**
 * simnow地址：
 * 180.168.146.187:10000
 * 180.168.146.187:10030
 * brokerid 9999
 * 
 * @author ee
 *
 */
public class PoolTradeTest extends TestCase{
	/**
	 * 切换环境
	 */
	private static final boolean TEST = true;
	private static String userid;
	private static String passwd;
	private static String brokerid;
	private static String host;
	private static int port = 52205;
	
	private static final long WATI_TIME = 1000 * 1000;
	private FtdClientPool pool;
	private RequestIdentity ri;
	private List<ConnectAddrProperty> sas;
	@Override
	protected void setUp() throws Exception {
		if(TEST) {
			userid = "changeme";
			passwd = "changeme";
			brokerid = "9999";
			host = "180.168.146.187";
			port = 10030;
		}else {
			userid = "changeme";
			passwd = "changeme";
			brokerid = "changeme";
			host = "changeme";
			port = 10000;
		}
		sas = new ArrayList<>();
		ConnectAddrProperty cap = new ConnectAddrProperty();
		cap.setSocketAddress(new InetSocketAddress(host, port));
		sas.add(cap);
		pool = FtdClientPool.getPool();
		ri = new RequestIdentity();
		ri.setBrokerId(brokerid);
		ri.setUserId(userid);
		ri.setReqId(6960);
		ApplicationRuntime.bindRequestIdentiity(ri);
	}
	
	public void testLogin() throws Exception {
		login();
		Thread.sleep(WATI_TIME);
	}
	
	public void login() {
		Future<Channel> future = pool.acquire(sas);
		future.addListener(new GenericFutureListener<Future<Channel>>() {

			@Override
			public void operationComplete(Future<Channel> future) throws Exception {
				if(future.isSuccess()) {
					Channel channel = future.getNow();
					FtdcProtocol ftdcProtocol = buildLogin(channel);
					channel.writeAndFlush(ftdcProtocol);
				}else {
					System.out.println(ExceptionUtils.getStackTrace(future.cause()));
				}
			}
		});
	}
	
	public void testLoginAndConstractBank() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqContractBank cb = new ReqContractBank();
		cb.setBrokerID(brokerid);
		cb.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.ContractbankReq.id(), Sequence.ContractBank);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndLogout() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqUserLogout lout = new ReqUserLogout();
		lout.setUserId(userid);
		lout.setBrokerID(brokerid);
		lout.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.UserLogoutReq.id(), Sequence.UserLogout);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfo() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqSettlementInfo info = new ReqSettlementInfo();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
//		info.setTradingDay("20170922");
		info.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.SettlementInfoReq.id(), Sequence.SettlementInfo);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfoConfirm() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqSettlementInfoConfirm info = new ReqSettlementInfoConfirm();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
//		info.setTradingDay("20170922");
		info.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.SettlementInfoConfirmReq.id(), Sequence.SettlementInfoConfirm);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQrySettlementInfoConfirm() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQrySettlementInfoConfirm info = new ReqQrySettlementInfoConfirm();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
		info.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QrySettlementInfoConfirmReq.id(), Sequence.QrySettlementInfoConfirm);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndTradingAccount() throws Exception {
		login();		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqTradingAccount reqTradingAccount = new ReqTradingAccount();
		reqTradingAccount.setBrokerID(brokerid);
		reqTradingAccount.setInvestorID(userid);
		reqTradingAccount.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.TradingAccountReq.id(), Sequence.TradingAccount);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderInsert() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqInputOrder order = new ReqInputOrder();
		order.setInstrumentID("ag1712");
		order.setInvestorID(userid);
		order.setDirection(FtdcDirection.BUY);
		order.setOrderPriceType(FtdcOrderPriceType.FTDC_LimitPrice);
		order.setCombOffsetFlag(FtdcOffsetFlagType.FTDC_Open);
		order.setVolumeTotalOriginal(1);
		order.setCombHedgeFlag(FtdcBillHedgeFlag.FTDC_Speculation);
		order.setLimitPrice(3750);
		order.setTimeCondition(FtdcTimeCondition.FTDC_GFD);
		order.setVolumeCondition(FtdcVolumeCondition.HOST_AV);
		order.setMinVolume(1);
		order.setContingentCondition(FtdcContingentCondition.FTDC_Immediately);
		order.setStopPrice(0);
		order.setForceCloseReason(FtdcForceCLoseReson.THOST_FTDCFCC_NotForceClose);
		order.setExchangeID(FtdcExchange.FTDC_SHFE);
		order.setBrokerID(brokerid);
		order.setRequestID(7000);
		order.setOrderRef("101");
//		order.setIPAddress("122.40.123.44");
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.OrderInsertReq.id(), Sequence.OrderInsert);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndInvestorPosition() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqInvestorPosition investorPosition = new ReqInvestorPosition();
		investorPosition.setBrokerID(brokerid);
		investorPosition.setInvestorID(userid);
		investorPosition.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.InvestorPositionReq.id(), Sequence.InvestorPosition);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryOrder() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryOrder order = new ReqQryOrder();
		order.setBrokerID(brokerid);
		order.setInvestorID(userid);
//		order.setInsertTimeStart("20170908");
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryOrderReq.id(), Sequence.QryOrder);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryTrade() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryTrade trade = new ReqQryTrade();
		trade.setBrokerID(brokerid);
		trade.setInvestorID(userid);
		trade.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryTradeReq.id(), Sequence.QryTrade);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryMarginRate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryMarginRate rate = new ReqQryMarginRate();
		rate.setBrokerID(brokerid);
		rate.setInvestorID(userid);
		rate.setInstrumentID("m1803");
		rate.setHedgeFlag(FtdcBillHedgeFlag.FTDC_Speculation);
		rate.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryMarginRateReq.id(), Sequence.QryMarginRate);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryCommissionRate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryCommissionRate rate = new ReqQryCommissionRate();
		rate.setBrokerID(brokerid);
		rate.setInvestorID(userid);
		rate.setInstrumentID("ag1712");
		rate.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryCommissionRateReq.id(), Sequence.QryCommissionRate);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndAccountRegister() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqAccountRegister reqAccountRegister = new ReqAccountRegister();
		reqAccountRegister.setBrokerID(brokerid);
		reqAccountRegister.setAccountID(userid);
		reqAccountRegister.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.AccountRegisterReq.id(), Sequence.AccountRegister);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryTransferSerial() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryTransferSerial reqQryTransferSerial = new ReqQryTransferSerial();
		reqQryTransferSerial.setAccountID(userid);
//		reqQryTransferSerial.setBankID("");
		reqQryTransferSerial.setBrokerID(brokerid);
//		reqQryTransferSerial.setCurrencyID("CNY");
		reqQryTransferSerial.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryTransferSerialReq.id(), Sequence.QryTransferSerial);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryInstrument() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryInstrument reqQryInstrument = new ReqQryInstrument();
//		reqQryInstrument.setInstrumentID("ag1712"); // 合约ag1712
		reqQryInstrument.setExchangeID(FtdcExchange.FTDC_SHFE); // 上海期货交易所,CZCE,DCE,CFFEX,INE
		reqQryInstrument.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.QryInstrumentReq.id(), Sequence.QryInstrument);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromBankToFuture() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqFromBankToFuture reqFromBankToFuture = new ReqFromBankToFuture();
		reqFromBankToFuture.setBrokerID(brokerid);
		reqFromBankToFuture.setRequestID(6971);
		reqFromBankToFuture.setBankID("4");// 4
		reqFromBankToFuture.setBankBranchID("0000");
		reqFromBankToFuture.setAccountID(userid);
		reqFromBankToFuture.setPassword("241398");
		reqFromBankToFuture.setTradeAmount(1);
		reqFromBankToFuture.setBankPassWord("241398");
		reqFromBankToFuture.setTradeCode(FtdcTradeCode.BankToFuture);
		reqFromBankToFuture.setSecuPwdFlag(FtdcPwdFlag.FTDC_BlankCheck);
		reqFromBankToFuture.setBankPwdFlag(FtdcPwdFlag.FTDC_NoCheck);
		reqFromBankToFuture.setVerifyCertNoFlag(FtdcYesNoIndicator.TDC_No);
		reqFromBankToFuture.setCurrencyID(FtdcCurrencyID.CNY);//CNY
		reqFromBankToFuture.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.FromBankToFutureReq.id(), Sequence.FromBankToFuture);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromFutureToBank() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqFromFutureToBank reqFromFutureToBank = new ReqFromFutureToBank();
		reqFromFutureToBank.setBrokerID(brokerid);
		reqFromFutureToBank.setRequestID(6972);
		reqFromFutureToBank.setBankID("4");
		reqFromFutureToBank.setBankBranchID("0000");
		reqFromFutureToBank.setAccountID(userid);
		reqFromFutureToBank.setPassword("241398");
		reqFromFutureToBank.setTradeAmount(1);
		reqFromFutureToBank.setBankPassWord("241398");
		reqFromFutureToBank.setTradeCode(FtdcTradeCode.FutureToBank);
		reqFromFutureToBank.setSecuPwdFlag(FtdcPwdFlag.FTDC_BlankCheck);
		reqFromFutureToBank.setBankPwdFlag(FtdcPwdFlag.FTDC_NoCheck);
		reqFromFutureToBank.setVerifyCertNoFlag(FtdcYesNoIndicator.TDC_No);
		reqFromFutureToBank.setCurrencyID(FtdcCurrencyID.CNY);
		reqFromFutureToBank.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.FromFutureToBankReq.id(), Sequence.FromFutureToBank);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderAction() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqOrderAction order = new ReqOrderAction();
		
		UserSession userSession = ftdcChannel.attr(FtdcTraderSpi.USER_SESSION).get();
		order.setSessionID(userSession.getSessionId());
		
		// 需要修改参数>>>>>>>>
		order.setOrderRef("101");
		// 需要修改参数<<<<<<<<
		
		order.setBrokerID(brokerid);
		order.setInvestorID(userid);
		
		order.setOrderActionRef(1);
		
		order.setRequestID(new Random().nextInt(9999));
		
		order.setExchangeID(FtdcExchange.FTDC_SHFE);
		order.setInstrumentID("ag1712");
		order.setIPAddress("122.40.123.44");
		order.setMacAddress("98:01:A7:9E:92:d7");
		order.setFrontID(1);
		order.setActionFlag(FtdcActionFlagType.THOST_FTDCAF_Delete);//0-删除  1-修改
		order.setUserID(userid);
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.OrderActionReq.id(), Sequence.OrderAction);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndUserPasswordUpdate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqUserPasswordUpdate order = new ReqUserPasswordUpdate();
		order.setUserID(userid);
		order.setOldPassword("890619ab");
		order.setNewPassword("890619aa");
		order.setBrokerID(brokerid);
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), ri.getReqId(), TID.UserPasswordUpdateReq.id(), Sequence.UserPasswordUpdate);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public static FtdcProtocol buildLogin(Channel channel) {
		ReqUserLogin userLogin = new ReqUserLogin();
		userLogin.setTradingDay("19800100");
		userLogin.setBrokerID(brokerid);
		userLogin.setUserId(userid);
		userLogin.setPasswd(passwd);
		userLogin.setInterfaceProductInfo("THOST User");
		userLogin.setProtocolInfo("FTDC 0");
		userLogin.setMacAddress("98:01:A7:9E:92:d7");
		ByteBuf buffer = channel.alloc().buffer();
		userLogin.write(buffer.retain());
		new PrintResultHandler().reqister(channel);
		return new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6960, TID.UserLoginReq.id());
	}
}
