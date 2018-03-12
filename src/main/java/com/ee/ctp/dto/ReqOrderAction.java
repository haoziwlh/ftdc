package com.ee.ctp.dto;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcActionFlagType;
import com.ee.ctp.enums.business.FtdcExchange;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:58
 *
 */
public class ReqOrderAction implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private int orderActionRef;
	private byte[] orderRef = new byte[13];
	private int requestID;
	private int frontID;
	private int sessionID;
	private byte[] exchangeID = new byte[9];
	private byte[] orderSysID = new byte[21];
	private byte[] actionFlag = new byte[1];
	private double limitPrice;
	private int volumeChange;
	private byte[] userID = new byte[16];
	private byte[] instrumentID = new byte[31];
	private byte[] investUnitID = new byte[17];
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

	public void setOrderActionRef(int orderActionRef) {
		this.orderActionRef = orderActionRef;
	}

	public void setOrderRef(String orderRef) {
		if (StringUtils.isNotEmpty(orderRef)) {
			System.arraycopy(orderRef.getBytes(), 0, this.orderRef, 0, orderRef.getBytes().length);
		}
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public void setFrontID(int frontID) {
		this.frontID = frontID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public void setExchangeID(FtdcExchange exchangeID) {
		if (exchangeID != null) {
			System.arraycopy(exchangeID.getExchange().getBytes(), 0, this.exchangeID, 0,
					exchangeID.getExchange().getBytes().length);
		}
	}

	public void setOrderSysID(String orderSysID) {
		if (StringUtils.isNotEmpty(orderSysID)) {
			System.arraycopy(orderSysID.getBytes(), 0, this.orderSysID, 0, orderSysID.getBytes().length);
		}
	}

	public void setActionFlag(FtdcActionFlagType actionFlag) {
		if (actionFlag != null) {
			System.arraycopy(actionFlag.type().getBytes(), 0, this.actionFlag, 0, actionFlag.type().getBytes().length);
		}
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public void setVolumeChange(int volumeChange) {
		this.volumeChange = volumeChange;
	}

	public void setUserID(String userID) {
		if (StringUtils.isNotEmpty(userID)) {
			System.arraycopy(userID.getBytes(), 0, this.userID, 0, userID.getBytes().length);
		}
	}

	public void setInstrumentID(String instrumentID) {
		if (StringUtils.isNotEmpty(instrumentID)) {
			System.arraycopy(instrumentID.getBytes(), 0, this.instrumentID, 0, instrumentID.getBytes().length);
		}
	}

	public void setInvestUnitID(String investUnitID) {
		if (StringUtils.isNotEmpty(investUnitID)) {
			System.arraycopy(investUnitID.getBytes(), 0, this.investUnitID, 0, investUnitID.getBytes().length);
		}
	}

	public void setIPAddress(String iPAddress) {
		if (StringUtils.isNotEmpty(iPAddress)) {
			System.arraycopy(iPAddress.getBytes(), 0, this.iPAddress, 0, iPAddress.getBytes().length);
		}
	}

	public void setMacAddress(String macAddress) {
		if (StringUtils.isNotEmpty(macAddress)) {
			System.arraycopy(macAddress.getBytes(), 0, this.macAddress, 0, macAddress.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeInt(orderActionRef);
		buffer.writeBytes(orderRef);
		buffer.writeInt(requestID);
		buffer.writeInt(frontID);
		buffer.writeInt(sessionID);
		buffer.writeBytes(exchangeID);
		buffer.writeBytes(orderSysID);
		buffer.writeBytes(actionFlag);
		buffer.writeDouble(limitPrice);
		buffer.writeInt(volumeChange);
		buffer.writeBytes(userID);
		buffer.writeBytes(instrumentID);
		buffer.writeBytes(investUnitID);
		buffer.writeBytes(iPAddress);
		buffer.writeBytes(macAddress);

		return buffer;
	}
}
