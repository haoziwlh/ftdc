package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcInvestorRangeType;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:46
 *
 */
public class RspQryMarginRate implements FtdcRsp{
	// 31
	private String instrumentID;
	// 1
	private FtdcInvestorRangeType investorRange;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 1
	private FtdcBillHedgeFlag hedgeFlag;
	// 8
	private double longMarginRatioByMoney;
	// 8
	private double longMarginRatioByVolume;
	// 8
	private double shortMarginRatioByMoney;
	// 8
	private double shortMarginRatioByVolume;
	// 4
	private int isRelative;

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

	public FtdcBillHedgeFlag getHedgeFlag() {
		return hedgeFlag;
	}

	public void setHedgeFlag(FtdcBillHedgeFlag hedgeFlag) {
		this.hedgeFlag = hedgeFlag;
	}

	public double getLongMarginRatioByMoney() {
		return longMarginRatioByMoney;
	}

	public void setLongMarginRatioByMoney(double longMarginRatioByMoney) {
		this.longMarginRatioByMoney = longMarginRatioByMoney;
	}

	public double getLongMarginRatioByVolume() {
		return longMarginRatioByVolume;
	}

	public void setLongMarginRatioByVolume(double longMarginRatioByVolume) {
		this.longMarginRatioByVolume = longMarginRatioByVolume;
	}

	public double getShortMarginRatioByMoney() {
		return shortMarginRatioByMoney;
	}

	public void setShortMarginRatioByMoney(double shortMarginRatioByMoney) {
		this.shortMarginRatioByMoney = shortMarginRatioByMoney;
	}

	public double getShortMarginRatioByVolume() {
		return shortMarginRatioByVolume;
	}

	public void setShortMarginRatioByVolume(double shortMarginRatioByVolume) {
		this.shortMarginRatioByVolume = shortMarginRatioByVolume;
	}

	public int getIsRelative() {
		return isRelative;
	}

	public void setIsRelative(int isRelative) {
		this.isRelative = isRelative;
	}

	@Override
	public RspQryMarginRate parseFrom(ByteBuf body, RspError error) {
		RspQryMarginRate info = new RspQryMarginRate();
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

		byte[] hedgeFlag = new byte[1];
		body.readBytes(hedgeFlag);
		info.setHedgeFlag(FtdcBillHedgeFlag.parseFrom(StringUtils.trimToEmpty(new String(hedgeFlag))));

		info.setLongMarginRatioByMoney(body.readDouble());

		info.setLongMarginRatioByVolume(body.readDouble());

		info.setShortMarginRatioByMoney(body.readDouble());

		info.setShortMarginRatioByVolume(body.readDouble());

		info.setIsRelative(body.readInt());

		return info;
	}

	@Override
	public String toString() {
		return "RspQryMarginRate [instrumentID=" + instrumentID + ", investorRange=" + investorRange + ", brokerID="
				+ brokerID + ", investorID=" + investorID + ", hedgeFlag=" + hedgeFlag + ", longMarginRatioByMoney="
				+ longMarginRatioByMoney + ", longMarginRatioByVolume=" + longMarginRatioByVolume
				+ ", shortMarginRatioByMoney=" + shortMarginRatioByMoney + ", shortMarginRatioByVolume="
				+ shortMarginRatioByVolume + ", isRelative=" + isRelative + "]";
	}
	
	
}
