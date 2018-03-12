package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:08
 *
 */
public class RspSettlementInfoConfirm implements FtdcRsp{
	private int errorCode;
	//81
	private String errorMsg;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 9
	private String confirmDate;
	// 9
	private String confirmTime;
	
	public String getBrokerID() {
		return brokerID;
	}
	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}
	public String getInvestorID() {
		return investorID;
	}
	public void setInvestorID(String investorID) {
		this.investorID = investorID;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public RspSettlementInfoConfirm parseFrom(ByteBuf body, RspError error) {
		RspSettlementInfoConfirm info = new RspSettlementInfoConfirm();
		
		info.setErrorMsg(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));
		
		byte[] investorID = new byte[13];
		body.readBytes(investorID);
		info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));
		
		byte[] confirmDate = new byte[9];
		body.readBytes(confirmDate);
		info.setConfirmDate(StringUtils.trimToEmpty(new String(confirmDate)));
		
		byte[] confirmTime = new byte[9];
		body.readBytes(confirmTime);
		info.setConfirmTime(StringUtils.trimToEmpty(new String(confirmTime)));
		return info;
	}
	
	@Override
	public String toString() {
		return "RspSettlementInfoConfirm [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", investorID=" + investorID + ", confirmDate=" + confirmDate + ", confirmTime=" + confirmTime + "]";
	}
	
}
