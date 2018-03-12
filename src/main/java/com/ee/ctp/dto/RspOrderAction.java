package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author zlj
 * 2017年10月17日 下午8:19:30
 *
 */
public class RspOrderAction implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorMsg;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 4
	private int orderActionRef;
	// 13
	private String orderRef;
	// 4
	private int requestID;
	// 4
	private int frontID;
	// 4
	private int sessionID;
	// 9
	private String exchangeID;
	// 21
	private String orderSysID;
	// 1
	private String actionFlag;
	// 8
	private double limitPrice;
	// 4
	private int volumeChange;
	// 16
	private String userID;
	// 31
	private String instrumentID;
	// 17
	private String investUnitID;
	// 16
	private String iPAddress;
	// 21
	private String macAddress;

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

	public String getInvestorID() {
		return investorID;
	}

	public void setInvestorID(String investorID) {
		this.investorID = investorID;
	}

	public int getOrderActionRef() {
		return orderActionRef;
	}

	public void setOrderActionRef(int orderActionRef) {
		this.orderActionRef = orderActionRef;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getFrontID() {
		return frontID;
	}

	public void setFrontID(int frontID) {
		this.frontID = frontID;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getExchangeID() {
		return exchangeID;
	}

	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}

	public String getOrderSysID() {
		return orderSysID;
	}

	public void setOrderSysID(String orderSysID) {
		this.orderSysID = orderSysID;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public int getVolumeChange() {
		return volumeChange;
	}

	public void setVolumeChange(int volumeChange) {
		this.volumeChange = volumeChange;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getInstrumentID() {
		return instrumentID;
	}

	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}

	public String getInvestUnitID() {
		return investUnitID;
	}

	public void setInvestUnitID(String investUnitID) {
		this.investUnitID = investUnitID;
	}

	public String getiPAddress() {
		return iPAddress;
	}

	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public RspOrderAction parseFrom(ByteBuf body, RspError error) {
		RspOrderAction info = new RspOrderAction();

		info.setErrorMsg(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());

		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] investorID = new byte[13];
		body.readBytes(investorID);
		info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));

		info.setOrderActionRef(body.readInt());

		byte[] orderRef = new byte[13];
		body.readBytes(orderRef);
		info.setOrderRef(StringUtils.trimToEmpty(new String(orderRef)));

		info.setRequestID(body.readInt());

		info.setFrontID(body.readInt());

		info.setSessionID(body.readInt());

		byte[] exchangeID = new byte[9];
		body.readBytes(exchangeID);
		info.setExchangeID(StringUtils.trimToEmpty(new String(exchangeID)));

		byte[] orderSysID = new byte[21];
		body.readBytes(orderSysID);
		info.setOrderSysID(StringUtils.trimToEmpty(new String(orderSysID)));

		byte[] actionFlag = new byte[1];
		body.readBytes(actionFlag);
		info.setActionFlag(StringUtils.trimToEmpty(new String(actionFlag)));

		info.setLimitPrice(body.readDouble());

		info.setVolumeChange(body.readInt());

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] instrumentID = new byte[31];
		body.readBytes(instrumentID);
		info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));

		byte[] investUnitID = new byte[17];
		body.readBytes(investUnitID);
		info.setInvestUnitID(StringUtils.trimToEmpty(new String(investUnitID)));

		byte[] iPAddress = new byte[16];
		body.readBytes(iPAddress);
		info.setiPAddress(StringUtils.trimToEmpty(new String(iPAddress)));

		byte[] macAddress = new byte[21];
		body.readBytes(macAddress);
		info.setMacAddress(StringUtils.trimToEmpty(new String(macAddress)));

		return info;
	}

	@Override
	public String toString() {
		return "RspOrderAction [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", investorID=" + investorID + ", orderActionRef=" + orderActionRef + ", orderRef=" + orderRef
				+ ", requestID=" + requestID + ", frontID=" + frontID + ", sessionID=" + sessionID + ", exchangeID="
				+ exchangeID + ", orderSysID=" + orderSysID + ", actionFlag=" + actionFlag + ", limitPrice="
				+ limitPrice + ", volumeChange=" + volumeChange + ", userID=" + userID + ", instrumentID="
				+ instrumentID + ", investUnitID=" + investUnitID + ", iPAddress=" + iPAddress + ", macAddress="
				+ macAddress + "]";
	}

}