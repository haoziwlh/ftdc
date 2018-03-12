package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author wyz
 * 2017年10月17日 下午8:19:06
 *
 */
public class RspFromBankToFuture implements FtdcRsp{
	// 4
	private int errorCode;
	// 81
	private String errorCont;
	// 7
	private String tradeCode;
	// 4
	private int bankID;
	// 5
	private String bankBranchID;
	// 11
	private String brokerID;
	// 31
	private String brokerBranchID;
	// 9
	private String tradeDate;
	// 9
	private String tradeTime;
	// 13
	private String bankSerial;
	// 9
	private String tradingDay;
	// 4
	private int plateSerial;
	// 1
	private String lastFragment;
	// 4
	private int sessionID;
	// 51
	private String customerName;
	// 1
	private String idCardType;
	// 51
	private String identifiedCardNo;
	// 1
	private String custType;
	// 41
	private String bankAccount;
	// 41
	private String bankPassWord;
	// 13
	private String accountID;
	// 41
	private String password;
	// 4
	private int installID;
	// 4
	private int futureSerial;
	// 16
	private String userID;
	// 1
	private String verifyCertNoFlag;
	// 4
	private int currencyID;
	// 8
	private double tradeAmount;
	// 8
	private double futureFetchAmount;
	// 1
	private String feePayFlag;
	// 8
	private double custFee;
	// 8
	private double brokerFee;
	// 129
	private String message;
	// 36
	private String digest;
	// 1
	private String bankAccType;
	// 3
	private String deviceID;
	// 1
	private String bankSecuAccType;
	// 33
	private String brokerIDByBank;
	// 41
	private String bankSecuAcc;
	// 1
	private String bankPwdFlag;
	// 1
	private String secuPwdFlag;
	// 17
	private String operNo;
	// 4
	private int requestID;
	// 4
	private int tID;
	// 1
	private String transferStatus;
	// 161
	private String longCustomerName;

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCont
	 */
	public String getErrorCont() {
		return errorCont;
	}

	/**
	 * @param errorCont the errorCont to set
	 */
	public void setErrorCont(String errorCont) {
		this.errorCont = errorCont;
	}


	/**
	 * @return the tradeCode
	 */
	public String getTradeCode() {
		return tradeCode;
	}

	/**
	 * @param tradeCode the tradeCode to set
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	/**
	 * @return the bankID
	 */
	public int getBankID() {
		return bankID;
	}

	/**
	 * @param bankID the bankID to set
	 */
	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	/**
	 * @return the bankBranchID
	 */
	public String getBankBranchID() {
		return bankBranchID;
	}

	/**
	 * @param bankBranchID the bankBranchID to set
	 */
	public void setBankBranchID(String bankBranchID) {
		this.bankBranchID = bankBranchID;
	}

	/**
	 * @return the brokerID
	 */
	public String getBrokerID() {
		return brokerID;
	}

	/**
	 * @param brokerID the brokerID to set
	 */
	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	/**
	 * @return the brokerBranchID
	 */
	public String getBrokerBranchID() {
		return brokerBranchID;
	}

	/**
	 * @param brokerBranchID the brokerBranchID to set
	 */
	public void setBrokerBranchID(String brokerBranchID) {
		this.brokerBranchID = brokerBranchID;
	}

	/**
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return the tradeTime
	 */
	public String getTradeTime() {
		return tradeTime;
	}

	/**
	 * @param tradeTime the tradeTime to set
	 */
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * @return the bankSerial
	 */
	public String getBankSerial() {
		return bankSerial;
	}

	/**
	 * @param bankSerial the bankSerial to set
	 */
	public void setBankSerial(String bankSerial) {
		this.bankSerial = bankSerial;
	}

	/**
	 * @return the tradingDay
	 */
	public String getTradingDay() {
		return tradingDay;
	}

	/**
	 * @param tradingDay the tradingDay to set
	 */
	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}

	/**
	 * @return the plateSerial
	 */
	public int getPlateSerial() {
		return plateSerial;
	}

	/**
	 * @param plateSerial the plateSerial to set
	 */
	public void setPlateSerial(int plateSerial) {
		this.plateSerial = plateSerial;
	}

	/**
	 * @return the lastFragment
	 */
	public String getLastFragment() {
		return lastFragment;
	}

	/**
	 * @param lastFragment the lastFragment to set
	 */
	public void setLastFragment(String lastFragment) {
		this.lastFragment = lastFragment;
	}

	/**
	 * @return the sessionID
	 */
	public int getSessionID() {
		return sessionID;
	}

	/**
	 * @param sessionID the sessionID to set
	 */
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the idCardType
	 */
	public String getIdCardType() {
		return idCardType;
	}

	/**
	 * @param idCardType the idCardType to set
	 */
	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	/**
	 * @return the identifiedCardNo
	 */
	public String getIdentifiedCardNo() {
		return identifiedCardNo;
	}

	/**
	 * @param identifiedCardNo the identifiedCardNo to set
	 */
	public void setIdentifiedCardNo(String identifiedCardNo) {
		this.identifiedCardNo = identifiedCardNo;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the bankPassWord
	 */
	public String getBankPassWord() {
		return bankPassWord;
	}

	/**
	 * @param bankPassWord the bankPassWord to set
	 */
	public void setBankPassWord(String bankPassWord) {
		this.bankPassWord = bankPassWord;
	}

	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the installID
	 */
	public int getInstallID() {
		return installID;
	}

	/**
	 * @param installID the installID to set
	 */
	public void setInstallID(int installID) {
		this.installID = installID;
	}

	/**
	 * @return the futureSerial
	 */
	public int getFutureSerial() {
		return futureSerial;
	}

	/**
	 * @param futureSerial the futureSerial to set
	 */
	public void setFutureSerial(int futureSerial) {
		this.futureSerial = futureSerial;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the verifyCertNoFlag
	 */
	public String getVerifyCertNoFlag() {
		return verifyCertNoFlag;
	}

	/**
	 * @param verifyCertNoFlag the verifyCertNoFlag to set
	 */
	public void setVerifyCertNoFlag(String verifyCertNoFlag) {
		this.verifyCertNoFlag = verifyCertNoFlag;
	}

	/**
	 * @return the currencyID
	 */
	public int getCurrencyID() {
		return currencyID;
	}

	/**
	 * @param currencyID the currencyID to set
	 */
	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}

	/**
	 * @return the tradeAmount
	 */
	public double getTradeAmount() {
		return tradeAmount;
	}

	/**
	 * @param tradeAmount the tradeAmount to set
	 */
	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	/**
	 * @return the futureFetchAmount
	 */
	public double getFutureFetchAmount() {
		return futureFetchAmount;
	}

	/**
	 * @param futureFetchAmount the futureFetchAmount to set
	 */
	public void setFutureFetchAmount(double futureFetchAmount) {
		this.futureFetchAmount = futureFetchAmount;
	}

	/**
	 * @return the feePayFlag
	 */
	public String getFeePayFlag() {
		return feePayFlag;
	}

	/**
	 * @param feePayFlag the feePayFlag to set
	 */
	public void setFeePayFlag(String feePayFlag) {
		this.feePayFlag = feePayFlag;
	}

	/**
	 * @return the custFee
	 */
	public double getCustFee() {
		return custFee;
	}

	/**
	 * @param custFee the custFee to set
	 */
	public void setCustFee(double custFee) {
		this.custFee = custFee;
	}

	/**
	 * @return the brokerFee
	 */
	public double getBrokerFee() {
		return brokerFee;
	}

	/**
	 * @param brokerFee the brokerFee to set
	 */
	public void setBrokerFee(double brokerFee) {
		this.brokerFee = brokerFee;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the digest
	 */
	public String getDigest() {
		return digest;
	}

	/**
	 * @param digest the digest to set
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}

	/**
	 * @return the bankAccType
	 */
	public String getBankAccType() {
		return bankAccType;
	}

	/**
	 * @param bankAccType the bankAccType to set
	 */
	public void setBankAccType(String bankAccType) {
		this.bankAccType = bankAccType;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the bankSecuAccType
	 */
	public String getBankSecuAccType() {
		return bankSecuAccType;
	}

	/**
	 * @param bankSecuAccType the bankSecuAccType to set
	 */
	public void setBankSecuAccType(String bankSecuAccType) {
		this.bankSecuAccType = bankSecuAccType;
	}

	/**
	 * @return the brokerIDByBank
	 */
	public String getBrokerIDByBank() {
		return brokerIDByBank;
	}

	/**
	 * @param brokerIDByBank the brokerIDByBank to set
	 */
	public void setBrokerIDByBank(String brokerIDByBank) {
		this.brokerIDByBank = brokerIDByBank;
	}

	/**
	 * @return the bankSecuAcc
	 */
	public String getBankSecuAcc() {
		return bankSecuAcc;
	}

	/**
	 * @param bankSecuAcc the bankSecuAcc to set
	 */
	public void setBankSecuAcc(String bankSecuAcc) {
		this.bankSecuAcc = bankSecuAcc;
	}

	/**
	 * @return the bankPwdFlag
	 */
	public String getBankPwdFlag() {
		return bankPwdFlag;
	}

	/**
	 * @param bankPwdFlag the bankPwdFlag to set
	 */
	public void setBankPwdFlag(String bankPwdFlag) {
		this.bankPwdFlag = bankPwdFlag;
	}

	/**
	 * @return the secuPwdFlag
	 */
	public String getSecuPwdFlag() {
		return secuPwdFlag;
	}

	/**
	 * @param secuPwdFlag the secuPwdFlag to set
	 */
	public void setSecuPwdFlag(String secuPwdFlag) {
		this.secuPwdFlag = secuPwdFlag;
	}

	/**
	 * @return the operNo
	 */
	public String getOperNo() {
		return operNo;
	}

	/**
	 * @param operNo the operNo to set
	 */
	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	/**
	 * @return the requestID
	 */
	public int getRequestID() {
		return requestID;
	}

	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	/**
	 * @return the tID
	 */
	public int gettID() {
		return tID;
	}

	/**
	 * @param tID the tID to set
	 */
	public void settID(int tID) {
		this.tID = tID;
	}

	/**
	 * @return the transferStatus
	 */
	public String getTransferStatus() {
		return transferStatus;
	}

	/**
	 * @param transferStatus the transferStatus to set
	 */
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	/**
	 * @return the longCustomerName
	 */
	public String getLongCustomerName() {
		return longCustomerName;
	}

	/**
	 * @param longCustomerName the longCustomerName to set
	 */
	public void setLongCustomerName(String longCustomerName) {
		this.longCustomerName = longCustomerName;
	}

	@Override
	public RspFromBankToFuture parseFrom(ByteBuf body, RspError error) {
		RspFromBankToFuture info = new RspFromBankToFuture();
		
		info.setErrorCont(error.getErrorMsg());
		info.setErrorCode(error.getErrorCode());
		
		byte[] tradeCode = new byte[7];
		body.readBytes(tradeCode);
		info.setTradeCode(StringUtils.trimToEmpty(new String(tradeCode)));

		info.setBankID(body.readInt());

		byte[] bankBranchID = new byte[5];
		body.readBytes(bankBranchID);
		info.setBankBranchID(StringUtils.trimToEmpty(new String(bankBranchID)));

		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] brokerBranchID = new byte[31];
		body.readBytes(brokerBranchID);
		info.setBrokerBranchID(StringUtils.trimToEmpty(new String(brokerBranchID)));

		byte[] tradeDate = new byte[9];
		body.readBytes(tradeDate);
		info.setTradeDate(StringUtils.trimToEmpty(new String(tradeDate)));

		byte[] tradeTime = new byte[9];
		body.readBytes(tradeTime);
		info.setTradeTime(StringUtils.trimToEmpty(new String(tradeTime)));

		byte[] bankSerial = new byte[13];
		body.readBytes(bankSerial);
		info.setBankSerial(StringUtils.trimToEmpty(new String(bankSerial)));

		byte[] tradingDay = new byte[9];
		body.readBytes(tradingDay);
		info.setTradingDay(StringUtils.trimToEmpty(new String(tradingDay)));

		info.setPlateSerial(body.readInt());

		byte[] lastFragment = new byte[1];
		body.readBytes(lastFragment);
		info.setLastFragment(StringUtils.trimToEmpty(new String(lastFragment)));

		info.setSessionID(body.readInt());

		byte[] customerName = new byte[51];
		body.readBytes(customerName);
		info.setCustomerName(StringUtils.trimToEmpty(new String(customerName)));

		byte[] idCardType = new byte[1];
		body.readBytes(idCardType);
		info.setIdCardType(StringUtils.trimToEmpty(new String(idCardType)));

		byte[] identifiedCardNo = new byte[51];
		body.readBytes(identifiedCardNo);
		info.setIdentifiedCardNo(StringUtils.trimToEmpty(new String(identifiedCardNo)));

		byte[] custType = new byte[1];
		body.readBytes(custType);
		info.setCustType(StringUtils.trimToEmpty(new String(custType)));

		byte[] bankAccount = new byte[41];
		body.readBytes(bankAccount);
		info.setBankAccount(StringUtils.trimToEmpty(new String(bankAccount)));

		byte[] bankPassWord = new byte[41];
		body.readBytes(bankPassWord);
		info.setBankPassWord(StringUtils.trimToEmpty(new String(bankPassWord)));

		byte[] accountID = new byte[13];
		body.readBytes(accountID);
		info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));

		byte[] password = new byte[41];
		body.readBytes(password);
		info.setPassword(StringUtils.trimToEmpty(new String(password)));

		info.setInstallID(body.readInt());

		info.setFutureSerial(body.readInt());

		byte[] userID = new byte[16];
		body.readBytes(userID);
		info.setUserID(StringUtils.trimToEmpty(new String(userID)));

		byte[] verifyCertNoFlag = new byte[1];
		body.readBytes(verifyCertNoFlag);
		info.setVerifyCertNoFlag(StringUtils.trimToEmpty(new String(verifyCertNoFlag)));

		info.setCurrencyID(body.readInt());

		info.setTradeAmount(body.readDouble());

		info.setFutureFetchAmount(body.readDouble());

		byte[] feePayFlag = new byte[1];
		body.readBytes(feePayFlag);
		info.setFeePayFlag(StringUtils.trimToEmpty(new String(feePayFlag)));

		info.setCustFee(body.readDouble());

		info.setBrokerFee(body.readDouble());

		byte[] message = new byte[129];
		body.readBytes(message);
		info.setMessage(StringUtils.trimToEmpty(new String(message)));

		byte[] digest = new byte[36];
		body.readBytes(digest);
		info.setDigest(StringUtils.trimToEmpty(new String(digest)));

		byte[] bankAccType = new byte[1];
		body.readBytes(bankAccType);
		info.setBankAccType(StringUtils.trimToEmpty(new String(bankAccType)));

		byte[] deviceID = new byte[3];
		body.readBytes(deviceID);
		info.setDeviceID(StringUtils.trimToEmpty(new String(deviceID)));

		byte[] bankSecuAccType = new byte[1];
		body.readBytes(bankSecuAccType);
		info.setBankSecuAccType(StringUtils.trimToEmpty(new String(bankSecuAccType)));

		byte[] brokerIDByBank = new byte[33];
		body.readBytes(brokerIDByBank);
		info.setBrokerIDByBank(StringUtils.trimToEmpty(new String(brokerIDByBank)));

		byte[] bankSecuAcc = new byte[41];
		body.readBytes(bankSecuAcc);
		info.setBankSecuAcc(StringUtils.trimToEmpty(new String(bankSecuAcc)));

		byte[] bankPwdFlag = new byte[1];
		body.readBytes(bankPwdFlag);
		info.setBankPwdFlag(StringUtils.trimToEmpty(new String(bankPwdFlag)));

		byte[] secuPwdFlag = new byte[1];
		body.readBytes(secuPwdFlag);
		info.setSecuPwdFlag(StringUtils.trimToEmpty(new String(secuPwdFlag)));

		byte[] operNo = new byte[17];
		body.readBytes(operNo);
		info.setOperNo(StringUtils.trimToEmpty(new String(operNo)));

		info.setRequestID(body.readInt());

		info.settID(body.readInt());

		byte[] transferStatus = new byte[1];
		body.readBytes(transferStatus);
		info.setTransferStatus(StringUtils.trimToEmpty(new String(transferStatus)));

		byte[] longCustomerName = new byte[161];
		body.readBytes(longCustomerName);
		info.setLongCustomerName(StringUtils.trimToEmpty(new String(longCustomerName)));

		return info;
	}

	@Override
	public String toString() {
		return "RspFromBankToFuture [errorCode=" + errorCode + ", errorCont=" + errorCont + ", tradeCode=" + tradeCode
				+ ", bankID=" + bankID + ", bankBranchID=" + bankBranchID + ", brokerID=" + brokerID
				+ ", brokerBranchID=" + brokerBranchID + ", tradeDate=" + tradeDate + ", tradeTime=" + tradeTime
				+ ", bankSerial=" + bankSerial + ", tradingDay=" + tradingDay + ", plateSerial=" + plateSerial
				+ ", lastFragment=" + lastFragment + ", sessionID=" + sessionID + ", customerName=" + customerName
				+ ", idCardType=" + idCardType + ", identifiedCardNo=" + identifiedCardNo + ", custType=" + custType
				+ ", bankAccount=" + bankAccount + ", bankPassWord=" + bankPassWord + ", accountID=" + accountID
				+ ", password=" + password + ", installID=" + installID + ", futureSerial=" + futureSerial + ", userID="
				+ userID + ", verifyCertNoFlag=" + verifyCertNoFlag + ", currencyID=" + currencyID + ", tradeAmount="
				+ tradeAmount + ", futureFetchAmount=" + futureFetchAmount + ", feePayFlag=" + feePayFlag + ", custFee="
				+ custFee + ", brokerFee=" + brokerFee + ", message=" + message + ", digest=" + digest
				+ ", bankAccType=" + bankAccType + ", deviceID=" + deviceID + ", bankSecuAccType=" + bankSecuAccType
				+ ", brokerIDByBank=" + brokerIDByBank + ", bankSecuAcc=" + bankSecuAcc + ", bankPwdFlag=" + bankPwdFlag
				+ ", secuPwdFlag=" + secuPwdFlag + ", operNo=" + operNo + ", requestID=" + requestID + ", tID=" + tID
				+ ", transferStatus=" + transferStatus + ", longCustomerName=" + longCustomerName + "]";
	}

	
}
