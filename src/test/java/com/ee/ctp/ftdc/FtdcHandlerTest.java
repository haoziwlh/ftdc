package com.ee.ctp.ftdc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ErrRtnOrderAction;
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
import com.ee.ctp.enums.FtdTagType;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.ftdc.FtdcHandler;
import com.ee.ctp.ftdc.FtdcProtocol;
import com.ee.ctp.handler.FtdcTraderSpi;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;

public class FtdcHandlerTest {

	@Test
	public void testHandlerHeartbeat() {
		FtdcProtocol protocol = new FtdcProtocol(FtdType.FTDTypeFTDC);
		FtdTagType tagType = FtdTagType.FTDTagKeepAlive;
		protocol.addExt(tagType.type(), tagType.length(), 0);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcHandler());
		channel.writeInbound(protocol);
		
		assertTrue(channel.finish());

		FtdcProtocol p = channel.readOutbound();
		assertEquals(FtdType.FTDTypeNone, p.getType());
		assertEquals(2, p.getExtLength());
		assertEquals(5, p.ext().getTagType());
		assertNull(channel.readInbound());
		channel.finish();
	}

	@Test(timeout = 5000)
	public void testHandlerNoData() {
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeByte(2);
		buffer.writeByte(0);
		buffer.writeShort(22);
		buffer.writeZero(22);
		FtdcProtocol protocol = new FtdcProtocol((short)2, (short)0, 22, buffer, true);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcHandler());
		channel.attr(FtdcTraderSpi.TRADER_API).set(new Testhandler());
		channel.writeInbound(protocol);
	}
	
	
	static class Testhandler implements FtdcTraderSpi {

		@Override
		public void reqister(Channel channel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserLogin(RspUserLogin rspUserLogin, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNodata(int reqId) {
			assertEquals(0, reqId);
		}

		@Override
		public void onRspError(RspError error, int reqId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspError(RspError error, RequestIdentity ri) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserLogout(RspUserLogout lout, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspSettlementInfo(RspSettlementInfo info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspSettlementInfoConfirm(RspSettlementInfoConfirm info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQrySettlementInfoConfirm(RspQrySettlementInfoConfirm info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspTradingAccount(RspTradingAccount tradingAccount, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspOrderInput(RspInputOrder inputOrder, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnOrder(RtnOrder rtnOrder, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnTrade(RtnTrade rtnTrade, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRecieveUnUsedTid(String tid, int reqId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspInvestorPosition(RspInvestorPosition investorPisition, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryMarginRate(RspQryMarginRate qryMarginRate, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryCommissionRate(RspQryCommissionRate qryCommissionRate, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onErrRtnOrderAction(ErrRtnOrderAction errRtnOrderAction, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryAccountRegister(RspAccountRegister accountRegister, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryContractBank(RspContractBank contractBank, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspOrderAction(RspOrderAction rspOrderAction, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryTransferSerial(RspQryTransferSerial info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryInstrument(RspQryInstrument info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspFutureOrBank(RspFutureOrBank info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnBankOrFuture(RtnBankOrFuture rtnBankOrFuture, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserPasswordUpdate(RspUserPasswordUpdate info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unRecieveUnusedSequence(int reqId, int tid, int sequence) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryOrder(RtnOrder order, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryTrade(RtnTrade trade, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspFundPasswordUpdate(RspFundPasswordUpdate info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserAuthFirst(RspAuth rspAuth, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserAuthSecond(RspAuth rspAuth, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
}
