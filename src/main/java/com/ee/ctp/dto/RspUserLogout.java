package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:25
 *
 */
public class RspUserLogout implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorMsg;
	// 11
	private String brokerID;
	// 16
	private String userID;
	
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
	
	public String getBrokerID() {
		return brokerID;
	}
	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public RspUserLogout parseFrom(ByteBuf body, RspError error) {
		RspUserLogout lout = new RspUserLogout();
		lout.setErrorMsg(error.getErrorMsg());
		lout.setErrorCode(error.getErrorCode());
		
		byte[] brokerid = new byte[11];
		body.readBytes(brokerid);
		lout.setBrokerID(StringUtils.trimToEmpty(new String(brokerid)));
		
		byte[] userid = new byte[16];
		body.readBytes(userid);
		lout.setUserID(StringUtils.trimToEmpty(new String(userid)));
		return lout;
	}
	
	@Override
	public String toString() {
		return "RspUserLogout [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", userID=" + userID + "]";
	}
	
}
