package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcBillHedgeFlag;
import com.ee.ctp.enums.business.FtdcPosiDirectionType;
import com.ee.ctp.enums.business.FtdcPositionDateType;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:25
 *
 */
public class RspInvestorPosition implements FtdcRsp{
	// 31
	private String instrumentID;
	// 11
	private String brokerID;
	// 13
	private String investorID;
	// 1
	private FtdcPosiDirectionType posiDirection;
	// 1
	private FtdcBillHedgeFlag hedgeFlag;
	// 1
	private FtdcPositionDateType positionDate;
	// 4
	private int ydPosition;
	// 4
	private int position;
	// 4
	private int longFrozen;
	// 4
	private int shortFrozen;
	// 8
	private double longFrozenAmount;
	// 8
	private double shortFrozenAmount;
	// 4
	private int openVolume;
	// 4
	private int closeVolume;
	// 8
	private double openAmount;
	// 8
	private double closeAmount;
	// 8
	private double positionCost;
	// 8
	private double preMargin;
	// 8
	private double useMargin;
	// 8
	private double frozenMargin;
	// 8
	private double frozenCash;
	// 8
	private double frozenCommission;
	// 8
	private double cashIn;
	// 8
	private double commission;
	// 8
	private double closeProfit;
	// 8
	private double positionProfit;
	// 8
	private double preSettlementPrice;
	// 8
	private double settlementPrice;
	// 9
	private String tradingDay;
	// 4
	private int settlementID;
	// 8
	private double openCost;
	// 8
	private double exchangeMargin;
	// 4
	private int combPosition;
	// 4
	private int combLongFrozen;
	// 4
	private int combShortFrozen;
	// 8
	private double closeProfitByDate;
	// 8
	private double closeProfitByTrade;
	// 4
	private int todayPosition;
	// 8
	private double marginRateByMoney;
	// 8
	private double marginRateByVolume;
	// 4
	private int strikeFrozen;
	// 8
	private double strikeFrozenAmount;
	// 4
	private int abandonFrozen;

	
	public String getInstrumentID() {
		return instrumentID;
	}


	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
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


	public FtdcPosiDirectionType getPosiDirection() {
		return posiDirection;
	}


	public void setPosiDirection(FtdcPosiDirectionType posiDirection) {
		this.posiDirection = posiDirection;
	}


	public FtdcBillHedgeFlag getHedgeFlag() {
		return hedgeFlag;
	}


	public void setHedgeFlag(FtdcBillHedgeFlag hedgeFlag) {
		this.hedgeFlag = hedgeFlag;
	}


	public FtdcPositionDateType getPositionDate() {
		return positionDate;
	}


	public void setPositionDate(FtdcPositionDateType positionDate) {
		this.positionDate = positionDate;
	}


	public int getYdPosition() {
		return ydPosition;
	}


	public void setYdPosition(int ydPosition) {
		this.ydPosition = ydPosition;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public int getLongFrozen() {
		return longFrozen;
	}


	public void setLongFrozen(int longFrozen) {
		this.longFrozen = longFrozen;
	}


	public int getShortFrozen() {
		return shortFrozen;
	}


	public void setShortFrozen(int shortFrozen) {
		this.shortFrozen = shortFrozen;
	}


	public double getLongFrozenAmount() {
		return longFrozenAmount;
	}


	public void setLongFrozenAmount(double longFrozenAmount) {
		this.longFrozenAmount = longFrozenAmount;
	}


	public double getShortFrozenAmount() {
		return shortFrozenAmount;
	}


	public void setShortFrozenAmount(double shortFrozenAmount) {
		this.shortFrozenAmount = shortFrozenAmount;
	}


	public int getOpenVolume() {
		return openVolume;
	}


	public void setOpenVolume(int openVolume) {
		this.openVolume = openVolume;
	}


	public int getCloseVolume() {
		return closeVolume;
	}


	public void setCloseVolume(int closeVolume) {
		this.closeVolume = closeVolume;
	}


	public double getOpenAmount() {
		return openAmount;
	}


	public void setOpenAmount(double openAmount) {
		this.openAmount = openAmount;
	}


	public double getCloseAmount() {
		return closeAmount;
	}


	public void setCloseAmount(double closeAmount) {
		this.closeAmount = closeAmount;
	}


	public double getPositionCost() {
		return positionCost;
	}


	public void setPositionCost(double positionCost) {
		this.positionCost = positionCost;
	}


	public double getPreMargin() {
		return preMargin;
	}


	public void setPreMargin(double preMargin) {
		this.preMargin = preMargin;
	}


	public double getUseMargin() {
		return useMargin;
	}


	public void setUseMargin(double useMargin) {
		this.useMargin = useMargin;
	}


	public double getFrozenMargin() {
		return frozenMargin;
	}


	public void setFrozenMargin(double frozenMargin) {
		this.frozenMargin = frozenMargin;
	}


	public double getFrozenCash() {
		return frozenCash;
	}


	public void setFrozenCash(double frozenCash) {
		this.frozenCash = frozenCash;
	}


	public double getFrozenCommission() {
		return frozenCommission;
	}


	public void setFrozenCommission(double frozenCommission) {
		this.frozenCommission = frozenCommission;
	}


	public double getCashIn() {
		return cashIn;
	}


	public void setCashIn(double cashIn) {
		this.cashIn = cashIn;
	}


	public double getCommission() {
		return commission;
	}


	public void setCommission(double commission) {
		this.commission = commission;
	}


	public double getCloseProfit() {
		return closeProfit;
	}


	public void setCloseProfit(double closeProfit) {
		this.closeProfit = closeProfit;
	}


	public double getPositionProfit() {
		return positionProfit;
	}


	public void setPositionProfit(double positionProfit) {
		this.positionProfit = positionProfit;
	}


	public double getPreSettlementPrice() {
		return preSettlementPrice;
	}


	public void setPreSettlementPrice(double preSettlementPrice) {
		this.preSettlementPrice = preSettlementPrice;
	}


	public double getSettlementPrice() {
		return settlementPrice;
	}


	public void setSettlementPrice(double settlementPrice) {
		this.settlementPrice = settlementPrice;
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


	public double getOpenCost() {
		return openCost;
	}


	public void setOpenCost(double openCost) {
		this.openCost = openCost;
	}


	public double getExchangeMargin() {
		return exchangeMargin;
	}


	public void setExchangeMargin(double exchangeMargin) {
		this.exchangeMargin = exchangeMargin;
	}


	public int getCombPosition() {
		return combPosition;
	}


	public void setCombPosition(int combPosition) {
		this.combPosition = combPosition;
	}


	public int getCombLongFrozen() {
		return combLongFrozen;
	}


	public void setCombLongFrozen(int combLongFrozen) {
		this.combLongFrozen = combLongFrozen;
	}


	public int getCombShortFrozen() {
		return combShortFrozen;
	}


	public void setCombShortFrozen(int combShortFrozen) {
		this.combShortFrozen = combShortFrozen;
	}


	public double getCloseProfitByDate() {
		return closeProfitByDate;
	}


	public void setCloseProfitByDate(double closeProfitByDate) {
		this.closeProfitByDate = closeProfitByDate;
	}


	public double getCloseProfitByTrade() {
		return closeProfitByTrade;
	}


	public void setCloseProfitByTrade(double closeProfitByTrade) {
		this.closeProfitByTrade = closeProfitByTrade;
	}


	public int getTodayPosition() {
		return todayPosition;
	}


	public void setTodayPosition(int todayPosition) {
		this.todayPosition = todayPosition;
	}


	public double getMarginRateByMoney() {
		return marginRateByMoney;
	}


	public void setMarginRateByMoney(double marginRateByMoney) {
		this.marginRateByMoney = marginRateByMoney;
	}


	public double getMarginRateByVolume() {
		return marginRateByVolume;
	}


	public void setMarginRateByVolume(double marginRateByVolume) {
		this.marginRateByVolume = marginRateByVolume;
	}


	public int getStrikeFrozen() {
		return strikeFrozen;
	}


	public void setStrikeFrozen(int strikeFrozen) {
		this.strikeFrozen = strikeFrozen;
	}


	public double getStrikeFrozenAmount() {
		return strikeFrozenAmount;
	}


	public void setStrikeFrozenAmount(double strikeFrozenAmount) {
		this.strikeFrozenAmount = strikeFrozenAmount;
	}


	public int getAbandonFrozen() {
		return abandonFrozen;
	}


	public void setAbandonFrozen(int abandonFrozen) {
		this.abandonFrozen = abandonFrozen;
	}

	@Override
	public RspInvestorPosition parseFrom(ByteBuf body, RspError error) {
		try {
			RspInvestorPosition info = new RspInvestorPosition();
			byte[] instrumentID = new byte[31];
			body.readBytes(instrumentID);
			info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));
			byte[] brokerID = new byte[11];
			body.readBytes(brokerID);
			info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));
			byte[] investorID = new byte[13];
			body.readBytes(investorID);
			info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));
			byte[] posiDirection = new byte[1];
			body.readBytes(posiDirection);
			info.setPosiDirection(FtdcPosiDirectionType.parseFrom(StringUtils.trimToEmpty(new String(posiDirection))));
			byte[] hedgeFlag = new byte[1];
			body.readBytes(hedgeFlag);
			info.setHedgeFlag(FtdcBillHedgeFlag.parseFrom(StringUtils.trimToEmpty(new String(hedgeFlag))));
			byte[] positionDate = new byte[1];
			body.readBytes(positionDate);
			info.setPositionDate(FtdcPositionDateType.parseFrom(StringUtils.trimToEmpty(new String(positionDate))));
			info.setYdPosition(body.readInt());
			info.setPosition(body.readInt());
			info.setLongFrozen(body.readInt());
			info.setShortFrozen(body.readInt());
			info.setLongFrozenAmount(body.readDouble());
			info.setShortFrozenAmount(body.readDouble());
			info.setOpenVolume(body.readInt());
			info.setCloseVolume(body.readInt());
			info.setOpenAmount(body.readDouble());
			info.setCloseAmount(body.readDouble());
			info.setPositionCost(body.readDouble());
			info.setPreMargin(body.readDouble());
			info.setUseMargin(body.readDouble());
			info.setFrozenMargin(body.readDouble());
			info.setFrozenCash(body.readDouble());
			info.setFrozenCommission(body.readDouble());
			info.setCashIn(body.readDouble());
			info.setCommission(body.readDouble());
			info.setCloseProfit(body.readDouble());
			info.setPositionProfit(body.readDouble());
			info.setPreSettlementPrice(body.readDouble());
			info.setSettlementPrice(body.readDouble());
			byte[] tradingDay = new byte[9];
			body.readBytes(tradingDay);
			info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));
			info.setSettlementID(body.readInt());
			info.setOpenCost(body.readDouble());
			info.setExchangeMargin(body.readDouble());
			info.setCombPosition(body.readInt());
			info.setCombLongFrozen(body.readInt());
			info.setCombShortFrozen(body.readInt());
			info.setCloseProfitByDate(body.readDouble());
			info.setCloseProfitByTrade(body.readDouble());
			info.setTodayPosition(body.readInt());
			info.setMarginRateByMoney(body.readDouble());
			info.setMarginRateByVolume(body.readDouble());
			info.setStrikeFrozen(body.readInt());
			info.setStrikeFrozenAmount(body.readDouble());
			info.setAbandonFrozen(body.readInt());
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}


	@Override
	public String toString() {
		return "RspInvestorPosition [instrumentID=" + instrumentID + ", brokerID=" + brokerID + ", investorID="
				+ investorID + ", posiDirection=" + posiDirection + ", hedgeFlag=" + hedgeFlag + ", positionDate="
				+ positionDate + ", ydPosition=" + ydPosition + ", position=" + position + ", longFrozen=" + longFrozen
				+ ", shortFrozen=" + shortFrozen + ", longFrozenAmount=" + longFrozenAmount + ", shortFrozenAmount="
				+ shortFrozenAmount + ", openVolume=" + openVolume + ", closeVolume=" + closeVolume + ", openAmount="
				+ openAmount + ", closeAmount=" + closeAmount + ", positionCost=" + positionCost + ", preMargin="
				+ preMargin + ", useMargin=" + useMargin + ", frozenMargin=" + frozenMargin + ", frozenCash="
				+ frozenCash + ", frozenCommission=" + frozenCommission + ", cashIn=" + cashIn + ", commission="
				+ commission + ", closeProfit=" + closeProfit + ", positionProfit=" + positionProfit
				+ ", preSettlementPrice=" + preSettlementPrice + ", settlementPrice=" + settlementPrice
				+ ", tradingDay=" + tradingDay + ", settlementID=" + settlementID + ", openCost=" + openCost
				+ ", exchangeMargin=" + exchangeMargin + ", combPosition=" + combPosition + ", combLongFrozen="
				+ combLongFrozen + ", combShortFrozen=" + combShortFrozen + ", closeProfitByDate=" + closeProfitByDate
				+ ", closeProfitByTrade=" + closeProfitByTrade + ", todayPosition=" + todayPosition
				+ ", marginRateByMoney=" + marginRateByMoney + ", marginRateByVolume=" + marginRateByVolume
				+ ", strikeFrozen=" + strikeFrozen + ", strikeFrozenAmount=" + strikeFrozenAmount + ", abandonFrozen="
				+ abandonFrozen + "]";
	}
	
	
}
