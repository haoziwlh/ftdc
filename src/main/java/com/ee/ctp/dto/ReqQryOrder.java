package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcExchange;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:16:57
 *
 */
public class ReqQryOrder implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] instrumentID = new byte[31];
	private byte[] exchangeID = new byte[9];
	private byte[] orderSysID = new byte[21];
	private byte[] insertTimeStart = new byte[9];
	private byte[] insertTimeEnd = new byte[9];

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

	public void setExchangeID(FtdcExchange exchangeID) {
		if (exchangeID != null) {
			System.arraycopy(exchangeID.getExchange().getBytes(), 0, this.exchangeID, 0, exchangeID.getExchange().getBytes().length);
		}
	}

	public void setOrderSysID(String orderSysID) {
		if (StringUtils.isNotEmpty(orderSysID)) {
			System.arraycopy(orderSysID.getBytes(), 0, this.orderSysID, 0, orderSysID.getBytes().length);
		}
	}

	public void setInsertTimeStart(String insertTimeStart) {
		if (StringUtils.isNotEmpty(insertTimeStart)) {
			System.arraycopy(insertTimeStart.getBytes(), 0, this.insertTimeStart, 0, insertTimeStart.getBytes().length);
		}
	}

	public void setInsertTimeEnd(String insertTimeEnd) {
		if (StringUtils.isNotEmpty(insertTimeEnd)) {
			System.arraycopy(insertTimeEnd.getBytes(), 0, this.insertTimeEnd, 0, insertTimeEnd.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(instrumentID);
		buffer.writeBytes(exchangeID);
		buffer.writeBytes(orderSysID);
		buffer.writeBytes(insertTimeStart);
		buffer.writeBytes(insertTimeEnd);

		return buffer;
	}
}
