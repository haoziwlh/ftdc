package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcCurrencyID;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author wyz
 * 2017年10月17日 下午8:15:29
 *
 */
public class ReqAccountRegister implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] accountID = new byte[13];
	private byte[] bankID = new byte[4];
	private byte[] bankBranchID = new byte[5];
	private byte[] currencyID = new byte[4];

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setAccountID(String accountID) {
		if (StringUtils.isNotEmpty(accountID)) {
			System.arraycopy(accountID.getBytes(), 0, this.accountID, 0, accountID.getBytes().length);
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

	public void setCurrencyID(FtdcCurrencyID currencyID) {
		if (currencyID != null) {
			System.arraycopy(currencyID.id().getBytes(), 0, this.currencyID, 0, currencyID.id().getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(accountID);
		buffer.writeBytes(bankID);
		buffer.writeBytes(bankBranchID);
		buffer.writeBytes(currencyID);

		return buffer;
	}
}
