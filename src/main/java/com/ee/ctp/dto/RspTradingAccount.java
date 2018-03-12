package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:20:15
 *
 */
public class RspTradingAccount implements FtdcRsp{
	// 11
	private String brokerID;
	// 13
	private String accountID;
	// 8
	private double preMortgage;
	// 8
	private double preCredit;
	// 8
	private double preDeposit;
	// 8
	private double preBalance;
	// 8
	private double preMargin;
	// 8
	private double interestBase;
	// 8
	private double interest;
	// 8
	private double deposit;
	// 8
	private double withdraw;
	// 8
	private double frozenMargin;
	// 8
	private double frozenCash;
	// 8
	private double frozenCommission;
	// 8
	private double currMargin;
	// 8
	private double cashIn;
	// 8
	private double commission;
	// 8
	private double closeProfit;
	// 8
	private double positionProfit;
	// 8
	private double balance;
	// 8
	private double available;
	// 8
	private double withdrawQuota;
	// 8
	private double reserve;
	// 9
	private String tradingDay;
	// 4
	private int settlementID;
	// 8
	private double credit;
	// 8
	private double mortgage;
	// 8
	private double exchangeMargin;
	// 8
	private double deliveryMargin;
	// 8
	private double exchangeDeliveryMargin;
	// 8
	private double reserveBalance;
	// 4
	private String currencyID;
	// 8
	private double preFundMortgageIn;
	// 8
	private double preFundMortgageOut;
	// 8
	private double fundMortgageIn;
	// 8
	private double fundMortgageOut;
	// 8
	private double fundMortgageAvailable;
	// 8
	private double mortgageableFund;
	// 8
	private double specProductMargin;
	// 8
	private double specProductFrozenMargin;
	// 8
	private double specProductCommission;
	// 8
	private double specProductFrozenCommission;
	// 8
	private double specProductPositionProfit;
	// 8
	private double specProductCloseProfit;
	// 8
	private double specProductPositionProfitByAlg;
	// 8
	private double specProductExchangeMargin;

	public String getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public double getPreMortgage() {
		return preMortgage;
	}

	public void setPreMortgage(double preMortgage) {
		this.preMortgage = preMortgage;
	}

	public double getPreCredit() {
		return preCredit;
	}

	public void setPreCredit(double preCredit) {
		this.preCredit = preCredit;
	}

	public double getPreDeposit() {
		return preDeposit;
	}

	public void setPreDeposit(double preDeposit) {
		this.preDeposit = preDeposit;
	}

	public double getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(double preBalance) {
		this.preBalance = preBalance;
	}

	public double getPreMargin() {
		return preMargin;
	}

	public void setPreMargin(double preMargin) {
		this.preMargin = preMargin;
	}

	public double getInterestBase() {
		return interestBase;
	}

	public void setInterestBase(double interestBase) {
		this.interestBase = interestBase;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
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

	public double getCurrMargin() {
		return currMargin;
	}

	public void setCurrMargin(double currMargin) {
		this.currMargin = currMargin;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public double getWithdrawQuota() {
		return withdrawQuota;
	}

	public void setWithdrawQuota(double withdrawQuota) {
		this.withdrawQuota = withdrawQuota;
	}

	public double getReserve() {
		return reserve;
	}

	public void setReserve(double reserve) {
		this.reserve = reserve;
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

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getMortgage() {
		return mortgage;
	}

	public void setMortgage(double mortgage) {
		this.mortgage = mortgage;
	}

	public double getExchangeMargin() {
		return exchangeMargin;
	}

	public void setExchangeMargin(double exchangeMargin) {
		this.exchangeMargin = exchangeMargin;
	}

	public double getDeliveryMargin() {
		return deliveryMargin;
	}

	public void setDeliveryMargin(double deliveryMargin) {
		this.deliveryMargin = deliveryMargin;
	}

	public double getExchangeDeliveryMargin() {
		return exchangeDeliveryMargin;
	}

	public void setExchangeDeliveryMargin(double exchangeDeliveryMargin) {
		this.exchangeDeliveryMargin = exchangeDeliveryMargin;
	}

	public double getReserveBalance() {
		return reserveBalance;
	}

	public void setReserveBalance(double reserveBalance) {
		this.reserveBalance = reserveBalance;
	}

	public String getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}

	public double getPreFundMortgageIn() {
		return preFundMortgageIn;
	}

	public void setPreFundMortgageIn(double preFundMortgageIn) {
		this.preFundMortgageIn = preFundMortgageIn;
	}

	public double getPreFundMortgageOut() {
		return preFundMortgageOut;
	}

	public void setPreFundMortgageOut(double preFundMortgageOut) {
		this.preFundMortgageOut = preFundMortgageOut;
	}

	public double getFundMortgageIn() {
		return fundMortgageIn;
	}

	public void setFundMortgageIn(double fundMortgageIn) {
		this.fundMortgageIn = fundMortgageIn;
	}

	public double getFundMortgageOut() {
		return fundMortgageOut;
	}

	public void setFundMortgageOut(double fundMortgageOut) {
		this.fundMortgageOut = fundMortgageOut;
	}

	public double getFundMortgageAvailable() {
		return fundMortgageAvailable;
	}

	public void setFundMortgageAvailable(double fundMortgageAvailable) {
		this.fundMortgageAvailable = fundMortgageAvailable;
	}

	public double getMortgageableFund() {
		return mortgageableFund;
	}

	public void setMortgageableFund(double mortgageableFund) {
		this.mortgageableFund = mortgageableFund;
	}

	public double getSpecProductMargin() {
		return specProductMargin;
	}

	public void setSpecProductMargin(double specProductMargin) {
		this.specProductMargin = specProductMargin;
	}

	public double getSpecProductFrozenMargin() {
		return specProductFrozenMargin;
	}

	public void setSpecProductFrozenMargin(double specProductFrozenMargin) {
		this.specProductFrozenMargin = specProductFrozenMargin;
	}

	public double getSpecProductCommission() {
		return specProductCommission;
	}

	public void setSpecProductCommission(double specProductCommission) {
		this.specProductCommission = specProductCommission;
	}

	public double getSpecProductFrozenCommission() {
		return specProductFrozenCommission;
	}

	public void setSpecProductFrozenCommission(double specProductFrozenCommission) {
		this.specProductFrozenCommission = specProductFrozenCommission;
	}

	public double getSpecProductPositionProfit() {
		return specProductPositionProfit;
	}

	public void setSpecProductPositionProfit(double specProductPositionProfit) {
		this.specProductPositionProfit = specProductPositionProfit;
	}

	public double getSpecProductCloseProfit() {
		return specProductCloseProfit;
	}

	public void setSpecProductCloseProfit(double specProductCloseProfit) {
		this.specProductCloseProfit = specProductCloseProfit;
	}

	public double getSpecProductPositionProfitByAlg() {
		return specProductPositionProfitByAlg;
	}

	public void setSpecProductPositionProfitByAlg(double specProductPositionProfitByAlg) {
		this.specProductPositionProfitByAlg = specProductPositionProfitByAlg;
	}

	public double getSpecProductExchangeMargin() {
		return specProductExchangeMargin;
	}

	public void setSpecProductExchangeMargin(double specProductExchangeMargin) {
		this.specProductExchangeMargin = specProductExchangeMargin;
	}

	@Override
	public RspTradingAccount parseFrom(ByteBuf body, RspError error) {
		RspTradingAccount info = new RspTradingAccount();
		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] accountID = new byte[13];
		body.readBytes(accountID);
		info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));

		info.setPreMortgage(body.readDouble());

		info.setPreCredit(body.readDouble());

		info.setPreDeposit(body.readDouble());

		info.setPreBalance(body.readDouble());

		info.setPreMargin(body.readDouble());

		info.setInterestBase(body.readDouble());

		info.setInterest(body.readDouble());

		info.setDeposit(body.readDouble());

		info.setWithdraw(body.readDouble());

		info.setFrozenMargin(body.readDouble());

		info.setFrozenCash(body.readDouble());

		info.setFrozenCommission(body.readDouble());

		info.setCurrMargin(body.readDouble());

		info.setCashIn(body.readDouble());

		info.setCommission(body.readDouble());

		info.setCloseProfit(body.readDouble());

		info.setPositionProfit(body.readDouble());

		info.setBalance(body.readDouble());

		info.setAvailable(body.readDouble());

		info.setWithdrawQuota(body.readDouble());

		info.setReserve(body.readDouble());

		byte[] tradingDay = new byte[9];
		body.readBytes(tradingDay);
		info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));

		info.setSettlementID(body.readInt());

		info.setCredit(body.readDouble());

		info.setMortgage(body.readDouble());

		info.setExchangeMargin(body.readDouble());

		info.setDeliveryMargin(body.readDouble());

		info.setExchangeDeliveryMargin(body.readDouble());

		info.setReserveBalance(body.readDouble());
		
		byte[] currencyId = new byte[4];
		body.readBytes(currencyId);
		info.setCurrencyID(StringUtils.trimToEmpty(new String(currencyId)));

		info.setPreFundMortgageIn(body.readDouble());

		info.setPreFundMortgageOut(body.readDouble());

		info.setFundMortgageIn(body.readDouble());

		info.setFundMortgageOut(body.readDouble());

		info.setFundMortgageAvailable(body.readDouble());

		info.setMortgageableFund(body.readDouble());

		info.setSpecProductMargin(body.readDouble());

		info.setSpecProductFrozenMargin(body.readDouble());

		info.setSpecProductCommission(body.readDouble());

		info.setSpecProductFrozenCommission(body.readDouble());

		info.setSpecProductPositionProfit(body.readDouble());

		info.setSpecProductCloseProfit(body.readDouble());

		info.setSpecProductPositionProfitByAlg(body.readDouble());

		info.setSpecProductExchangeMargin(body.readDouble());

		return info;
	}

	@Override
	public String toString() {
		return "RspTradingAccount [brokerID=" + brokerID + ", accountID=" + accountID + ", preMortgage=" + preMortgage
				+ ", preCredit=" + preCredit + ", preDeposit=" + preDeposit + ", preBalance=" + preBalance
				+ ", preMargin=" + preMargin + ", interestBase=" + interestBase + ", interest=" + interest
				+ ", deposit=" + deposit + ", withdraw=" + withdraw + ", frozenMargin=" + frozenMargin + ", frozenCash="
				+ frozenCash + ", frozenCommission=" + frozenCommission + ", currMargin=" + currMargin + ", cashIn="
				+ cashIn + ", commission=" + commission + ", closeProfit=" + closeProfit + ", positionProfit="
				+ positionProfit + ", balance=" + balance + ", available=" + available + ", withdrawQuota="
				+ withdrawQuota + ", reserve=" + reserve + ", tradingDay=" + tradingDay + ", settlementID="
				+ settlementID + ", credit=" + credit + ", mortgage=" + mortgage + ", exchangeMargin=" + exchangeMargin
				+ ", deliveryMargin=" + deliveryMargin + ", exchangeDeliveryMargin=" + exchangeDeliveryMargin
				+ ", reserveBalance=" + reserveBalance + ", currencyID=" + currencyID + ", preFundMortgageIn="
				+ preFundMortgageIn + ", preFundMortgageOut=" + preFundMortgageOut + ", fundMortgageIn="
				+ fundMortgageIn + ", fundMortgageOut=" + fundMortgageOut + ", fundMortgageAvailable="
				+ fundMortgageAvailable + ", mortgageableFund=" + mortgageableFund + ", specProductMargin="
				+ specProductMargin + ", specProductFrozenMargin=" + specProductFrozenMargin
				+ ", specProductCommission=" + specProductCommission + ", specProductFrozenCommission="
				+ specProductFrozenCommission + ", specProductPositionProfit=" + specProductPositionProfit
				+ ", specProductCloseProfit=" + specProductCloseProfit + ", specProductPositionProfitByAlg="
				+ specProductPositionProfitByAlg + ", specProductExchangeMargin=" + specProductExchangeMargin + "]";
	}
	
	
}
