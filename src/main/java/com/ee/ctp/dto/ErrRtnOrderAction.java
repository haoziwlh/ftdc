package com.ee.ctp.dto;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;
/**
 * 
 * @author zlj
 * 2017年10月17日 下午8:14:26
 *
 */
public class ErrRtnOrderAction implements FtdcRsp{
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
	// 9
	private String actionDate;
	// 9
	private String actionTime;
	// 21
	private String traderID;
	// 4
	private int installID;
	// 13
	private String orderLocalID;
	// 13
	private String actionLocalID;
	// 11
	private String participantID;
	// 11
	private String clientID;
	// 21
	private String businessUnit;
	// 1
	private String orderActionStatus;
	// 16
	private String userID;
	// 81
	private String statusMsg;
	// 31
	private String instrumentID;
	// 9
	private String branchID;
	// 17
	private String investUnitID;
	// 16
	private String iPAddress;
	// 21
	private String macAddress;
	// 4
	private int errorCode;
	// 81
	private String errorMsg;

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

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public String getTraderID() {
		return traderID;
	}

	public void setTraderID(String traderID) {
		this.traderID = traderID;
	}

	public int getInstallID() {
		return installID;
	}

	public void setInstallID(int installID) {
		this.installID = installID;
	}

	public String getOrderLocalID() {
		return orderLocalID;
	}

	public void setOrderLocalID(String orderLocalID) {
		this.orderLocalID = orderLocalID;
	}

	public String getActionLocalID() {
		return actionLocalID;
	}

	public void setActionLocalID(String actionLocalID) {
		this.actionLocalID = actionLocalID;
	}

	public String getParticipantID() {
		return participantID;
	}

	public void setParticipantID(String participantID) {
		this.participantID = participantID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getOrderActionStatus() {
		return orderActionStatus;
	}

	public void setOrderActionStatus(String orderActionStatus) {
		this.orderActionStatus = orderActionStatus;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getInstrumentID() {
		return instrumentID;
	}

	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
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
	public ErrRtnOrderAction parseFrom(ByteBuf body, RspError error) {
		ErrRtnOrderAction info = new ErrRtnOrderAction();
		
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

		byte[] actionDate = new byte[9];
		body.readBytes(actionDate);
		info.setActionDate(StringUtils.trimToEmpty(new String(actionDate)));

		byte[] actionTime = new byte[9];
		body.readBytes(actionTime);
		info.setActionTime(StringUtils.trimToEmpty(new String(actionTime)));

		byte[] traderID = new byte[21];
		body.readBytes(traderID);
		info.setTraderID(StringUtils.trimToEmpty(new String(traderID)));

		info.setInstallID(body.readInt());

		byte[] orderLocalID = new byte[13];
		body.readBytes(orderLocalID);
		info.setOrderLocalID(StringUtils.trimToEmpty(new String(orderLocalID)));

		byte[] actionLocalID = new byte[13];
		body.readBytes(actionLocalID);
		info.setActionLocalID(StringUtils
				.trimToEmpty(new String(actionLocalID)));

		byte[] participantID = new byte[11];
		body.readBytes(participantID);
		info.setParticipantID(StringUtils
				.trimToEmpty(new String(participantID)));

		byte[] clientID = new byte[11];
		body.readBytes(clientID);
		info.setClientID(StringUtils.trimToEmpty(new String(clientID)));

		byte[] businessUnit = new byte[21];
		body.readBytes(businessUnit);
		info.setBusinessUnit(StringUtils.trimToEmpty(new String(businessUnit)));

		byte[] orderActionStatus = new byte[1];
		body.readBytes(orderActionStatus);
		info.setOrderActionStatus(StringUtils.trimToEmpty(new String(
				orderActionStatus)));

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] statusMsg = new byte[81];
		body.readBytes(statusMsg);
		info.setStatusMsg(StringUtils.trimToEmpty(new String(statusMsg)));

		byte[] instrumentID = new byte[31];
		body.readBytes(instrumentID);
		info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));

		byte[] branchID = new byte[9];
		body.readBytes(branchID);
		info.setBranchID(StringUtils.trimToEmpty(new String(branchID)));

		byte[] investUnitID = new byte[17];
		body.readBytes(investUnitID);
		info.setInvestUnitID(StringUtils.trimToEmpty(new String(investUnitID)));

		byte[] iPAddress = new byte[16];
		body.readBytes(iPAddress);
		info.setiPAddress(StringUtils.trimToEmpty(new String(iPAddress)));

		byte[] macAddress = new byte[21];
		body.readBytes(macAddress);
		info.setMacAddress(StringUtils.trimToEmpty(new String(macAddress)));

		// TID 00000055
		body.readInt();
		
		info.setErrorCode(body.readInt());
		byte[] errorMsg = new byte[81];
		body.readBytes(errorMsg);
		try {
			info.setErrorMsg(StringUtils.trimToEmpty(new String(errorMsg, ApplicationRuntime.conf().defaultEncoding())));
		} catch (UnsupportedEncodingException e) {
			//ignore
		}
		
		return info;
	}

	@Override
	public String toString() {
		return "ErrRtnOrderAction [brokerID=" + brokerID + ", investorID="
				+ investorID + ", orderActionRef=" + orderActionRef
				+ ", orderRef=" + orderRef + ", requestID=" + requestID
				+ ", frontID=" + frontID + ", sessionID=" + sessionID
				+ ", exchangeID=" + exchangeID + ", orderSysID=" + orderSysID
				+ ", actionFlag=" + actionFlag + ", limitPrice=" + limitPrice
				+ ", volumeChange=" + volumeChange + ", actionDate="
				+ actionDate + ", actionTime=" + actionTime + ", traderID="
				+ traderID + ", installID=" + installID + ", orderLocalID="
				+ orderLocalID + ", actionLocalID=" + actionLocalID
				+ ", participantID=" + participantID + ", clientID=" + clientID
				+ ", businessUnit=" + businessUnit + ", orderActionStatus="
				+ orderActionStatus + ", userID=" + userID + ", statusMsg="
				+ statusMsg + ", instrumentID=" + instrumentID + ", branchID="
				+ branchID + ", investUnitID=" + investUnitID + ", iPAddress="
				+ iPAddress + ", macAddress=" + macAddress + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + "]";
	}
}
