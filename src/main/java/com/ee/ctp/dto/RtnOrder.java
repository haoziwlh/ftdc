package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcContingentCondition;
import com.ee.ctp.enums.business.FtdcDirection;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.enums.business.FtdcForceCLoseReson;
import com.ee.ctp.enums.business.FtdcOffsetFlagType;
import com.ee.ctp.enums.business.FtdcOrderPriceType;
import com.ee.ctp.enums.business.FtdcOrderSourceType;
import com.ee.ctp.enums.business.FtdcOrderStatusType;
import com.ee.ctp.enums.business.FtdcOrderSubmitStatusType;
import com.ee.ctp.enums.business.FtdcOrderType;
import com.ee.ctp.enums.business.FtdcTimeCondition;
import com.ee.ctp.enums.business.FtdcVolumeCondition;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:40
 *
 */
public class RtnOrder implements FtdcRsp{
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
	private FtdcOrderPriceType orderPriceType;
	// 1
	private FtdcDirection direction;
	// 5
	private FtdcOffsetFlagType combOffsetFlag;
	// 5
	private FtdcBillHedgeFlag combHedgeFlag;
	// 8
	private double limitPrice;
	// 4
	private int volumeTotalOriginal;
	// 1
	private FtdcTimeCondition timeCondition;
	// 9
	private String gTDDate;
	// 1
	private FtdcVolumeCondition volumeCondition;
	// 4
	private int minVolume;
	// 1
	private FtdcContingentCondition contingentCondition;
	// 8
	private double stopPrice;
	// 1
	private FtdcForceCLoseReson forceCloseReason;
	// 4
	private int isAutoSuspend;
	// 21
	private String businessUnit;
	// 4
	private int requestID;
	// 13
	private String orderLocalID;
	// 9
	private FtdcExchange exchangeID;
	// 11
	private String participantID;
	// 11
	private String clientID;
	// 31
	private String exchangeInstID;
	// 21
	private String traderID;
	// 4
	private int installID;
	// 1
	private FtdcOrderSubmitStatusType orderSubmitStatus;
	// 4
	private int notifySequence;
	// 9
	private String tradingDay;
	// 4
	private int settlementID;
	// 21
	private String orderSysID;
	// 1
	private FtdcOrderSourceType orderSource;
	// 1
	private FtdcOrderStatusType orderStatus;
	// 1
	private FtdcOrderType orderType;
	// 4
	private int volumeTraded;
	// 4
	private int volumeTotal;
	// 9
	private String insertDate;
	// 9
	private String insertTime;
	// 9
	private String activeTime;
	// 9
	private String suspendTime;
	// 9
	private String updateTime;
	// 9
	private String cancelTime;
	// 21
	private String activeTraderID;
	// 11
	private String clearingPartID;
	// 4
	private int sequenceNo;
	// 4
	private int frontID;
	// 4
	private int sessionID;
	// 11
	private String userProductInfo;
	// 81
	private String statusMsg;
	// 4
	private int userForceClose;
	// 16
	private String activeUserID;
	// 4
	private int brokerOrderSeq;
	// 21
	private String relativeOrderSysID;
	// 4
	private int zCETotalTradedVolume;
	// 4
	private int isSwapOrder;
	// 9
	private String branchID;
	// 17
	private String investUnitID;
	// 13
	private String accountID;
	// 4
	private int currencyID;
	// 16
	private String iPAddress;
	// 21
	private String macAddress;

	
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


	public FtdcOrderPriceType getOrderPriceType() {
		return orderPriceType;
	}


	public void setOrderPriceType(FtdcOrderPriceType orderPriceType) {
		this.orderPriceType = orderPriceType;
	}


	public FtdcDirection getDirection() {
		return direction;
	}


	public void setDirection(FtdcDirection direction) {
		this.direction = direction;
	}


	public FtdcOffsetFlagType getCombOffsetFlag() {
		return combOffsetFlag;
	}


	public void setCombOffsetFlag(FtdcOffsetFlagType combOffsetFlag) {
		this.combOffsetFlag = combOffsetFlag;
	}


	public FtdcBillHedgeFlag getCombHedgeFlag() {
		return combHedgeFlag;
	}


	public void setCombHedgeFlag(FtdcBillHedgeFlag combHedgeFlag) {
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


	public FtdcTimeCondition getTimeCondition() {
		return timeCondition;
	}


	public void setTimeCondition(FtdcTimeCondition timeCondition) {
		this.timeCondition = timeCondition;
	}


	public String getGTDDate() {
		return gTDDate;
	}


	public void setGTDDate(String gTDDate) {
		this.gTDDate = gTDDate;
	}


	public FtdcVolumeCondition getVolumeCondition() {
		return volumeCondition;
	}


	public void setVolumeCondition(FtdcVolumeCondition volumeCondition) {
		this.volumeCondition = volumeCondition;
	}


	public int getMinVolume() {
		return minVolume;
	}


	public void setMinVolume(int minVolume) {
		this.minVolume = minVolume;
	}


	public FtdcContingentCondition getContingentCondition() {
		return contingentCondition;
	}


	public void setContingentCondition(FtdcContingentCondition contingentCondition) {
		this.contingentCondition = contingentCondition;
	}


	public double getStopPrice() {
		return stopPrice;
	}


	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}


	public FtdcForceCLoseReson getForceCloseReason() {
		return forceCloseReason;
	}


	public void setForceCloseReason(FtdcForceCLoseReson forceCloseReason) {
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


	public String getOrderLocalID() {
		return orderLocalID;
	}


	public void setOrderLocalID(String orderLocalID) {
		this.orderLocalID = orderLocalID;
	}


	public FtdcExchange getExchangeID() {
		return exchangeID;
	}


	public void setExchangeID(FtdcExchange exchangeID) {
		this.exchangeID = exchangeID;
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


	public String getExchangeInstID() {
		return exchangeInstID;
	}


	public void setExchangeInstID(String exchangeInstID) {
		this.exchangeInstID = exchangeInstID;
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


	public FtdcOrderSubmitStatusType getOrderSubmitStatus() {
		return orderSubmitStatus;
	}


	public void setOrderSubmitStatus(FtdcOrderSubmitStatusType orderSubmitStatus) {
		this.orderSubmitStatus = orderSubmitStatus;
	}


	public int getNotifySequence() {
		return notifySequence;
	}


	public void setNotifySequence(int notifySequence) {
		this.notifySequence = notifySequence;
	}


	public String getTradingDay() {
		return tradingDay;
	}


	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}


	public int getSettlementID() {
		return settlementID;
	}


	public void setSettlementID(int settlementID) {
		this.settlementID = settlementID;
	}


	public String getOrderSysID() {
		return orderSysID;
	}


	public void setOrderSysID(String orderSysID) {
		this.orderSysID = orderSysID;
	}


	public FtdcOrderSourceType getOrderSource() {
		return orderSource;
	}


	public void setOrderSource(FtdcOrderSourceType orderSource) {
		this.orderSource = orderSource;
	}


	public FtdcOrderStatusType getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(FtdcOrderStatusType orderStatus) {
		this.orderStatus = orderStatus;
	}


	public FtdcOrderType getOrderType() {
		return orderType;
	}


	public void setOrderType(FtdcOrderType orderType) {
		this.orderType = orderType;
	}


	public int getVolumeTraded() {
		return volumeTraded;
	}


	public void setVolumeTraded(int volumeTraded) {
		this.volumeTraded = volumeTraded;
	}


	public int getVolumeTotal() {
		return volumeTotal;
	}


	public void setVolumeTotal(int volumeTotal) {
		this.volumeTotal = volumeTotal;
	}


	public String getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}


	public String getInsertTime() {
		return insertTime;
	}


	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}


	public String getActiveTime() {
		return activeTime;
	}


	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}


	public String getSuspendTime() {
		return suspendTime;
	}


	public void setSuspendTime(String suspendTime) {
		this.suspendTime = suspendTime;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public String getCancelTime() {
		return cancelTime;
	}


	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}


	public String getActiveTraderID() {
		return activeTraderID;
	}


	public void setActiveTraderID(String activeTraderID) {
		this.activeTraderID = activeTraderID;
	}


	public String getClearingPartID() {
		return clearingPartID;
	}


	public void setClearingPartID(String clearingPartID) {
		this.clearingPartID = clearingPartID;
	}


	public int getSequenceNo() {
		return sequenceNo;
	}


	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
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


	public String getUserProductInfo() {
		return userProductInfo;
	}


	public void setUserProductInfo(String userProductInfo) {
		this.userProductInfo = userProductInfo;
	}


	public String getStatusMsg() {
		return statusMsg;
	}


	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}


	public int getUserForceClose() {
		return userForceClose;
	}


	public void setUserForceClose(int userForceClose) {
		this.userForceClose = userForceClose;
	}


	public String getActiveUserID() {
		return activeUserID;
	}


	public void setActiveUserID(String activeUserID) {
		this.activeUserID = activeUserID;
	}


	public int getBrokerOrderSeq() {
		return brokerOrderSeq;
	}


	public void setBrokerOrderSeq(int brokerOrderSeq) {
		this.brokerOrderSeq = brokerOrderSeq;
	}


	public String getRelativeOrderSysID() {
		return relativeOrderSysID;
	}


	public void setRelativeOrderSysID(String relativeOrderSysID) {
		this.relativeOrderSysID = relativeOrderSysID;
	}


	public int getZCETotalTradedVolume() {
		return zCETotalTradedVolume;
	}


	public void setZCETotalTradedVolume(int zCETotalTradedVolume) {
		this.zCETotalTradedVolume = zCETotalTradedVolume;
	}


	public int getIsSwapOrder() {
		return isSwapOrder;
	}


	public void setIsSwapOrder(int isSwapOrder) {
		this.isSwapOrder = isSwapOrder;
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
	public RtnOrder parseFrom(ByteBuf body, RspError error) {
		try {
			RtnOrder info = new RtnOrder();
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
			info.setOrderPriceType(FtdcOrderPriceType.parseFrom(StringUtils.trimToEmpty(new String(orderPriceType))));
			byte[] direction = new byte[1];
			body.readBytes(direction);
			info.setDirection(FtdcDirection.parseFrom(StringUtils.trimToEmpty(new String(direction))));
			byte[] combOffsetFlag = new byte[5];
			body.readBytes(combOffsetFlag);
			info.setCombOffsetFlag(FtdcOffsetFlagType.parseFrom(StringUtils.trimToEmpty(new String(combOffsetFlag))));
			byte[] combHedgeFlag = new byte[5];
			body.readBytes(combHedgeFlag);
			info.setCombHedgeFlag(FtdcBillHedgeFlag.parseFrom(StringUtils.trimToEmpty(new String(combHedgeFlag))));
			info.setLimitPrice(body.readDouble());
			info.setVolumeTotalOriginal(body.readInt());
			byte[] timeCondition = new byte[1];
			body.readBytes(timeCondition);
			info.setTimeCondition(FtdcTimeCondition.parseFrom(StringUtils.trimToEmpty(new String(timeCondition))));
			byte[] gTDDate = new byte[9];
			body.readBytes(gTDDate);
			info.setGTDDate(StringUtils.trimToEmpty(new String(gTDDate)));
			byte[] volumeCondition = new byte[1];
			body.readBytes(volumeCondition);
			info.setVolumeCondition(
					FtdcVolumeCondition.parseFrom(StringUtils.trimToEmpty(new String(volumeCondition))));
			info.setMinVolume(body.readInt());
			byte[] contingentCondition = new byte[1];
			body.readBytes(contingentCondition);
			info.setContingentCondition(
					FtdcContingentCondition.parseFrom(StringUtils.trimToEmpty(new String(contingentCondition))));
			info.setStopPrice(body.readDouble());
			byte[] forceCloseReason = new byte[1];
			body.readBytes(forceCloseReason);
			info.setForceCloseReason(
					FtdcForceCLoseReson.parseFrom(StringUtils.trimToEmpty(new String(forceCloseReason))));
			info.setIsAutoSuspend(body.readInt());
			byte[] businessUnit = new byte[21];
			body.readBytes(businessUnit);
			info.setBusinessUnit(StringUtils.trimToEmpty(new String(businessUnit)));
			info.setRequestID(body.readInt());
			byte[] orderLocalID = new byte[13];
			body.readBytes(orderLocalID);
			info.setOrderLocalID(StringUtils.trimToEmpty(new String(orderLocalID)));
			byte[] exchangeID = new byte[9];
			body.readBytes(exchangeID);
			info.setExchangeID(FtdcExchange.parseFrom(StringUtils.trimToEmpty(new String(exchangeID))));
			byte[] participantID = new byte[11];
			body.readBytes(participantID);
			info.setParticipantID(StringUtils.trimToEmpty(new String(participantID)));
			byte[] clientID = new byte[11];
			body.readBytes(clientID);
			info.setClientID(StringUtils.trimToEmpty(new String(clientID)));
			byte[] exchangeInstID = new byte[31];
			body.readBytes(exchangeInstID);
			info.setExchangeInstID(StringUtils.trimToEmpty(new String(exchangeInstID)));
			byte[] traderID = new byte[21];
			body.readBytes(traderID);
			info.setTraderID(StringUtils.trimToEmpty(new String(traderID)));
			info.setInstallID(body.readInt());
			byte[] orderSubmitStatus = new byte[1];
			body.readBytes(orderSubmitStatus);
			info.setOrderSubmitStatus(
					FtdcOrderSubmitStatusType.parseFrom(StringUtils.trimToEmpty(new String(orderSubmitStatus))));
			info.setNotifySequence(body.readInt());
			byte[] tradingDay = new byte[9];
			body.readBytes(tradingDay);
			info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));
			info.setSettlementID(body.readInt());
			byte[] orderSysID = new byte[21];
			body.readBytes(orderSysID);
			info.setOrderSysID(StringUtils.trimToEmpty(new String(orderSysID)));
			byte[] orderSource = new byte[1];
			body.readBytes(orderSource);
			info.setOrderSource(FtdcOrderSourceType.parseFrom(StringUtils.trimToEmpty(new String(orderSource))));
			byte[] orderStatus = new byte[1];
			body.readBytes(orderStatus);
			info.setOrderStatus(FtdcOrderStatusType.parseFrom(StringUtils.trimToEmpty(new String(orderStatus))));
			byte[] orderType = new byte[1];
			body.readBytes(orderType);
			info.setOrderType(FtdcOrderType.parseFrom(StringUtils.trimToEmpty(new String(orderType))));
			info.setVolumeTraded(body.readInt());
			info.setVolumeTotal(body.readInt());
			byte[] insertDate = new byte[9];
			body.readBytes(insertDate);
			info.setInsertDate(StringUtils.trimToEmpty(new String(insertDate)));
			byte[] insertTime = new byte[9];
			body.readBytes(insertTime);
			info.setInsertTime(StringUtils.trimToEmpty(new String(insertTime)));
			byte[] activeTime = new byte[9];
			body.readBytes(activeTime);
			info.setActiveTime(StringUtils.trimToEmpty(new String(activeTime)));
			byte[] suspendTime = new byte[9];
			body.readBytes(suspendTime);
			info.setSuspendTime(StringUtils.trimToEmpty(new String(suspendTime)));
			byte[] updateTime = new byte[9];
			body.readBytes(updateTime);
			info.setUpdateTime(StringUtils.trimToEmpty(new String(updateTime)));
			byte[] cancelTime = new byte[9];
			body.readBytes(cancelTime);
			info.setCancelTime(StringUtils.trimToEmpty(new String(cancelTime)));
			byte[] activeTraderID = new byte[21];
			body.readBytes(activeTraderID);
			info.setActiveTraderID(StringUtils.trimToEmpty(new String(activeTraderID)));
			byte[] clearingPartID = new byte[11];
			body.readBytes(clearingPartID);
			info.setClearingPartID(StringUtils.trimToEmpty(new String(clearingPartID)));
			info.setSequenceNo(body.readInt());
			info.setFrontID(body.readInt());
			info.setSessionID(body.readInt());
			byte[] userProductInfo = new byte[11];
			body.readBytes(userProductInfo);
			info.setUserProductInfo(StringUtils.trimToEmpty(new String(userProductInfo)));
			byte[] statusMsg = new byte[81];
			body.readBytes(statusMsg);
			try {
				info.setStatusMsg(StringUtils.trimToEmpty(new String(statusMsg, ApplicationRuntime.conf().defaultEncoding())));
			} catch (UnsupportedEncodingException e) {
				// ignore
			}
			info.setUserForceClose(body.readInt());
			byte[] activeUserID = new byte[16];
			body.readBytes(activeUserID);
			info.setActiveUserID(StringUtils.trimToEmpty(new String(activeUserID)));
			info.setBrokerOrderSeq(body.readInt());
			byte[] relativeOrderSysID = new byte[21];
			body.readBytes(relativeOrderSysID);
			info.setRelativeOrderSysID(StringUtils.trimToEmpty(new String(relativeOrderSysID)));
			info.setZCETotalTradedVolume(body.readInt());
			info.setIsSwapOrder(body.readInt());
			byte[] branchID = new byte[9];
			body.readBytes(branchID);
			info.setBranchID(StringUtils.trimToEmpty(new String(branchID)));
			byte[] investUnitID = new byte[17];
			body.readBytes(investUnitID);
			info.setInvestUnitID(StringUtils.trimToEmpty(new String(investUnitID)));
			byte[] accountID = new byte[13];
			body.readBytes(accountID);
			info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));
			info.setCurrencyID(body.readInt());
			byte[] iPAddress = new byte[16];
			body.readBytes(iPAddress);
			info.setIPAddress(StringUtils.trimToEmpty(new String(iPAddress)));
			byte[] macAddress = new byte[21];
			body.readBytes(macAddress);
			info.setMacAddress(StringUtils.trimToEmpty(new String(macAddress)));
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}


	@Override
	public String toString() {
		return "RtnOrder [brokerID=" + brokerID + ", investorID=" + investorID + ", instrumentID=" + instrumentID
				+ ", orderRef=" + orderRef + ", userID=" + userID + ", orderPriceType=" + orderPriceType
				+ ", direction=" + direction + ", combOffsetFlag=" + combOffsetFlag + ", combHedgeFlag=" + combHedgeFlag
				+ ", limitPrice=" + limitPrice + ", volumeTotalOriginal=" + volumeTotalOriginal + ", timeCondition="
				+ timeCondition + ", gTDDate=" + gTDDate + ", volumeCondition=" + volumeCondition + ", minVolume="
				+ minVolume + ", contingentCondition=" + contingentCondition + ", stopPrice=" + stopPrice
				+ ", forceCloseReason=" + forceCloseReason + ", isAutoSuspend=" + isAutoSuspend + ", businessUnit="
				+ businessUnit + ", requestID=" + requestID + ", orderLocalID=" + orderLocalID + ", exchangeID="
				+ exchangeID + ", participantID=" + participantID + ", clientID=" + clientID + ", exchangeInstID="
				+ exchangeInstID + ", traderID=" + traderID + ", installID=" + installID + ", orderSubmitStatus="
				+ orderSubmitStatus + ", notifySequence=" + notifySequence + ", tradingDay=" + tradingDay
				+ ", settlementID=" + settlementID + ", orderSysID=" + orderSysID + ", orderSource=" + orderSource
				+ ", orderStatus=" + orderStatus + ", orderType=" + orderType + ", volumeTraded=" + volumeTraded
				+ ", volumeTotal=" + volumeTotal + ", insertDate=" + insertDate + ", insertTime=" + insertTime
				+ ", activeTime=" + activeTime + ", suspendTime=" + suspendTime + ", updateTime=" + updateTime
				+ ", cancelTime=" + cancelTime + ", activeTraderID=" + activeTraderID + ", clearingPartID="
				+ clearingPartID + ", sequenceNo=" + sequenceNo + ", frontID=" + frontID + ", sessionID=" + sessionID
				+ ", userProductInfo=" + userProductInfo + ", statusMsg=" + statusMsg + ", userForceClose="
				+ userForceClose + ", activeUserID=" + activeUserID + ", brokerOrderSeq=" + brokerOrderSeq
				+ ", relativeOrderSysID=" + relativeOrderSysID + ", zCETotalTradedVolume=" + zCETotalTradedVolume
				+ ", isSwapOrder=" + isSwapOrder + ", branchID=" + branchID + ", investUnitID=" + investUnitID
				+ ", accountID=" + accountID + ", currencyID=" + currencyID + ", iPAddress=" + iPAddress
				+ ", macAddress=" + macAddress + "]";
	}
	
	
}
