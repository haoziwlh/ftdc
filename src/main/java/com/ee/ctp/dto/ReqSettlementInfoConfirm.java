package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:17:51
 *
 */
public class ReqSettlementInfoConfirm implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] confirmDate = new byte[9];
	private byte[] confirmTime = new byte[9];
	
	public void setBrokerID(String brokerID) {
		if(StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}
	
	public void setInvestorID(String investorID) {
		if(StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}
	
	public void setConfirmDate(String confirmDate) {
		if(StringUtils.isNotEmpty(confirmDate)) {
			System.arraycopy(confirmDate.getBytes(), 0, this.confirmDate, 0, confirmDate.getBytes().length);
		}
	}
	
	public void setConfirmTime(String confirmTime) {
		if(StringUtils.isNotEmpty(confirmTime)) {
			System.arraycopy(confirmTime.getBytes(), 0, this.confirmTime, 0, confirmTime.getBytes().length);
		}
	}
	
	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(confirmDate);
		buffer.writeBytes(confirmTime);
		return buffer;
	}
}

