package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.enums.business.FtdcBankAccType;
import com.ee.ctp.enums.business.FtdcCustType;
import com.ee.ctp.enums.business.FtdcIdCardType;
import com.ee.ctp.enums.business.FtdcOpenOrDestroyType;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author wyz
 * 2017年10月17日 下午8:18:43
 *
 */
public class RspAccountRegister implements FtdcRsp{
	// 9
	private String tradeDay;
	// 4
	private String bankID;
	// 5
	private String bankBranchID;
	// 41
	private String bankAccount;
	// 11
	private String brokerID;
	// 31
	private String brokerBranchID;
	// 13
	private String accountID;
	// 1
	private FtdcIdCardType idCardType;
	// 51
	private String identifiedCardNo;
	// 51
	private String customerName;
	// 4
	private String currencyID;
	// 1
	private FtdcOpenOrDestroyType openOrDestroy;
	// 9
	private String regDate;
	// 9
	private String outDate;
	// 4
	private int tID;
	// 1
	private FtdcCustType custType;
	// 1
	private FtdcBankAccType bankAccType;
	// 161
	private String longCustomerName;

	
	public String getTradeDay() {
		return tradeDay;
	}


	public void setTradeDay(String tradeDay) {
		this.tradeDay = tradeDay;
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


	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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


	public String getAccountID() {
		return accountID;
	}


	public void setAccountID(String accountID) {
		this.accountID = accountID;
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


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCurrencyID() {
		return currencyID;
	}


	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}


	public FtdcOpenOrDestroyType getOpenOrDestroy() {
		return openOrDestroy;
	}


	public void setOpenOrDestroy(FtdcOpenOrDestroyType openOrDestroy) {
		this.openOrDestroy = openOrDestroy;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public String getOutDate() {
		return outDate;
	}


	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}


	public int getTID() {
		return tID;
	}


	public void setTID(int tID) {
		this.tID = tID;
	}


	public FtdcCustType getCustType() {
		return custType;
	}


	public void setCustType(FtdcCustType custType) {
		this.custType = custType;
	}


	public FtdcBankAccType getBankAccType() {
		return bankAccType;
	}


	public void setBankAccType(FtdcBankAccType bankAccType) {
		this.bankAccType = bankAccType;
	}


	public String getLongCustomerName() {
		return longCustomerName;
	}


	public void setLongCustomerName(String longCustomerName) {
		this.longCustomerName = longCustomerName;
	}

	@Override
	public RspAccountRegister parseFrom(ByteBuf body, RspError error) {
		RspAccountRegister info = new RspAccountRegister();
		byte[] tradeDay = new byte[9];
		body.readBytes(tradeDay);
		info.setTradeDay(StringUtils.trimToEmpty(new String(tradeDay)));

		byte[] bankID = new byte[4];
		body.readBytes(bankID);
		info.setBankID(StringUtils.trimToEmpty(new String(bankID)));

		byte[] bankBranchID = new byte[5];
		body.readBytes(bankBranchID);
		info.setBankBranchID(StringUtils.trimToEmpty(new String(bankBranchID)));

		byte[] bankAccount = new byte[41];
		body.readBytes(bankAccount);
		info.setBankAccount(StringUtils.trimToEmpty(new String(bankAccount)));

		byte[] brokerID = new byte[11];
		body.readBytes(brokerID);
		info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));

		byte[] brokerBranchID = new byte[31];
		body.readBytes(brokerBranchID);
		info.setBrokerBranchID(StringUtils.trimToEmpty(new String(brokerBranchID)));

		byte[] accountID = new byte[13];
		body.readBytes(accountID);
		info.setAccountID(StringUtils.trimToEmpty(new String(accountID)));

		byte[] idCardType = new byte[1];
		body.readBytes(idCardType);
		info.setIdCardType(FtdcIdCardType.parseFrom(StringUtils.trimToEmpty(new String(idCardType))));

		byte[] identifiedCardNo = new byte[51];
		body.readBytes(identifiedCardNo);
		info.setIdentifiedCardNo(StringUtils.trimToEmpty(new String(identifiedCardNo)));

		byte[] customerName = new byte[51];
		body.readBytes(customerName);
		try {
			info.setCustomerName(StringUtils.trimToEmpty(new String(customerName, ApplicationRuntime.conf().defaultEncoding())));
		} catch (UnsupportedEncodingException e) {
			// nop
		}

		byte[] currencyID = new byte[4];
		body.readBytes(currencyID);
		info.setCurrencyID(StringUtils.trimToEmpty(new String(currencyID)));
		
		byte[] openOrDestroy = new byte[1];
		body.readBytes(openOrDestroy);
		info.setOpenOrDestroy(FtdcOpenOrDestroyType.parseFrom(StringUtils.trimToEmpty(new String(openOrDestroy))));

		byte[] regDate = new byte[9];
		body.readBytes(regDate);
		info.setRegDate(StringUtils.trimToEmpty(new String(regDate)));

		byte[] outDate = new byte[9];
		body.readBytes(outDate);
		info.setOutDate(StringUtils.trimToEmpty(new String(outDate)));

		info.setTID(body.readInt());

		byte[] custType = new byte[1];
		body.readBytes(custType);
		info.setCustType(FtdcCustType.parseFrom(StringUtils.trimToEmpty(new String(custType))));

		byte[] bankAccType = new byte[1];
		body.readBytes(bankAccType);
		info.setBankAccType(FtdcBankAccType.parseFrom(StringUtils.trimToEmpty(new String(bankAccType))));

		byte[] longCustomerName = new byte[161];
		body.readBytes(longCustomerName);
		try {
			info.setLongCustomerName(StringUtils.trimToEmpty(new String(longCustomerName, ApplicationRuntime.conf().defaultEncoding())));
		} catch (UnsupportedEncodingException e) {
			// nop
		}

		return info;
	}


	@Override
	public String toString() {
		return "RspAccountRegister [tradeDay=" + tradeDay + ", bankID=" + bankID + ", bankBranchID=" + bankBranchID
				+ ", bankAccount=" + bankAccount + ", brokerID=" + brokerID + ", brokerBranchID=" + brokerBranchID
				+ ", accountID=" + accountID + ", idCardType=" + idCardType + ", identifiedCardNo=" + identifiedCardNo
				+ ", customerName=" + customerName + ", currencyID=" + currencyID + ", openOrDestroy=" + openOrDestroy
				+ ", regDate=" + regDate + ", outDate=" + outDate + ", tID=" + tID + ", custType=" + custType
				+ ", bankAccType=" + bankAccType + ", longCustomerName=" + longCustomerName + "]";
	}
	
	
}
