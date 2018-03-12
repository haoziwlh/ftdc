package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:20
 *
 */
public class RspUserLogin implements FtdcRsp{
	private int errorCode;
	//81
	private String errorMsg;
	//9
	private String tradingDay;
	//9
	private String loginTime;
	//11
	private String brokerID;
	//16
	private String userID;
	//41
	private String systemName;
	//4
	private int frontId;
	//4
	private int sessionId;
	//13
	private String orderRefType;
	//9
	private String shfeTime;
	//9
	private String dcetTime;
	//9
	private String czceTime;
	//9
	private String ffexTime;
	//9
	private String inetTime;
	
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
	
	public String getTradingDay() {
		return tradingDay;
	}
	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public int getFrontId() {
		return frontId;
	}
	public void setFrontId(int frontId) {
		this.frontId = frontId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getOrderRefType() {
		return orderRefType;
	}
	public void setOrderRefType(String orderRefType) {
		this.orderRefType = orderRefType;
	}
	public String getShfeTime() {
		return shfeTime;
	}
	public void setShfeTime(String shfeTime) {
		this.shfeTime = shfeTime;
	}
	public String getDcetTime() {
		return dcetTime;
	}
	public void setDcetTime(String dcetTime) {
		this.dcetTime = dcetTime;
	}
	public String getCzceTime() {
		return czceTime;
	}
	public void setCzceTime(String czceTime) {
		this.czceTime = czceTime;
	}
	public String getFfexTime() {
		return ffexTime;
	}
	public void setFfexTime(String ffexTime) {
		this.ffexTime = ffexTime;
	}
	public String getInetTime() {
		return inetTime;
	}
	public void setInetTime(String inetTime) {
		this.inetTime = inetTime;
	}
	
	@Override
	public RspUserLogin parseFrom(ByteBuf body, RspError error) {
		RspUserLogin userLogin = new RspUserLogin();
		
		userLogin.setErrorMsg(error.getErrorMsg());
		userLogin.setErrorCode(error.getErrorCode());
		
		byte[] tradingDay = new byte[9];
		body.readBytes(tradingDay);
		userLogin.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));
		
		byte[] tradingTime = new byte[9];
		body.readBytes(tradingTime);
		userLogin.setLoginTime(StringUtils.trimToEmpty(new String(tradingTime)));
		
		byte[] brokerid = new byte[11];
		body.readBytes(brokerid);
		userLogin.setBrokerID(StringUtils.trimToEmpty(new String(brokerid)));
		
		byte[] userid = new byte[16];
		body.readBytes(userid);
		userLogin.setUserID(StringUtils.trimToEmpty(new String(userid)));
		
		byte[] sysname = new byte[41];
		body.readBytes(sysname);
		userLogin.setSystemName(StringUtils.trimToEmpty(new String(sysname)));
		
		userLogin.setFrontId(body.readInt());
		userLogin.setSessionId(body.readInt());
		
		byte[] maxorderRef = new byte[13];
		body.readBytes(maxorderRef);
		userLogin.setOrderRefType(StringUtils.trimToEmpty(new String(maxorderRef)));
		
		byte[] shfe = new byte[9];
		body.readBytes(shfe);
		userLogin.setShfeTime(StringUtils.trimToEmpty(new String(shfe)));
		
		byte[] dcet = new byte[9];
		body.readBytes(dcet);
		userLogin.setDcetTime(StringUtils.trimToEmpty(new String(dcet)));
		
		byte[] czce = new byte[9];
		body.readBytes(czce);
		userLogin.setCzceTime(StringUtils.trimToEmpty(new String(czce)));
		
		byte[] ffex = new byte[9];
		body.readBytes(ffex);
		userLogin.setFfexTime(StringUtils.trimToEmpty(new String(ffex)));
		
		byte[] inet = new byte[9];
		body.readBytes(inet);
		userLogin.setInetTime(StringUtils.trimToEmpty(new String(inet)));
		
		return userLogin;
	}
	
	@Override
	public String toString() {
		return "RspUserLogin [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", tradingDay=" + tradingDay + ", loginTime=" + loginTime + ", brokerID=" + brokerID + ", userID="
				+ userID + ", systemName=" + systemName + ", frontId=" + frontId + ", sessionId=" + sessionId
				+ ", orderRefType=" + orderRefType + ", shfeTime=" + shfeTime + ", dcetTime=" + dcetTime + ", czceTime="
				+ czceTime + ", ffexTime=" + ffexTime + ", inetTime=" + inetTime + "]";
	}
	
	
}
