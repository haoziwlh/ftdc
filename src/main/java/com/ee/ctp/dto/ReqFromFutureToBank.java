package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcBankAccType;
import com.ee.ctp.enums.business.FtdcCurrencyID;
import com.ee.ctp.enums.business.FtdcCustType;
import com.ee.ctp.enums.business.FtdcFeePayFlagType;
import com.ee.ctp.enums.business.FtdcIdCardType;
import com.ee.ctp.enums.business.FtdcLastFragmentType;
import com.ee.ctp.enums.business.FtdcPwdFlag;
import com.ee.ctp.enums.business.FtdcTradeCode;
import com.ee.ctp.enums.business.FtdcTransferStatusType;
import com.ee.ctp.enums.business.FtdcYesNoIndicator;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author zlj
 * 2017年10月17日 下午8:15:58
 *
 */
public class ReqFromFutureToBank implements FtdcReq{
	private byte[] tradeCode = new byte[7];
	private byte[] bankID = new byte[4];
	private byte[] bankBranchID = new byte[5];
	private byte[] brokerID = new byte[11];
	private byte[] brokerBranchID = new byte[31];
	private byte[] tradeDate = new byte[9];
	private byte[] tradeTime = new byte[9];
	private byte[] bankSerial = new byte[13];
	private byte[] tradingDay = new byte[9];
	private int plateSerial;
	private byte[] lastFragment = new byte[1];
	private int sessionID;
	private byte[] customerName = new byte[51];
	private byte[] idCardType = new byte[1];
	private byte[] identifiedCardNo = new byte[51];
	private byte[] custType = new byte[1];
	private byte[] bankAccount = new byte[41];
	private byte[] bankPassWord = new byte[41];
	private byte[] accountID = new byte[13];
	private byte[] password = new byte[41];
	private int installID;
	private int futureSerial;
	private byte[] userID = new byte[16];
	private byte[] verifyCertNoFlag = new byte[1];
	private int currencyID;
	private double tradeAmount;
	private double futureFetchAmount;
	private byte[] feePayFlag = new byte[1];
	private double custFee;
	private double brokerFee;
	private byte[] message = new byte[129];
	private byte[] digest = new byte[36];
	private byte[] bankAccType = new byte[1];
	private byte[] deviceID = new byte[3];
	private byte[] bankSecuAccType = new byte[1];
	private byte[] brokerIDByBank = new byte[33];
	private byte[] bankSecuAcc = new byte[41];
	private byte[] bankPwdFlag = new byte[1];
	private byte[] secuPwdFlag = new byte[1];
	private byte[] operNo = new byte[17];
	private int requestID;
	private int tID;
	private byte[] transferStatus = new byte[1];
	private byte[] longCustomerName = new byte[161];

	public void setTradeCode(FtdcTradeCode tradeCode) {
		if (tradeCode != null) {
			System.arraycopy(tradeCode.getCode().getBytes(), 0, this.tradeCode, 0,
					tradeCode.getCode().getBytes().length);
		}

	}

	public void setBankID(String bankID) {
		if (StringUtils.isNotEmpty(bankID)) {
			System.arraycopy(bankID.getBytes(), 0, this.bankID, 0, bankID.getBytes().length);
		}
	}

	public void setBankBranchID(String bankBranchID) {
		if (StringUtils.isNotEmpty(bankBranchID)) {
			System.arraycopy(bankBranchID.getBytes(), 0, this.bankBranchID, 0, bankBranchID.getBytes().length);
		}

	}

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setBrokerBranchID(String brokerBranchID) {
		if (StringUtils.isNotEmpty(brokerBranchID)) {
			System.arraycopy(brokerBranchID.getBytes(), 0, this.brokerBranchID, 0, brokerBranchID.getBytes().length);
		}
	}

	public void setTradeDate(String tradeDate) {
		if (StringUtils.isNotEmpty(tradeDate)) {
			System.arraycopy(tradeDate.getBytes(), 0, this.tradeDate, 0, tradeDate.getBytes().length);
		}
	}

	public void setTradeTime(String tradeTime) {
		if (StringUtils.isNotEmpty(tradeTime)) {
			System.arraycopy(tradeTime.getBytes(), 0, this.tradeTime, 0, tradeTime.getBytes().length);
		}
	}

	public void setBankSerial(String bankSerial) {
		if (StringUtils.isNotEmpty(bankSerial)) {
			System.arraycopy(bankSerial.getBytes(), 0, this.bankSerial, 0, bankSerial.getBytes().length);
		}
	}

	public void setTradingDay(String tradingDay) {
		if (StringUtils.isNotEmpty(tradingDay)) {
			System.arraycopy(tradingDay.getBytes(), 0, this.tradingDay, 0, tradingDay.getBytes().length);
		}
	}

	public void setPlateSerial(int plateSerial) {
		this.plateSerial = plateSerial;
	}

	public void setLastFragment(FtdcLastFragmentType lastFragment) {
		if (null != lastFragment) {
			System.arraycopy(lastFragment.type().getBytes(), 0, this.lastFragment, 0,
					lastFragment.type().getBytes().length);
		}

	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public void setCustomerName(String customerName) {
		if (StringUtils.isNotEmpty(customerName)) {
			System.arraycopy(customerName.getBytes(), 0, this.customerName, 0, customerName.getBytes().length);
		}
	}

	public void setIdCardType(FtdcIdCardType idCardType) {
		if (null != idCardType) {
			System.arraycopy(idCardType.type().getBytes(), 0, this.idCardType, 0, idCardType.type().getBytes().length);
		}

	}

	public void setIdentifiedCardNo(String identifiedCardNo) {
		if (StringUtils.isNotEmpty(identifiedCardNo)) {
			System.arraycopy(identifiedCardNo.getBytes(), 0, this.identifiedCardNo, 0,
					identifiedCardNo.getBytes().length);
		}
	}

	public void setCustType(FtdcCustType custType) {
		if (null != custType) {
			System.arraycopy(custType.type().getBytes(), 0, this.custType, 0, custType.type().getBytes().length);
		}

	}

	public void setBankAccount(String bankAccount) {
		if (StringUtils.isNotEmpty(bankAccount)) {
			System.arraycopy(bankAccount.getBytes(), 0, this.bankAccount, 0, bankAccount.getBytes().length);
		}
	}

	public void setBankPassWord(String bankPassWord) {
		if (StringUtils.isNotEmpty(bankPassWord)) {
			int min = Math.min(bankPassWord.getBytes().length, this.bankPassWord.length);
			System.arraycopy(bankPassWord.getBytes(), 0, this.bankPassWord, 0, min);
		}
	}

	public void setAccountID(String accountID) {
		if (StringUtils.isNotEmpty(accountID)) {
			System.arraycopy(accountID.getBytes(), 0, this.accountID, 0, accountID.getBytes().length);
		}
	}

	public void setPassword(String password) {
		if (StringUtils.isNotEmpty(password)) {
			int min = Math.min(password.getBytes().length, this.password.length);
			System.arraycopy(password.getBytes(), 0, this.password, 0, min);
		}
	}

	public void setInstallID(int installID) {
		this.installID = installID;
	}

	public void setFutureSerial(int futureSerial) {
		this.futureSerial = futureSerial;
	}

	public void setUserID(String userID) {
		if (StringUtils.isNotEmpty(userID)) {
			System.arraycopy(userID.getBytes(), 0, this.userID, 0, userID.getBytes().length);
		}
	}

	public void setVerifyCertNoFlag(FtdcYesNoIndicator verifyCertNoFlag) {
		if (verifyCertNoFlag != null) {
			System.arraycopy(verifyCertNoFlag.getFlag().getBytes(), 0, this.verifyCertNoFlag, 0,
					verifyCertNoFlag.getFlag().getBytes().length);
		}

	}

	public void setCurrencyID(FtdcCurrencyID currencyID) {
		this.currencyID = currencyID.getId();
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public void setFutureFetchAmount(double futureFetchAmount) {
		this.futureFetchAmount = futureFetchAmount;
	}

	public void setFeePayFlag(FtdcFeePayFlagType feePayFlag) {
		if (null != feePayFlag) {
			System.arraycopy(feePayFlag.type().getBytes(), 0, this.feePayFlag, 0, feePayFlag.type().getBytes().length);
		}

	}

	public void setCustFee(double custFee) {
		this.custFee = custFee;
	}

	public void setBrokerFee(double brokerFee) {
		this.brokerFee = brokerFee;
	}

	public void setMessage(String message) {
		if (StringUtils.isNotEmpty(message)) {
			System.arraycopy(message.getBytes(), 0, this.message, 0, message.getBytes().length);
		}
	}

	public void setDigest(String digest) {
		if (StringUtils.isNotEmpty(digest)) {
			System.arraycopy(digest.getBytes(), 0, this.digest, 0, digest.getBytes().length);
		}
	}

	public void setBankAccType(FtdcBankAccType bankAccType) {
		if (null != bankAccType) {
			System.arraycopy(bankAccType.type().getBytes(), 0, this.bankAccType, 0,
					bankAccType.type().getBytes().length);
		}

	}

	public void setDeviceID(String deviceID) {
		if (StringUtils.isNotEmpty(deviceID)) {
			System.arraycopy(deviceID.getBytes(), 0, this.deviceID, 0, deviceID.getBytes().length);
		}
	}

	public void setBankSecuAccType(String bankSecuAccType) {
		if (StringUtils.isNotEmpty(bankSecuAccType)) {
			System.arraycopy(bankSecuAccType.getBytes(), 0, this.bankSecuAccType, 0, bankSecuAccType.getBytes().length);
		}
	}

	public void setBrokerIDByBank(String brokerIDByBank) {
		if (StringUtils.isNotEmpty(brokerIDByBank)) {
			System.arraycopy(brokerIDByBank.getBytes(), 0, this.brokerIDByBank, 0, brokerIDByBank.getBytes().length);
		}
	}

	public void setBankSecuAcc(String bankSecuAcc) {
		if (StringUtils.isNotEmpty(bankSecuAcc)) {
			System.arraycopy(bankSecuAcc.getBytes(), 0, this.bankSecuAcc, 0, bankSecuAcc.getBytes().length);
		}
	}

	public void setBankPwdFlag(FtdcPwdFlag bankPwdFlag) {
		if (bankPwdFlag != null) {
			System.arraycopy(bankPwdFlag.getFlag().getBytes(), 0, this.bankPwdFlag, 0,
					bankPwdFlag.getFlag().getBytes().length);
		}

	}

	public void setSecuPwdFlag(FtdcPwdFlag secuPwdFlag) {
		if (secuPwdFlag != null) {
			System.arraycopy(secuPwdFlag.getFlag().getBytes(), 0, this.secuPwdFlag, 0,
					secuPwdFlag.getFlag().getBytes().length);
		}

	}

	public void setOperNo(String operNo) {
		if (StringUtils.isNotEmpty(operNo)) {
			System.arraycopy(operNo.getBytes(), 0, this.operNo, 0, operNo.getBytes().length);
		}
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public void setTID(int tID) {
		this.tID = tID;
	}

	public void setTransferStatus(FtdcTransferStatusType transferStatus) {
		if (null != transferStatus) {
			System.arraycopy(transferStatus.type().getBytes(), 0, this.transferStatus, 0,
					transferStatus.type().getBytes().length);
		}

	}

	public void setLongCustomerName(String longCustomerName) {
		if (StringUtils.isNotEmpty(longCustomerName)) {
			System.arraycopy(longCustomerName.getBytes(), 0, this.longCustomerName, 0,
					longCustomerName.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(tradeCode);
		buffer.writeBytes(bankID);
		buffer.writeBytes(bankBranchID);
		buffer.writeBytes(brokerID);
		buffer.writeBytes(brokerBranchID);
		buffer.writeBytes(tradeDate);
		buffer.writeBytes(tradeTime);
		buffer.writeBytes(bankSerial);
		buffer.writeBytes(tradingDay);
		buffer.writeInt(plateSerial);
		buffer.writeBytes(lastFragment);
		buffer.writeInt(sessionID);
		buffer.writeBytes(customerName);
		buffer.writeBytes(idCardType);
		buffer.writeBytes(identifiedCardNo);
		buffer.writeBytes(custType);
		buffer.writeBytes(bankAccount);
		buffer.writeBytes(bankPassWord);
		buffer.writeBytes(accountID);
		buffer.writeBytes(password);
		buffer.writeInt(installID);
		buffer.writeInt(futureSerial);
		buffer.writeBytes(userID);
		buffer.writeBytes(verifyCertNoFlag);
		buffer.writeInt(currencyID);
		buffer.writeDouble(tradeAmount);
		buffer.writeDouble(futureFetchAmount);
		buffer.writeBytes(feePayFlag);
		buffer.writeDouble(custFee);
		buffer.writeDouble(brokerFee);
		buffer.writeBytes(message);
		buffer.writeBytes(digest);
		buffer.writeBytes(bankAccType);
		buffer.writeBytes(deviceID);
		buffer.writeBytes(bankSecuAccType);
		buffer.writeBytes(brokerIDByBank);
		buffer.writeBytes(bankSecuAcc);
		buffer.writeBytes(bankPwdFlag);
		buffer.writeBytes(secuPwdFlag);
		buffer.writeBytes(operNo);
		buffer.writeInt(requestID);
		buffer.writeInt(tID);
		buffer.writeBytes(transferStatus);
		buffer.writeBytes(longCustomerName);

		return buffer;
	}
}
