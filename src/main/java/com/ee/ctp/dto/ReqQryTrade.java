package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:17:20
 *
 */
public class ReqQryTrade implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] instrumentID = new byte[31];
	private byte[] exchangeID = new byte[9];
	private byte[] tradeID = new byte[21];
	private byte[] tradeTimeStart = new byte[9];
	private byte[] tradeTimeEnd = new byte[9];

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setInvestorID(String investorID) {
		if (StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}

	public void setInstrumentID(String instrumentID) {
		if (StringUtils.isNotEmpty(instrumentID)) {
			System.arraycopy(instrumentID.getBytes(), 0, this.instrumentID, 0, instrumentID.getBytes().length);
		}
	}

	public void setExchangeID(String exchangeID) {
		if (StringUtils.isNotEmpty(exchangeID)) {
			System.arraycopy(exchangeID.getBytes(), 0, this.exchangeID, 0, exchangeID.getBytes().length);
		}
	}

	public void setTradeID(String tradeID) {
		if (StringUtils.isNotEmpty(tradeID)) {
			System.arraycopy(tradeID.getBytes(), 0, this.tradeID, 0, tradeID.getBytes().length);
		}
	}

	public void setTradeTimeStart(String tradeTimeStart) {
		if (StringUtils.isNotEmpty(tradeTimeStart)) {
			System.arraycopy(tradeTimeStart.getBytes(), 0, this.tradeTimeStart, 0, tradeTimeStart.getBytes().length);
		}
	}

	public void setTradeTimeEnd(String tradeTimeEnd) {
		if (StringUtils.isNotEmpty(tradeTimeEnd)) {
			System.arraycopy(tradeTimeEnd.getBytes(), 0, this.tradeTimeEnd, 0, tradeTimeEnd.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(instrumentID);
		buffer.writeBytes(exchangeID);
		buffer.writeBytes(tradeID);
		buffer.writeBytes(tradeTimeStart);
		buffer.writeBytes(tradeTimeEnd);

		return buffer;
	}
}
