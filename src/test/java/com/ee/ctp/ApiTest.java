package com.ee.ctp;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ReqAccountRegister;
import com.ee.ctp.dto.ReqContractBank;
import com.ee.ctp.dto.ReqFromBankToFuture;
import com.ee.ctp.dto.ReqFromFutureToBank;
import com.ee.ctp.dto.ReqFundPasswordUpdate;
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
import com.ee.ctp.handler.FtdcTraderApi;
import com.ee.ctp.handler.FtdcTraderApiAdapter;
import com.ee.ctp.handler.FtdcTraderSpi;
import com.ee.ctp.handler.PrintResultHandler;
import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;

import io.netty.channel.Channel;
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
public class ApiTest extends TestCase{
	/**
	 * 切换环境
	 */
	private static final boolean TEST = false;
	private static String userid;
	private static String passwd;
	private static String brokerid;
	private static String host;
	private static int port = 52205;
	
	private static final long WATI_TIME = 1000 * 1000;
	private FtdcTraderApi api;
	private PrintResultHandler printResultHandler;
	private static final AtomicInteger REQID = new AtomicInteger(7000);
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
		
		List<ConnectAddrProperty> sas = new ArrayList<>();
		ConnectAddrProperty cap = new ConnectAddrProperty();
		cap.setSocketAddress(new InetSocketAddress(host, port));
		sas.add(cap);
		api = new FtdcTraderApiAdapter();
		printResultHandler = new PrintResultHandler();
		api.registerFront(sas);
		api.registerSpi(getRi(), this.printResultHandler);
	}
	
	public void testLogin() throws Exception {
		login();
		Thread.sleep(WATI_TIME);
	}
	
	private RequestIdentity getRi() {
		RequestIdentity ri = new RequestIdentity();
		ri.setBrokerId(brokerid);
		ri.setUserId(userid);
		ri.setReqId(REQID.getAndIncrement());
		ri.setAuthCode("V7P3HOV70GGKSWBC");
		return ri;
	}
	
	public void login() {
		api.reqUserLogin(getRi(), buildLogin());
	}
	
	public void testLoginAndConstractBank() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqContractBank cb = new ReqContractBank();
		cb.setBrokerID(brokerid);
		api.reqQryContractBank(getRi(), cb);
		
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndLogout() throws Exception {
		login();
		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		ReqUserLogout lout = new ReqUserLogout();
		lout.setUserId(userid);
		lout.setBrokerID(brokerid);
		
		api.reqUserLogout(getRi(), lout);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfo() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		ReqSettlementInfo info = new ReqSettlementInfo();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
//		info.setTradingDay("20170922");

		api.reqSettlementInfo(getRi(), info);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndSettlementInfoConfirm() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		ReqSettlementInfoConfirm info = new ReqSettlementInfoConfirm();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
//		info.setTradingDay("20170922");
		api.reqSettlementInfoConfirm(getRi(), info);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQrySettlementInfoConfirm() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		ReqQrySettlementInfoConfirm info = new ReqQrySettlementInfoConfirm();
		info.setBrokerID(brokerid);
		info.setInvestorID(userid);
		api.reqQrySettlementInfoConfirm(getRi(), info);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndTradingAccount() throws Exception {
		login();		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqTradingAccount reqTradingAccount = new ReqTradingAccount();
		reqTradingAccount.setBrokerID(brokerid);
		reqTradingAccount.setInvestorID(userid);
		api.reqTradingAccount(getRi(), reqTradingAccount);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderInsert() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
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
		api.reqOrderInput(getRi(), order);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndInvestorPosition() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqInvestorPosition investorPosition = new ReqInvestorPosition();
		investorPosition.setBrokerID(brokerid);
		investorPosition.setInvestorID(userid);
		api.reqInvestorPosition(getRi(), investorPosition);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryOrder() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryOrder order = new ReqQryOrder();
		order.setBrokerID(brokerid);
		order.setInvestorID(userid);
//		order.setInsertTimeStart("20170908");
		api.reqQryOrder(getRi(), order);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryTrade() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryTrade trade = new ReqQryTrade();
		trade.setBrokerID(brokerid);
		trade.setInvestorID(userid);
		api.reqQryTrade(getRi(), trade);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryMarginRate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryMarginRate rate = new ReqQryMarginRate();
		rate.setBrokerID(brokerid);
		rate.setInvestorID(userid);
		rate.setInstrumentID("m1803");
		rate.setHedgeFlag(FtdcBillHedgeFlag.FTDC_Speculation);
		api.reqQryMarginRate(getRi(), rate);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndQryCommissionRate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryCommissionRate rate = new ReqQryCommissionRate();
		rate.setBrokerID(brokerid);
		rate.setInvestorID(userid);
		rate.setInstrumentID("ag1712");
		api.reqQryCommissionRate(getRi(), rate);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndAccountRegister() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqAccountRegister reqAccountRegister = new ReqAccountRegister();
		reqAccountRegister.setBrokerID(brokerid);
		reqAccountRegister.setAccountID(userid);
		api.reqQryAccountRegister(getRi(), reqAccountRegister);
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryTransferSerial() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryTransferSerial reqQryTransferSerial = new ReqQryTransferSerial();
		reqQryTransferSerial.setAccountID(userid);
//		reqQryTransferSerial.setBankID("");
		reqQryTransferSerial.setBrokerID(brokerid);
//		reqQryTransferSerial.setCurrencyID("CNY");
		api.reqQryTransferSerial(getRi(), reqQryTransferSerial);
		Thread.sleep(WATI_TIME);
	}
	
	public void testQryInstrument() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqQryInstrument reqQryInstrument = new ReqQryInstrument();
//		reqQryInstrument.setInstrumentID("ag1712"); // 合约ag1712
		reqQryInstrument.setExchangeID(FtdcExchange.FTDC_SHFE); // 上海期货交易所,CZCE,DCE,CFFEX,INE
		api.reqQryInstrument(getRi(), reqQryInstrument);
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromBankToFuture() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
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
		api.reqFromBankToFuture(getRi(), reqFromBankToFuture);
		Thread.sleep(WATI_TIME);
	}
	
	public void testFromFutureToBank() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
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
		reqFromFutureToBank.setCurrencyID(FtdcCurrencyID.CNY);//CNY
		api.reqFromFutureToBank(getRi(), reqFromFutureToBank);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderAction() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
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
		api.reqOrderAction(getRi(), order);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndUserPasswordUpdate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqUserPasswordUpdate order = new ReqUserPasswordUpdate();
		order.setUserID(userid);
		order.setOldPassword("890619ab");
		order.setNewPassword("890619aa");
		order.setBrokerID(brokerid);
		api.reqUserPasswordUpdate(getRi(), order);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndFundPasswordUpdate() throws Exception {
		login();
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(brokerid, userid);
			Thread.sleep(1000);
		}
		
		ReqFundPasswordUpdate order = new ReqFundPasswordUpdate();
		order.setUserID(userid);
		order.setOldPassword("123456");
		order.setNewPassword("654321");
		order.setBrokerID(brokerid);
		order.setCurrencyID(FtdcCurrencyID.CNY);
		
		api.reqFundPasswordUpdate(getRi(), order);
		Thread.sleep(WATI_TIME);
	}
	
	public void testUserAuth() throws Exception {
		api.reqAuthAndLogin(getRi(), buildLogin());
		Thread.sleep(WATI_TIME);
	}
	
	public static ReqUserLogin buildLogin() {
		ReqUserLogin userLogin = new ReqUserLogin();
		userLogin.setTradingDay("19800100");
		userLogin.setBrokerID(brokerid);
		userLogin.setUserId(userid);
		userLogin.setPasswd(passwd);
		userLogin.setInterfaceProductInfo("THOST User");
		userLogin.setProtocolInfo("FTDC 0");
		userLogin.setUserProductInfo("test");
		userLogin.setMacAddress("99:02:A7:9E:92:d7");
		return userLogin;
	}
}
