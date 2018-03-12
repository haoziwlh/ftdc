package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcContingentCondition;
import com.ee.ctp.enums.business.FtdcCurrencyID;
import com.ee.ctp.enums.business.FtdcDirection;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.enums.business.FtdcForceCLoseReson;
import com.ee.ctp.enums.business.FtdcOffsetFlagType;
import com.ee.ctp.enums.business.FtdcOrderPriceType;
import com.ee.ctp.enums.business.FtdcTimeCondition;
import com.ee.ctp.enums.business.FtdcVolumeCondition;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:58
 *
 */
public class ReqInputOrder implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] instrumentID = new byte[31];
	private byte[] orderRef = new byte[13];
	private byte[] userID = new byte[16];
	private byte[] orderPriceType = new byte[1];
	private byte[] direction = new byte[1];
	private byte[] combOffsetFlag = new byte[5];
	private byte[] combHedgeFlag = new byte[5];
	private double limitPrice;
	private int volumeTotalOriginal;
	private byte[] timeCondition = new byte[1];
	private byte[] gTDDate = new byte[9];
	private byte[] volumeCondition = new byte[1];
	private int minVolume;
	private byte[] contingentCondition = new byte[1];
	private double stopPrice;
	private byte[] forceCloseReason = new byte[1];
	private int isAutoSuspend;
	private byte[] businessUnit = new byte[21];
	private int requestID;
	private int userForceClose;
	private int isSwapOrder;
	private byte[] exchangeID = new byte[9];
	private byte[] investUnitID = new byte[17];
	private byte[] accountID = new byte[13];
	private int currencyID;
	private byte[] clientID = new byte[11];
	private byte[] iPAddress = new byte[16];
	private byte[] macAddress = new byte[21];

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setInvestorID(String investorID) {
		if (StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}

	public void setInstrumentID(String instrumentID) {
		if (StringUtils.isNotEmpty(instrumentID)) {
			System.arraycopy(instrumentID.getBytes(), 0, this.instrumentID, 0, instrumentID.getBytes().length);
		}
	}

	public void setOrderRef(String orderRef) {
		if (StringUtils.isNotEmpty(orderRef)) {
			System.arraycopy(orderRef.getBytes(), 0, this.orderRef, 0, orderRef.getBytes().length);
		}
	}

	public void setUserID(String userID) {
		if (StringUtils.isNotEmpty(userID)) {
			System.arraycopy(userID.getBytes(), 0, this.userID, 0, userID.getBytes().length);
		}
	}

	public void setOrderPriceType(FtdcOrderPriceType orderPriceType) {
		if (orderPriceType != null) {
			System.arraycopy(orderPriceType.getType().getBytes(), 0, this.orderPriceType, 0,
					orderPriceType.getType().getBytes().length);
		}
	}

	public void setDirection(FtdcDirection direction) {
		if (direction != null) {
			System.arraycopy(direction.getDirection().getBytes(), 0, this.direction, 0,
					direction.getDirection().getBytes().length);
		}
	}

	public void setCombOffsetFlag(FtdcOffsetFlagType combOffsetFlag) {
		if (combOffsetFlag != null) {
			System.arraycopy(combOffsetFlag.getOffset().getBytes(), 0, this.combOffsetFlag, 0,
					combOffsetFlag.getOffset().getBytes().length);
		}
	}

	public void setCombHedgeFlag(FtdcBillHedgeFlag combHedgeFlag) {
		if (combHedgeFlag != null) {
			System.arraycopy(combHedgeFlag.getFlag().getBytes(), 0, this.combHedgeFlag, 0,
					combHedgeFlag.getFlag().getBytes().length);
		}
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public void setVolumeTotalOriginal(int volumeTotalOriginal) {
		this.volumeTotalOriginal = volumeTotalOriginal;
	}

	public void setTimeCondition(FtdcTimeCondition timeCondition) {
		if (timeCondition != null) {
			System.arraycopy(timeCondition.getCodition().getBytes(), 0, this.timeCondition, 0,
					timeCondition.getCodition().getBytes().length);
		}
	}

	public void setGTDDate(String gTDDate) {
		if (StringUtils.isNotEmpty(gTDDate)) {
			System.arraycopy(gTDDate.getBytes(), 0, this.gTDDate, 0, gTDDate.getBytes().length);
		}
	}

	public void setVolumeCondition(FtdcVolumeCondition volumeCondition) {
		if (volumeCondition != null) {
			System.arraycopy(volumeCondition.getvCondition().getBytes(), 0, this.volumeCondition, 0,
					volumeCondition.getvCondition().getBytes().length);
		}
	}

	public void setMinVolume(int minVolume) {
		this.minVolume = minVolume;
	}

	public void setContingentCondition(FtdcContingentCondition contingentCondition) {
		if (contingentCondition != null) {
			System.arraycopy(contingentCondition.getContingent().getBytes(), 0, this.contingentCondition, 0,
					contingentCondition.getContingent().getBytes().length);
		}
	}

	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public void setForceCloseReason(FtdcForceCLoseReson forceCloseReason) {
		if (forceCloseReason != null) {
			System.arraycopy(forceCloseReason.type().getBytes(), 0, this.forceCloseReason, 0,
					forceCloseReason.type().getBytes().length);
		}
	}

	public void setIsAutoSuspend(int isAutoSuspend) {
		this.isAutoSuspend = isAutoSuspend;
	}

	public void setBusinessUnit(String businessUnit) {
		if (StringUtils.isNotEmpty(businessUnit)) {
			System.arraycopy(businessUnit.getBytes(), 0, this.businessUnit, 0, businessUnit.getBytes().length);
		}
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public void setUserForceClose(int userForceClose) {
		this.userForceClose = userForceClose;
	}

	public void setIsSwapOrder(int isSwapOrder) {
		this.isSwapOrder = isSwapOrder;
	}

	public void setExchangeID(FtdcExchange exchangeID) {
		if (exchangeID != null) {
			System.arraycopy(exchangeID.getExchange().getBytes(), 0, this.exchangeID, 0,
					exchangeID.getExchange().getBytes().length);
		}
	}

	public void setInvestUnitID(String investUnitID) {
		if (StringUtils.isNotEmpty(investUnitID)) {
			System.arraycopy(investUnitID.getBytes(), 0, this.investUnitID, 0, investUnitID.getBytes().length);
		}
	}

	public void setAccountID(String accountID) {
		if (StringUtils.isNotEmpty(accountID)) {
			System.arraycopy(accountID.getBytes(), 0, this.accountID, 0, accountID.getBytes().length);
		}
	}

	public void setCurrencyID(FtdcCurrencyID currencyID) {
		if (currencyID != null) {
			this.currencyID = currencyID.getId();
		}
	}

	public void setClientID(String clientID) {
		if (StringUtils.isNotEmpty(clientID)) {
			System.arraycopy(clientID.getBytes(), 0, this.clientID, 0, clientID.getBytes().length);
		}
	}

	public void setIPAddress(String iPAddress) {
		if (StringUtils.isNotEmpty(iPAddress)) {
			System.arraycopy(iPAddress.getBytes(), 0, this.iPAddress, 0, iPAddress.getBytes().length);
		}
	}

	public void setMacAddress(String macAddress) {
		if (StringUtils.isNotEmpty(macAddress) && macAddress.getBytes().length <= this.macAddress.length) {
			System.arraycopy(macAddress.getBytes(), 0, this.macAddress, 0, macAddress.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(instrumentID);
		buffer.writeBytes(orderRef);
		buffer.writeBytes(userID);
		buffer.writeBytes(orderPriceType);
		buffer.writeBytes(direction);
		buffer.writeBytes(combOffsetFlag);
		buffer.writeBytes(combHedgeFlag);
		buffer.writeDouble(limitPrice);
		buffer.writeInt(volumeTotalOriginal);
		buffer.writeBytes(timeCondition);
		buffer.writeBytes(gTDDate);
		buffer.writeBytes(volumeCondition);
		buffer.writeInt(minVolume);
		buffer.writeBytes(contingentCondition);
		buffer.writeDouble(stopPrice);
		buffer.writeBytes(forceCloseReason);
		buffer.writeInt(isAutoSuspend);
		buffer.writeBytes(businessUnit);
		buffer.writeInt(requestID);
		buffer.writeInt(userForceClose);
		buffer.writeInt(isSwapOrder);
		buffer.writeBytes(exchangeID);
		buffer.writeBytes(investUnitID);
		buffer.writeBytes(accountID);
		buffer.writeInt(currencyID);
		buffer.writeBytes(clientID);
		buffer.writeBytes(iPAddress);
		buffer.writeBytes(macAddress);

		return buffer;
	}
}
