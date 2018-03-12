package com.ee.ctp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.Random;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.CtpClient;
import com.ee.ctp.dto.ReqOrderAction;
import com.ee.ctp.dto.ReqUserLogin;
import com.ee.ctp.dto.UserSession;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
import com.ee.ctp.enums.business.FtdcActionFlagType;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.ftdc.FtdcProtocol;
import com.ee.ctp.handler.FtdcTraderSpi;
import com.ee.ctp.handler.PrintResultHandler;

import junit.framework.TestCase;

/**
 * @author ee
 *
 */
public class TradeOrderActionTest extends TestCase{

	private static final String USERID = "changeme";
	private static final String PASSWD = "changeme";
	private static final String BROKERID = "9999";
	private static final String HOST = "180.168.146.187";
	private static final int PORT = 10000;
	
	private static final long WATI_TIME = 1000 * 1000;
	
	public void testLogin() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(HOST, PORT, userLoginReqhandler);
		Thread.sleep(WATI_TIME);
	}
	
	public void testLoginAndOrderAction() throws Exception {
		UserLoginReqhandler userLoginReqhandler = new UserLoginReqhandler();
		CtpClient.ctp(HOST, PORT, userLoginReqhandler);
		
		Channel ftdcChannel = ApplicationRuntime.getFtdcChannel(BROKERID, USERID);
		while(ftdcChannel == null) {
			ftdcChannel = ApplicationRuntime.getFtdcChannel(BROKERID, USERID);
			Thread.sleep(1000);
		}
		
		ByteBuf buffer = ftdcChannel.alloc().buffer();
		ReqOrderAction order = new ReqOrderAction();
		
		UserSession userSession = ftdcChannel.attr(FtdcTraderSpi.USER_SESSION).get();
		order.setSessionID(userSession.getSessionId());
		
		// 需要修改参数>>>>>>>>
		order.setOrderRef("101");
		// 需要修改参数<<<<<<<<
		
		order.setBrokerID(BROKERID);
		order.setInvestorID(USERID);
		
		order.setOrderActionRef(1);
		
		order.setRequestID(new Random().nextInt(9999));
		
		order.setExchangeID(FtdcExchange.FTDC_SHFE);
		order.setInstrumentID("ag1712");
		order.setIPAddress("122.40.123.44");
		order.setMacAddress("98:01:A7:9E:92:d7");
		order.setFrontID(1);
		order.setActionFlag(FtdcActionFlagType.THOST_FTDCAF_Delete);//0-删除  1-修改
		order.setUserID(USERID);
		order.write(buffer.retain());
		FtdcProtocol ftdc = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 6971, TID.OrderActionReq.id(), Sequence.OrderAction);
		ftdcChannel.writeAndFlush(ftdc);
		
		Thread.sleep(WATI_TIME);
	}
	
	
	static class UserLoginReqhandler implements ChannelFutureListener {

		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			Channel channel = future.channel();
			ReqUserLogin userLogin = new ReqUserLogin();
			userLogin.setTradingDay("19800100");
			userLogin.setBrokerID(BROKERID);
			userLogin.setUserId(USERID);
			userLogin.setPasswd(PASSWD);
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
