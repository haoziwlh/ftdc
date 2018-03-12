package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author zlj
 * 2017年10月17日 下午8:20:30
 *
 */
public class RspUserPasswordUpdate implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorMsg;
	// 11
	private String brokerID;
	// 16
	private String userID;
	// 41
	private String oldPassword;
	// 41
	private String newPassword;
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
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	public RspUserPasswordUpdate parseFrom(ByteBuf body, RspError error) {
		RspUserPasswordUpdate info = new RspUserPasswordUpdate();
		
		info.setErrorMsg(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] oldPassword = new byte[41];
		body.readBytes(oldPassword);
		info.setOldPassword(StringUtils.trimToEmpty(new String(oldPassword)));

		byte[] newPassword = new byte[41];
		body.readBytes(newPassword);
		info.setNewPassword(StringUtils.trimToEmpty(new String(newPassword)));

		return info;
	}
	@Override
	public String toString() {
		return "RspUserPasswordUpdate [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", userID=" + userID + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
	
}
