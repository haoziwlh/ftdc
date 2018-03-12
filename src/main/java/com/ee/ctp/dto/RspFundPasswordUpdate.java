package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcCurrencyID;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author lyb
 *
 */
public class RspFundPasswordUpdate implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorMsg;
	// 11
	private String brokerID;
	// 13
	private String userID;
	// 41
	private String oldPassword;
	// 41
	private String newPassword;
	private FtdcCurrencyID currencyID;
	
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
	public FtdcCurrencyID getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(FtdcCurrencyID currencyID) {
		this.currencyID = currencyID;
	}
	
	@Override
	public String toString() {
		return "RspFundPasswordUpdate [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", userID=" + userID + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", currencyID=" + currencyID + "]";
	}
	
	
	@Override
	public RspFundPasswordUpdate parseFrom(ByteBuf body, RspError error) {
		
		this.setErrorMsg(error.getErrorMsg());
		this.setErrorCode(error.getErrorCode());
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		this.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] userID = new byte[13];
		body.readBytes(userID);
		this.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] oldPassword = new byte[41];
		body.readBytes(oldPassword);
		this.setOldPassword(StringUtils.trimToEmpty(new String(oldPassword)));

		byte[] newPassword = new byte[41];
		body.readBytes(newPassword);
		this.setNewPassword(StringUtils.trimToEmpty(new String(newPassword)));

		return this;
	}
}
