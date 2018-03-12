package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:18
 *
 */
public class RspInputOrder implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorMsg;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 31
	private String instrumentID;
	// 13
	private String orderRef;
	// 16
	private String userID;
	// 1
	private String orderPriceType;
	// 1
	private String direction;
	// 5
	private String combOffsetFlag;
	// 5
	private String combHedgeFlag;
	// 8
	private double limitPrice;
	// 4
	private int volumeTotalOriginal;
	// 1
	private String timeCondition;
	// 9
	private String gTDDate;
	// 1
	private String volumeCondition;
	// 4
	private int minVolume;
	// 1
	private String contingentCondition;
	// 8
	private double stopPrice;
	// 1
	private String forceCloseReason;
	// 4
	private int isAutoSuspend;
	// 21
	private String businessUnit;
	// 4
	private int requestID;
	// 4
	private int userForceClose;
	// 4
	private int isSwapOrder;
	// 9
	private String exchangeID;
	// 17
	private String investUnitID;
	// 13
	private String accountID;
	// 4
	private int currencyID;
	// 11
	private String clientID;
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


	public String getInstrumentID() {
		return instrumentID;
	}


	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}


	public String getOrderRef() {
		return orderRef;
	}


	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getOrderPriceType() {
		return orderPriceType;
	}


	public void setOrderPriceType(String orderPriceType) {
		this.orderPriceType = orderPriceType;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getCombOffsetFlag() {
		return combOffsetFlag;
	}


	public void setCombOffsetFlag(String combOffsetFlag) {
		this.combOffsetFlag = combOffsetFlag;
	}


	public String getCombHedgeFlag() {
		return combHedgeFlag;
	}


	public void setCombHedgeFlag(String combHedgeFlag) {
		this.combHedgeFlag = combHedgeFlag;
	}


	public double getLimitPrice() {
		return limitPrice;
	}


	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}


	public int getVolumeTotalOriginal() {
		return volumeTotalOriginal;
	}


	public void setVolumeTotalOriginal(int volumeTotalOriginal) {
		this.volumeTotalOriginal = volumeTotalOriginal;
	}


	public String getTimeCondition() {
		return timeCondition;
	}


	public void setTimeCondition(String timeCondition) {
		this.timeCondition = timeCondition;
	}


	public String getGTDDate() {
		return gTDDate;
	}


	public void setGTDDate(String gTDDate) {
		this.gTDDate = gTDDate;
	}


	public String getVolumeCondition() {
		return volumeCondition;
	}


	public void setVolumeCondition(String volumeCondition) {
		this.volumeCondition = volumeCondition;
	}


	public int getMinVolume() {
		return minVolume;
	}


	public void setMinVolume(int minVolume) {
		this.minVolume = minVolume;
	}


	public String getContingentCondition() {
		return contingentCondition;
	}


	public void setContingentCondition(String contingentCondition) {
		this.contingentCondition = contingentCondition;
	}


	public double getStopPrice() {
		return stopPrice;
	}


	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}


	public String getForceCloseReason() {
		return forceCloseReason;
	}


	public void setForceCloseReason(String forceCloseReason) {
		this.forceCloseReason = forceCloseReason;
	}


	public int getIsAutoSuspend() {
		return isAutoSuspend;
	}


	public void setIsAutoSuspend(int isAutoSuspend) {
		this.isAutoSuspend = isAutoSuspend;
	}


	public String getBusinessUnit() {
		return businessUnit;
	}


	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}


	public int getRequestID() {
		return requestID;
	}


	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}


	public int getUserForceClose() {
		return userForceClose;
	}


	public void setUserForceClose(int userForceClose) {
		this.userForceClose = userForceClose;
	}


	public int getIsSwapOrder() {
		return isSwapOrder;
	}


	public void setIsSwapOrder(int isSwapOrder) {
		this.isSwapOrder = isSwapOrder;
	}


	public String getExchangeID() {
		return exchangeID;
	}


	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}


	public String getInvestUnitID() {
		return investUnitID;
	}


	public void setInvestUnitID(String investUnitID) {
		this.investUnitID = investUnitID;
	}


	public String getAccountID() {
		return accountID;
	}


	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}


	public int getCurrencyID() {
		return currencyID;
	}


	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}


	public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public String getIPAddress() {
		return iPAddress;
	}


	public void setIPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}


	public String getMacAddress() {
		return macAddress;
	}


	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public RspInputOrder parseFrom(ByteBuf body, RspError error) {
		RspInputOrder info = new RspInputOrder();
		
		info.setErrorMsg(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());
		
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] investorID = new byte[13];
		body.readBytes(investorID);
		info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));

		byte[] instrumentID = new byte[31];
		body.readBytes(instrumentID);
		info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));

		byte[] orderRef = new byte[13];
		body.readBytes(orderRef);
		info.setOrderRef(StringUtils.trimToEmpty(new String(orderRef)));

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] orderPriceType = new byte[1];
		body.readBytes(orderPriceType);
		info.setOrderPriceType(StringUtils.trimToEmpty(new String(orderPriceType)));

		byte[] direction = new byte[1];
		body.readBytes(direction);
		info.setDirection(StringUtils.trimToEmpty(new String(direction)));

		byte[] combOffsetFlag = new byte[5];
		body.readBytes(combOffsetFlag);
		info.setCombOffsetFlag(StringUtils.trimToEmpty(new String(combOffsetFlag)));

		byte[] combHedgeFlag = new byte[5];
		body.readBytes(combHedgeFlag);
		info.setCombHedgeFlag(StringUtils.trimToEmpty(new String(combHedgeFlag)));

		info.setLimitPrice(body.readDouble());

		info.setVolumeTotalOriginal(body.readInt());

		byte[] timeCondition = new byte[1];
		body.readBytes(timeCondition);
		info.setTimeCondition(StringUtils.trimToEmpty(new String(timeCondition)));

		byte[] gTDDate = new byte[9];
		body.readBytes(gTDDate);
		info.setGTDDate(StringUtils.trimToEmpty(new String(gTDDate)));

		byte[] volumeCondition = new byte[1];
		body.readBytes(volumeCondition);
		info.setVolumeCondition(StringUtils.trimToEmpty(new String(volumeCondition)));

		info.setMinVolume(body.readInt());

		byte[] contingentCondition = new byte[1];
		body.readBytes(contingentCondition);
		info.setContingentCondition(StringUtils.trimToEmpty(new String(contingentCondition)));

		info.setStopPrice(body.readDouble());

		byte[] forceCloseReason = new byte[1];
		body.readBytes(forceCloseReason);
		info.setForceCloseReason(StringUtils.trimToEmpty(new String(forceCloseReason)));

		info.setIsAutoSuspend(body.readInt());

		byte[] businessUnit = new byte[21];
		body.readBytes(businessUnit);
		info.setBusinessUnit(StringUtils.trimToEmpty(new String(businessUnit)));

		info.setRequestID(body.readInt());

		info.setUserForceClose(body.readInt());

		info.setIsSwapOrder(body.readInt());

		byte[] exchangeID = new byte[9];
		body.readBytes(exchangeID);
		info.setExchangeID(StringUtils.trimToEmpty(new String(exchangeID)));

		byte[] investUnitID = new byte[17];
		body.readBytes(investUnitID);
		info.setInvestUnitID(StringUtils.trimToEmpty(new String(investUnitID)));

		byte[] accountID = new byte[13];
		body.readBytes(accountID);
		info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));

		info.setCurrencyID(body.readInt());

		byte[] clientID = new byte[11];
		body.readBytes(clientID);
		info.setClientID(StringUtils.trimToEmpty(new String(clientID)));

		byte[] iPAddress = new byte[16];
		body.readBytes(iPAddress);
		info.setIPAddress(StringUtils.trimToEmpty(new String(iPAddress)));

		byte[] macAddress = new byte[21];
		body.readBytes(macAddress);
		info.setMacAddress(StringUtils.trimToEmpty(new String(macAddress)));

		return info;
	}
	@Override
	public String toString() {
		return "RspInputOrder [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", brokerID=" + brokerID
				+ ", investorID=" + investorID + ", instrumentID=" + instrumentID + ", orderRef=" + orderRef
				+ ", userID=" + userID + ", orderPriceType=" + orderPriceType + ", direction=" + direction
				+ ", combOffsetFlag=" + combOffsetFlag + ", combHedgeFlag=" + combHedgeFlag + ", limitPrice="
				+ limitPrice + ", volumeTotalOriginal=" + volumeTotalOriginal + ", timeCondition=" + timeCondition
				+ ", gTDDate=" + gTDDate + ", volumeCondition=" + volumeCondition + ", minVolume=" + minVolume
				+ ", contingentCondition=" + contingentCondition + ", stopPrice=" + stopPrice + ", forceCloseReason="
				+ forceCloseReason + ", isAutoSuspend=" + isAutoSuspend + ", businessUnit=" + businessUnit
				+ ", requestID=" + requestID + ", userForceClose=" + userForceClose + ", isSwapOrder=" + isSwapOrder
				+ ", exchangeID=" + exchangeID + ", investUnitID=" + investUnitID + ", accountID=" + accountID
				+ ", currencyID=" + currencyID + ", clientID=" + clientID + ", iPAddress=" + iPAddress + ", macAddress="
				+ macAddress + "]";
	}

	
}
