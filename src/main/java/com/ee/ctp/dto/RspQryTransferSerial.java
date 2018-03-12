package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.enums.business.FtdcAvailabilityFlagType;
import com.ee.ctp.enums.business.FtdcBankAccType;
import com.ee.ctp.enums.business.FtdcFutureAccType;
import com.ee.ctp.enums.business.FtdcIdCardType;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:56
 *
 */
public class RspQryTransferSerial implements FtdcRsp{
	// 4
	private int plateSerial;
	// 9
	private String tradeDate;
	// 9
	private String tradingDay;
	// 9
	private String tradeTime;
	// 7
	private String tradeCode;
	// 4
	private int sessionID;
	// 4
	private String bankID;
	// 5
	private String bankBranchID;
	// 1
	private FtdcBankAccType bankAccType;
	// 41
	private String bankAccount;
	// 13
	private String bankSerial;
	// 11
	private String brokerID;
	// 31
	private String brokerBranchID;
	// 1
	private FtdcFutureAccType futureAccType;
	// 13
	private String accountID;
	// 13
	private String investorID;
	// 4
	private int futureSerial;
	// 1
	private FtdcIdCardType idCardType;
	// 51
	private String identifiedCardNo;
	// 4
	private String currencyID;
	// 8
	private double tradeAmount;
	// 8
	private double custFee;
	// 8
	private double brokerFee;
	// 1
	private FtdcAvailabilityFlagType availabilityFlag;
	// 17
	private String operatorCode;
	// 41
	private String bankNewAccount;
	// 4
	private int errorID;
	// 81
	private String errorMsg;

	public int getPlateSerial() {
		return plateSerial;
	}

	public void setPlateSerial(int plateSerial) {
		this.plateSerial = plateSerial;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradingDay() {
		return tradingDay;
	}

	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public String getBankBranchID() {
		return bankBranchID;
	}

	public void setBankBranchID(String bankBranchID) {
		this.bankBranchID = bankBranchID;
	}

	public FtdcBankAccType getBankAccType() {
		return bankAccType;
	}

	public void setBankAccType(FtdcBankAccType bankAccType) {
		this.bankAccType = bankAccType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankSerial() {
		return bankSerial;
	}

	public void setBankSerial(String bankSerial) {
		this.bankSerial = bankSerial;
	}

	public String getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	public String getBrokerBranchID() {
		return brokerBranchID;
	}

	public void setBrokerBranchID(String brokerBranchID) {
		this.brokerBranchID = brokerBranchID;
	}

	public FtdcFutureAccType getFutureAccType() {
		return futureAccType;
	}

	public void setFutureAccType(FtdcFutureAccType futureAccType) {
		this.futureAccType = futureAccType;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getInvestorID() {
		return investorID;
	}

	public void setInvestorID(String investorID) {
		this.investorID = investorID;
	}

	public int getFutureSerial() {
		return futureSerial;
	}

	public void setFutureSerial(int futureSerial) {
		this.futureSerial = futureSerial;
	}

	public FtdcIdCardType getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(FtdcIdCardType idCardType) {
		this.idCardType = idCardType;
	}

	public String getIdentifiedCardNo() {
		return identifiedCardNo;
	}

	public void setIdentifiedCardNo(String identifiedCardNo) {
		this.identifiedCardNo = identifiedCardNo;
	}

	public String getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public double getCustFee() {
		return custFee;
	}

	public void setCustFee(double custFee) {
		this.custFee = custFee;
	}

	public double getBrokerFee() {
		return brokerFee;
	}

	public void setBrokerFee(double brokerFee) {
		this.brokerFee = brokerFee;
	}

	public FtdcAvailabilityFlagType getAvailabilityFlag() {
		return availabilityFlag;
	}

	public void setAvailabilityFlag(FtdcAvailabilityFlagType availabilityFlag) {
		this.availabilityFlag = availabilityFlag;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getBankNewAccount() {
		return bankNewAccount;
	}

	public void setBankNewAccount(String bankNewAccount) {
		this.bankNewAccount = bankNewAccount;
	}

	public int getErrorID() {
		return errorID;
	}

	public void setErrorID(int errorID) {
		this.errorID = errorID;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public RspQryTransferSerial parseFrom(ByteBuf body, RspError error) {
		RspQryTransferSerial info = new RspQryTransferSerial();

		info.setPlateSerial(body.readInt());

		byte[] tradeDate = new byte[9];
		body.readBytes(tradeDate);
		info.setTradeDate(StringUtils.trimToEmpty(new String(tradeDate)));

		byte[] tradingDay = new byte[9];
		body.readBytes(tradingDay);
		info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));

		byte[] tradeTime = new byte[9];
		body.readBytes(tradeTime);
		info.setTradeTime(StringUtils.trimToEmpty(new String(tradeTime)));

		byte[] tradeCode = new byte[7];
		body.readBytes(tradeCode);
		info.setTradeCode(StringUtils.trimToEmpty(new String(tradeCode)));

		info.setSessionID(body.readInt());

		byte[] bankId = new byte[4];
		body.readBytes(bankId);
		info.setBankID(StringUtils.trimToEmpty(new String(bankId)));

		byte[] bankBranchID = new byte[5];
		body.readBytes(bankBranchID);
		info.setBankBranchID(StringUtils.trimToEmpty(new String(bankBranchID)));

		byte[] bankAccType = new byte[1];
		body.readBytes(bankAccType);
		info.setBankAccType(FtdcBankAccType.parseFrom(StringUtils.trimToEmpty(new String(bankAccType))));

		byte[] bankAccount = new byte[41];
		body.readBytes(bankAccount);
		info.setBankAccount(StringUtils.trimToEmpty(new String(bankAccount)));

		byte[] bankSerial = new byte[13];
		body.readBytes(bankSerial);
		info.setBankSerial(StringUtils.trimToEmpty(new String(bankSerial)));

		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] brokerBranchID = new byte[31];
		body.readBytes(brokerBranchID);
		info.setBrokerBranchID(StringUtils.trimToEmpty(new String(brokerBranchID)));

		byte[] futureAccType = new byte[1];
		body.readBytes(futureAccType);
		info.setFutureAccType(FtdcFutureAccType.parseFrom(StringUtils.trimToEmpty(new String(futureAccType))));

		byte[] accountID = new byte[13];
		body.readBytes(accountID);
		info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));

		byte[] investorID = new byte[13];
		body.readBytes(investorID);
		info.setInvestorID(StringUtils.trimToEmpty(new String(investorID)));

		info.setFutureSerial(body.readInt());

		byte[] idCardType = new byte[1];
		body.readBytes(idCardType);
		info.setIdCardType(FtdcIdCardType.parseFrom(StringUtils.trimToEmpty(new String(idCardType))));

		byte[] identifiedCardNo = new byte[51];
		body.readBytes(identifiedCardNo);
		info.setIdentifiedCardNo(StringUtils.trimToEmpty(new String(identifiedCardNo)));

		byte[] currencyId = new byte[4];
		body.readBytes(currencyId);
		info.setCurrencyID(StringUtils.trimToEmpty(new String(currencyId)));

		info.setTradeAmount(body.readDouble());

		info.setCustFee(body.readDouble());

		info.setBrokerFee(body.readDouble());

		byte[] availabilityFlag = new byte[1];
		body.readBytes(availabilityFlag);
		info.setAvailabilityFlag(FtdcAvailabilityFlagType.parseFrom(StringUtils.trimToEmpty(new String(availabilityFlag))));

		byte[] operatorCode = new byte[17];
		body.readBytes(operatorCode);
		info.setOperatorCode(StringUtils.trimToEmpty(new String(operatorCode)));

		byte[] bankNewAccount = new byte[41];
		body.readBytes(bankNewAccount);
		info.setBankNewAccount(StringUtils.trimToEmpty(new String(bankNewAccount)));

		info.setErrorID(body.readInt());

		byte[] errorMsg = new byte[81];
		body.readBytes(errorMsg);
		try {
			info.setErrorMsg(StringUtils.trimToEmpty(new String(errorMsg, ApplicationRuntime.conf().defaultEncoding())));
		} catch (UnsupportedEncodingException e) {
			// nop
		}

		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RspQryTransferSerial [plateSerial=" + plateSerial + ", tradeDate=" + tradeDate + ", tradingDay="
				+ tradingDay + ", tradeTime=" + tradeTime + ", tradeCode=" + tradeCode + ", sessionID=" + sessionID
				+ ", bankID=" + bankID + ", bankBranchID=" + bankBranchID + ", bankAccType=" + bankAccType
				+ ", bankAccount=" + bankAccount + ", bankSerial=" + bankSerial + ", brokerID=" + brokerID
				+ ", brokerBranchID=" + brokerBranchID + ", futureAccType=" + futureAccType + ", accountID=" + accountID
				+ ", investorID=" + investorID + ", futureSerial=" + futureSerial + ", idCardType=" + idCardType
				+ ", identifiedCardNo=" + identifiedCardNo + ", currencyID=" + currencyID + ", tradeAmount="
				+ tradeAmount + ", custFee=" + custFee + ", brokerFee=" + brokerFee + ", availabilityFlag="
				+ availabilityFlag + ", operatorCode=" + operatorCode + ", bankNewAccount=" + bankNewAccount
				+ ", errorID=" + errorID + ", errorMsg=" + errorMsg + "]";
	}

}
