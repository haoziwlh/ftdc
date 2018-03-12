package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:47
 *
 */
public class ReqContractBank implements FtdcReq{

	private byte[] brokerID = new byte[11];
	private byte[] bankId = new byte[4];
	private byte[] bankBrchId = new byte[5];

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setBankId(String bankId) {
		if (StringUtils.isNotEmpty(bankId)) {
			System.arraycopy(bankId.getBytes(), 0, this.bankId, 0, bankId.getBytes().length);
		}
	}

	public void setBankBrchId(String bankBrchId) {
		if (StringUtils.isNotEmpty(bankBrchId)) {
			System.arraycopy(bankBrchId.getBytes(), 0, this.bankBrchId, 0, bankBrchId.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(bankId);
		buffer.writeBytes(bankBrchId);
		return buffer;
	}
}
