package com.ee.ctp.dto;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:01
 *
 */
public class RspSettlementInfo implements FtdcRsp{
	
	public static final SequenceComparator DEFAULT_COMPARATOR = new SequenceComparator();
	
	//9
	private String tradingDay;
	//4
	private int settlementID;
	//11
	private String brokerID;
	//13
	private String investorID;
	//4
	private int sequenceNo;
	//501
	private byte[] content;
	
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
	public int getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Override
	public RspSettlementInfo parseFrom(ByteBuf body, RspError error) {
		try {
			RspSettlementInfo info = new RspSettlementInfo();
			byte[] tradingDay = new byte[9];
			body.readBytes(tradingDay);
			info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));
			info.setSettlementID(body.readInt());
			byte[] brokerID = new byte[11];
			body.readBytes(brokerID);
			info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));
			byte[] investorID = new byte[13];
			body.readBytes(investorID);
			info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));
			info.setSequenceNo(body.readInt());
			byte[] content = new byte[501];
			body.readBytes(content);
			info.setContent(content);
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}
	
	@Override
	public String toString() {
		return "RspSettlementInfo [tradingDay=" + tradingDay + ", settlementID=" + settlementID + ", brokerID="
				+ brokerID + ", investorID=" + investorID + ", sequenceNo=" + sequenceNo + "]";
	}
	
	static class SequenceComparator implements Comparator<RspSettlementInfo> {

		@Override
		public int compare(RspSettlementInfo o1, RspSettlementInfo o2) {
			return Integer.compare(o1.getSequenceNo(), o2.getSequenceNo());
		}
		
	}
}
