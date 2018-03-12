package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcCurrencyID;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:17:28
 *
 */
public class ReqQryTransferSerial implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] accountID = new byte[13];
	private byte[] bankID = new byte[4];
	private int currencyID;

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

	public void setCurrencyID(FtdcCurrencyID currencyID) {
		if(currencyID != null) {
			this.currencyID = currencyID.getId(); }
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(accountID);
		buffer.writeBytes(bankID);
		buffer.writeInt(currencyID);
		return buffer;
	}
}
