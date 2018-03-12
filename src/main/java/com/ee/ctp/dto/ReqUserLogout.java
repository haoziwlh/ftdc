package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:18:31
 *
 */
public class ReqUserLogout implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] userId = new byte[16];
	
	public void setBrokerID(String brokerID) {
		if(StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setUserId(String userId) {
		if(StringUtils.isNotEmpty(userId)) {
			System.arraycopy(userId.getBytes(), 0, this.userId, 0, userId.getBytes().length);
		}
	}
	
	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(userId);
		return buffer;
	}
}
