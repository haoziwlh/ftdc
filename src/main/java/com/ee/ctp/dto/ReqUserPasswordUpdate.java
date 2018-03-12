package com.ee.ctp.dto;

import io.netty.buffer.ByteBuf;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author zlj
 * 2017年10月17日 下午8:18:36
 *
 */
public class ReqUserPasswordUpdate implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] userID = new byte[16];
	private byte[] oldPassword = new byte[41];
	private byte[] newPassword = new byte[41];

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setUserID(String userID) {
		if (StringUtils.isNotEmpty(userID)) {
			System.arraycopy(userID.getBytes(), 0, this.userID, 0, userID.getBytes().length);
		}
	}

	public void setOldPassword(String oldPassword) {
		if (StringUtils.isNotEmpty(oldPassword)) {
			int min = Math.min(oldPassword.getBytes().length, this.oldPassword.length);
			System.arraycopy(oldPassword.getBytes(), 0, this.oldPassword, 0, min);
		}
	}

	public void setNewPassword(String newPassword) {
		if (StringUtils.isNotEmpty(newPassword)) {
			int min = Math.min(newPassword.getBytes().length, this.newPassword.length);
			System.arraycopy(newPassword.getBytes(), 0, this.newPassword, 0, min);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(userID);
		buffer.writeBytes(oldPassword);
		buffer.writeBytes(newPassword);

		return buffer;
	}
}
