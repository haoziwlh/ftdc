package com.ee.ctp.dto;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;

/**
 * 
 * @author lyb
 *
 */
public class RspAuth implements FtdcRsp{
	private int errorCode;
	//81
	private String errorMsg;
	
	// 11
	private String brokerID;
	// 16
	private String userID;
	// 11
	private String userProductInfo;
	//128
	private byte[] authInfo;
	
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
	public String getUserProductInfo() {
		return userProductInfo;
	}
	public void setUserProductInfo(String userProductInfo) {
		this.userProductInfo = userProductInfo;
	}
	
	public byte[] getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(byte[] authInfo) {
		this.authInfo = authInfo;
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
	
	public RspAuth parseSecondFrom(ByteBuf body, RspError error) {
		RspAuth info = new RspAuth();
		info.setErrorMsg(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] productInfo = new byte[11];
		body.readBytes(productInfo);
		info.setUserProductInfo(StringUtils.trimToEmpty(new String(productInfo)));
		return info;
	}
	
	public RspAuth parseFirstFrom(ByteBuf body) {
		RspAuth info = new RspAuth();
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] productInfo = new byte[11];
		body.readBytes(productInfo);
		info.setUserProductInfo(StringUtils.trimToEmpty(new String(productInfo)));
		
		byte[] authInfo = new byte[128];
		body.readBytes(authInfo);
		info.setAuthInfo(authInfo);
		return info;
	}
	
	
	@Override
	public FtdcRsp parseFrom(ByteBuf body, RspError error) {
		return null;
	}
	
	@Override
	public String toString() {
		return "RspAuth [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID + ", userID="
				+ userID + ", userProductInfo=" + userProductInfo + ", authInfo=" + Arrays.toString(authInfo) + "]";
	}
}
