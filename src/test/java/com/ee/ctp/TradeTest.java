package com.ee.ctp;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.CtpClient;
import com.ee.ctp.dto.ReqAccountRegister;
import com.ee.ctp.dto.ReqContractBank;
import com.ee.ctp.dto.ReqFromBankToFuture;
import com.ee.ctp.dto.ReqFromFutureToBank;
import com.ee.ctp.dto.ReqInputOrder;
import com.ee.ctp.dto.ReqInvestorPosition;
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
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
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
import com.ee.ctp.handler.PrintResultHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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
public class TradeTest extends TestCase{
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
	
	@Override
	protected void setUp() throws Exception {
		if(TEST) {
			userid = "changeme";
			passwd = "changeme";
			brokerid = "9999";
			host = "180.168.146.187";
			port = 10000;
		}else {
			userid = "changeme";
			passwd = "changeme";
			brokerid = "changeme";
			host = "changeme";
			port = 10000;
		}
		
	}
	
	public void testLogin() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndConstractBank() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqContractBank cb = new ReqContractBank();
		cb.setBrokerID(brokerid);
		cb.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6961, TID.ContractbankReq.id(), Sequence.ContractBank);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndLogout() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6962, TID.UserLogoutReq.id(), Sequence.UserLogout);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfo() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6963, TID.SettlementInfoReq.id(), Sequence.SettlementInfo);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfoConfirm() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6964, TID.SettlementInfoConfirmReq.id(), Sequence.SettlementInfoConfirm);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQrySettlementInfoConfirm() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6965, TID.QrySettlementInfoConfirmReq.id(), Sequence.QrySettlementInfoConfirm);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndTradingAccount() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6966, TID.TradingAccountReq.id(), Sequence.TradingAccount);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderInsert() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6967, TID.OrderInsertReq.id(), Sequence.OrderInsert);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndInvestorPosition() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6968, TID.InvestorPositionReq.id(), Sequence.InvestorPosition);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryOrder() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqQryOrder order = new ReqQryOrder();
		order.setBrokerID(brokerid);
		order.setInvestorID(userid);
		order.setInsertTimeStart("20170928");
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6969, TID.QryOrderReq.id(), Sequence.QryOrder);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryTrade() throws Exception {  
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6970, TID.QryTradeReq.id(), Sequence.QryTrade);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryMarginRate() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6971, TID.QryMarginRateReq.id(), Sequence.QryMarginRate);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryCommissionRate() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6972, TID.QryCommissionRateReq.id(), Sequence.QryCommissionRate);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndAccountRegister() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6973, TID.AccountRegisterReq.id(), Sequence.AccountRegister);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryTransferSerial() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6969, TID.QryTransferSerialReq.id(), Sequence.QryTransferSerial);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryInstrument() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6970, TID.QryInstrumentReq.id(), Sequence.QryInstrument);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromBankToFuture() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqFromBankToFuture reqFromBankToFuture = new ReqFromBankToFuture();
		reqFromBankToFuture.setBrokerID(brokerid);
		reqFromBankToFuture.setRequestID(6971);
		reqFromBankToFuture.setBankID("4");// 4(int 872415232)
		reqFromBankToFuture.setBankBranchID("0000");
		reqFromBankToFuture.setAccountID(userid);
		reqFromBankToFuture.setPassword("241398");
		reqFromBankToFuture.setTradeAmount(1);
		reqFromBankToFuture.setBankPassWord("241398");
		reqFromBankToFuture.setTradeCode(FtdcTradeCode.FutureToBank);
		reqFromBankToFuture.setSecuPwdFlag(FtdcPwdFlag.FTDC_BlankCheck);
		reqFromBankToFuture.setBankPwdFlag(FtdcPwdFlag.FTDC_NoCheck);
		reqFromBankToFuture.setVerifyCertNoFlag(FtdcYesNoIndicator.TDC_No);
		reqFromBankToFuture.setCurrencyID(FtdcCurrencyID.CNY);//CNY
		reqFromBankToFuture.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6971, TID.FromBankToFutureReq.id(), Sequence.FromBankToFuture);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromFutureToBank() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(host, port, userLoginReqhandler);
		
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
		reqFromFutureToBank.setCurrencyID(FtdcCurrencyID.CNY); // int 1129208064
		reqFromFutureToBank.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6972, TID.FromFutureToBankReq.id(), Sequence.FromFutureToBank);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	static class UserLoginReqhandler implements ChannelFutureListener {

		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			Channel channel = future.channel();
			ReqUserLogin userLogin = new ReqUserLogin();
			userLogin.setTradingDay("19800100");
			userLogin.setBrokerID(brokerid);
			userLogin.setUserId(userid);
			userLogin.setPasswd(passwd);
			userLogin.setInterfaceProductInfo("THOST User");
			userLogin.setProtocolInfo("FTDC 0");
			userLogin.setMacAddress("99:01:A7:9E:92:d7");
			ByteBuf buffer = channel.alloc().buffer();
			userLogin.write(buffer.retain());
			new PrintResultHandler().reqister(channel);
			FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(),6960, TID.UserLoginReq.id());
			channel.writeAndFlush(ftdc);
		}
		
	}
}
