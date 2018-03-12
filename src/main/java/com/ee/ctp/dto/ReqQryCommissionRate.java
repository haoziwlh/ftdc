package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:58
 *
 */
public class ReqQryCommissionRate implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] instrumentID = new byte[31];

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

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(instrumentID);

		return buffer;
	}
}
