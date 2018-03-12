package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcInvestorRangeType;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:36
 *
 */
public class RspQryCommissionRate implements FtdcRsp{
	// 31
	private String instrumentID;
	// 1
	private FtdcInvestorRangeType investorRange;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 8
	private double openRatioByMoney;
	// 8
	private double openRatioByVolume;
	// 8
	private double closeRatioByMoney;
	// 8
	private double closeRatioByVolume;
	// 8
	private double closeTodayRatioByMoney;
	// 8
	private double closeTodayRatioByVolume;

	
	public String getInstrumentID() {
		return instrumentID;
	}


	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}


	public FtdcInvestorRangeType getInvestorRange() {
		return investorRange;
	}


	public void setInvestorRange(FtdcInvestorRangeType investorRange) {
		this.investorRange = investorRange;
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


	public double getOpenRatioByMoney() {
		return openRatioByMoney;
	}


	public void setOpenRatioByMoney(double openRatioByMoney) {
		this.openRatioByMoney = openRatioByMoney;
	}


	public double getOpenRatioByVolume() {
		return openRatioByVolume;
	}


	public void setOpenRatioByVolume(double openRatioByVolume) {
		this.openRatioByVolume = openRatioByVolume;
	}


	public double getCloseRatioByMoney() {
		return closeRatioByMoney;
	}


	public void setCloseRatioByMoney(double closeRatioByMoney) {
		this.closeRatioByMoney = closeRatioByMoney;
	}


	public double getCloseRatioByVolume() {
		return closeRatioByVolume;
	}


	public void setCloseRatioByVolume(double closeRatioByVolume) {
		this.closeRatioByVolume = closeRatioByVolume;
	}


	public double getCloseTodayRatioByMoney() {
		return closeTodayRatioByMoney;
	}


	public void setCloseTodayRatioByMoney(double closeTodayRatioByMoney) {
		this.closeTodayRatioByMoney = closeTodayRatioByMoney;
	}


	public double getCloseTodayRatioByVolume() {
		return closeTodayRatioByVolume;
	}


	public void setCloseTodayRatioByVolume(double closeTodayRatioByVolume) {
		this.closeTodayRatioByVolume = closeTodayRatioByVolume;
	}

	@Override
	public RspQryCommissionRate parseFrom(ByteBuf body, RspError error) {
		RspQryCommissionRate info = new RspQryCommissionRate();
		byte[] instrumentID = new byte[31];
		body.readBytes(instrumentID);
		info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));

		byte[] investorRange = new byte[1];
		body.readBytes(investorRange);
		info.setInvestorRange(FtdcInvestorRangeType.parseFrom(StringUtils.trimToEmpty(new String(investorRange))));

		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] investorID = new byte[13];
		body.readBytes(investorID);
		info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));

		info.setOpenRatioByMoney(body.readDouble());

		info.setOpenRatioByVolume(body.readDouble());

		info.setCloseRatioByMoney(body.readDouble());

		info.setCloseRatioByVolume(body.readDouble());

		info.setCloseTodayRatioByMoney(body.readDouble());

		info.setCloseTodayRatioByVolume(body.readDouble());

		return info;
	}


	@Override
	public String toString() {
		return "RspQryCommissionRate [instrumentID=" + instrumentID + ", investorRange=" + investorRange + ", brokerID="
				+ brokerID + ", investorID=" + investorID + ", openRatioByMoney=" + openRatioByMoney
				+ ", openRatioByVolume=" + openRatioByVolume + ", closeRatioByMoney=" + closeRatioByMoney
				+ ", closeRatioByVolume=" + closeRatioByVolume + ", closeTodayRatioByMoney=" + closeTodayRatioByMoney
				+ ", closeTodayRatioByVolume=" + closeTodayRatioByVolume + "]";
	}
	
	
}
