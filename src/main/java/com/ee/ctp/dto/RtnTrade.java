package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcDirection;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.enums.business.FtdcOffsetFlagType;
import com.ee.ctp.enums.business.FtdcPriceSourceType;
import com.ee.ctp.enums.business.FtdcTradeSourceType;
import com.ee.ctp.enums.business.FtdcTradeType;
import com.ee.ctp.enums.business.FtdcTradingRoleType;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:46
 *
 */
public class RtnTrade implements FtdcRsp{
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
	// 9
	private FtdcExchange exchangeID;
	// 21
	private String tradeID;
	// 1
	private FtdcDirection direction;
	// 21
	private String orderSysID;
	// 11
	private String participantID;
	// 11
	private String clientID;
	// 1
	private FtdcTradingRoleType tradingRole;
	// 31
	private String exchangeInstID;
	// 1
	private FtdcOffsetFlagType offsetFlag;
	// 1
	private FtdcBillHedgeFlag hedgeFlag;
	// 8
	private double price;
	// 4
	private int volume;
	// 9
	private String tradeDate;
	// 9
	private String tradeTime;
	// 1
	private FtdcTradeType tradeType;
	// 1
	private FtdcPriceSourceType priceSource;
	// 21
	private String traderID;
	// 13
	private String orderLocalID;
	// 11
	private String clearingPartID;
	// 21
	private String businessUnit;
	// 4
	private int sequenceNo;
	// 9
	private String tradingDay;
	// 4
	private int settlementID;
	// 4
	private int brokerOrderSeq;
	// 1
	private FtdcTradeSourceType tradeSource;

	
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


	public FtdcExchange getExchangeID() {
		return exchangeID;
	}


	public void setExchangeID(FtdcExchange exchangeID) {
		this.exchangeID = exchangeID;
	}


	public String getTradeID() {
		return tradeID;
	}


	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}


	public FtdcDirection getDirection() {
		return direction;
	}


	public void setDirection(FtdcDirection direction) {
		this.direction = direction;
	}


	public String getOrderSysID() {
		return orderSysID;
	}


	public void setOrderSysID(String orderSysID) {
		this.orderSysID = orderSysID;
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


	public FtdcTradingRoleType getTradingRole() {
		return tradingRole;
	}


	public void setTradingRole(FtdcTradingRoleType tradingRole) {
		this.tradingRole = tradingRole;
	}


	public String getExchangeInstID() {
		return exchangeInstID;
	}


	public void setExchangeInstID(String exchangeInstID) {
		this.exchangeInstID = exchangeInstID;
	}


	public FtdcOffsetFlagType getOffsetFlag() {
		return offsetFlag;
	}


	public void setOffsetFlag(FtdcOffsetFlagType offsetFlag) {
		this.offsetFlag = offsetFlag;
	}


	public FtdcBillHedgeFlag getHedgeFlag() {
		return hedgeFlag;
	}


	public void setHedgeFlag(FtdcBillHedgeFlag hedgeFlag) {
		this.hedgeFlag = hedgeFlag;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public String getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}


	public String getTradeTime() {
		return tradeTime;
	}


	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}


	public FtdcTradeType getTradeType() {
		return tradeType;
	}


	public void setTradeType(FtdcTradeType tradeType) {
		this.tradeType = tradeType;
	}


	public FtdcPriceSourceType getPriceSource() {
		return priceSource;
	}


	public void setPriceSource(FtdcPriceSourceType priceSource) {
		this.priceSource = priceSource;
	}


	public String getTraderID() {
		return traderID;
	}


	public void setTraderID(String traderID) {
		this.traderID = traderID;
	}


	public String getOrderLocalID() {
		return orderLocalID;
	}


	public void setOrderLocalID(String orderLocalID) {
		this.orderLocalID = orderLocalID;
	}


	public String getClearingPartID() {
		return clearingPartID;
	}


	public void setClearingPartID(String clearingPartID) {
		this.clearingPartID = clearingPartID;
	}


	public String getBusinessUnit() {
		return businessUnit;
	}


	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}


	public int getSequenceNo() {
		return sequenceNo;
	}


	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
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


	public int getBrokerOrderSeq() {
		return brokerOrderSeq;
	}


	public void setBrokerOrderSeq(int brokerOrderSeq) {
		this.brokerOrderSeq = brokerOrderSeq;
	}


	public FtdcTradeSourceType getTradeSource() {
		return tradeSource;
	}


	public void setTradeSource(FtdcTradeSourceType tradeSource) {
		this.tradeSource = tradeSource;
	}

	@Override
	public RtnTrade parseFrom(ByteBuf body, RspError error) {
		try {
			RtnTrade info = new RtnTrade();
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
			byte[] exchangeID = new byte[9];
			body.readBytes(exchangeID);
			info.setExchangeID(FtdcExchange.parseFrom(StringUtils.trimToEmpty(new String(exchangeID))));
			byte[] tradeID = new byte[21];
			body.readBytes(tradeID);
			info.setTradeID(StringUtils.trimToEmpty(new String(tradeID)));
			byte[] direction = new byte[1];
			body.readBytes(direction);
			info.setDirection(FtdcDirection.parseFrom(StringUtils.trimToEmpty(new String(direction))));
			byte[] orderSysID = new byte[21];
			body.readBytes(orderSysID);
			info.setOrderSysID(StringUtils.trimToEmpty(new String(orderSysID)));
			byte[] participantID = new byte[11];
			body.readBytes(participantID);
			info.setParticipantID(StringUtils.trimToEmpty(new String(participantID)));
			byte[] clientID = new byte[11];
			body.readBytes(clientID);
			info.setClientID(StringUtils.trimToEmpty(new String(clientID)));
			byte[] tradingRole = new byte[1];
			body.readBytes(tradingRole);
			info.setTradingRole(FtdcTradingRoleType.parseFrom(StringUtils.trimToEmpty(new String(tradingRole))));
			byte[] exchangeInstID = new byte[31];
			body.readBytes(exchangeInstID);
			info.setExchangeInstID(StringUtils.trimToEmpty(new String(exchangeInstID)));
			byte[] offsetFlag = new byte[1];
			body.readBytes(offsetFlag);
			info.setOffsetFlag(FtdcOffsetFlagType.parseFrom(StringUtils.trimToEmpty(new String(offsetFlag))));
			byte[] hedgeFlag = new byte[1];
			body.readBytes(hedgeFlag);
			info.setHedgeFlag(FtdcBillHedgeFlag.parseFrom(StringUtils.trimToEmpty(new String(hedgeFlag))));
			info.setPrice(body.readDouble());
			info.setVolume(body.readInt());
			byte[] tradeDate = new byte[9];
			body.readBytes(tradeDate);
			info.setTradeDate(StringUtils.trimToEmpty(new String(tradeDate)));
			byte[] tradeTime = new byte[9];
			body.readBytes(tradeTime);
			info.setTradeTime(StringUtils.trimToEmpty(new String(tradeTime)));
			byte[] tradeType = new byte[1];
			body.readBytes(tradeType);
			info.setTradeType(FtdcTradeType.parseFrom(StringUtils.trimToEmpty(new String(tradeType))));
			byte[] priceSource = new byte[1];
			body.readBytes(priceSource);
			info.setPriceSource(FtdcPriceSourceType.parseFrom(StringUtils.trimToEmpty(new String(priceSource))));
			byte[] traderID = new byte[21];
			body.readBytes(traderID);
			info.setTraderID(StringUtils.trimToEmpty(new String(traderID)));
			byte[] orderLocalID = new byte[13];
			body.readBytes(orderLocalID);
			info.setOrderLocalID(StringUtils.trimToEmpty(new String(orderLocalID)));
			byte[] clearingPartID = new byte[11];
			body.readBytes(clearingPartID);
			info.setClearingPartID(StringUtils.trimToEmpty(new String(clearingPartID)));
			byte[] businessUnit = new byte[21];
			body.readBytes(businessUnit);
			info.setBusinessUnit(StringUtils.trimToEmpty(new String(businessUnit)));
			info.setSequenceNo(body.readInt());
			byte[] tradingDay = new byte[9];
			body.readBytes(tradingDay);
			info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));
			info.setSettlementID(body.readInt());
			info.setBrokerOrderSeq(body.readInt());
			byte[] tradeSource = new byte[1];
			body.readBytes(tradeSource);
			info.setTradeSource(FtdcTradeSourceType.parseFrom(StringUtils.trimToEmpty(new String(tradeSource))));
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}


	@Override
	public String toString() {
		return "RtnTrade [brokerID=" + brokerID + ", investorID=" + investorID + ", instrumentID=" + instrumentID
				+ ", orderRef=" + orderRef + ", userID=" + userID + ", exchangeID=" + exchangeID + ", tradeID="
				+ tradeID + ", direction=" + direction + ", orderSysID=" + orderSysID + ", participantID="
				+ participantID + ", clientID=" + clientID + ", tradingRole=" + tradingRole + ", exchangeInstID="
				+ exchangeInstID + ", offsetFlag=" + offsetFlag + ", hedgeFlag=" + hedgeFlag + ", price=" + price
				+ ", volume=" + volume + ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime + ", tradeType="
				+ tradeType + ", priceSource=" + priceSource + ", traderID=" + traderID + ", orderLocalID="
				+ orderLocalID + ", clearingPartID=" + clearingPartID + ", businessUnit=" + businessUnit
				+ ", sequenceNo=" + sequenceNo + ", tradingDay=" + tradingDay + ", settlementID=" + settlementID
				+ ", brokerOrderSeq=" + brokerOrderSeq + ", tradeSource=" + tradeSource + "]";
	}
	
	
}
