package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.RequestIdentity;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:18:23
 *
 */
public class ReqUserLogin implements FtdcReq{
	/**
	 * 猜测是控制订阅私有流、公有流
	 */
	private static final byte[] PADDING = new byte[]{
			0x10, 0x01, 0x00, 0x06, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00,
			0x10, 0x01, 0x00, 0x06, 0x00, 0x02, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
			0x10, 0x01, 0x00, 0x06, 0x00, 0x03, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
			0x10, 0x01, 0x00, 0x06, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00
	};
	private byte[] tradingDay = new byte[9];
	private byte[] brokerID = new byte[11];
	private byte[] userId = new byte[16];
	private byte[] passwd = new byte[41];
	private byte[] userProductInfo = new byte[11];
	private byte[] interfaceProductInfo = new byte[11];
	private byte[] protocolInfo = new byte[11];
	private byte[] macAddress = new byte[21];
	private byte[] oneTimePassword = new byte[41];
	private byte[] clientIPAddress = new byte[16];
	private byte[] loginRemark = new byte[36];
	
	public void setTradingDay(String tradingDay) {
		if(StringUtils.isNotEmpty(tradingDay)) {
			System.arraycopy(tradingDay.getBytes(), 0, this.tradingDay, 0, tradingDay.getBytes().length);
		}
	}
	
	public void setBrokerID(String brokerID) {
		if(StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setUserId(String userId) {
		if(StringUtils.isNotEmpty(userId)) {
			int min = Math.min(userId.getBytes().length, this.userId.length);
			System.arraycopy(userId.getBytes(), 0, this.userId, 0, min);
		}
	}

	public void setPasswd(String passwd) {
		if(StringUtils.isNotEmpty(passwd)) {
			int min = Math.min(passwd.getBytes().length, this.passwd.length);
			System.arraycopy(passwd.getBytes(), 0, this.passwd, 0, min);
		}
	}
	

	public void setUserProductInfo(String userProductInfo) {
		if(StringUtils.isNotEmpty(userProductInfo)) {
			System.arraycopy(userProductInfo.getBytes(), 0, this.userProductInfo, 0, userProductInfo.getBytes().length);
		}
	}

	public void setInterfaceProductInfo(String interfaceProductInfo) {
		if(StringUtils.isNotEmpty(interfaceProductInfo)) {
			System.arraycopy(interfaceProductInfo.getBytes(), 0, this.interfaceProductInfo, 0, interfaceProductInfo.getBytes().length);
		}
	}

	public void setProtocolInfo(String protocolInfo) {
		if(StringUtils.isNotEmpty(protocolInfo)) {
			System.arraycopy(protocolInfo.getBytes(), 0, this.protocolInfo, 0, protocolInfo.getBytes().length);
		}
	}

	public void setMacAddress(String macAddress) {
		if(StringUtils.isNotEmpty(macAddress)) {
			int min = Math.min(macAddress.getBytes().length, this.macAddress.length);
			System.arraycopy(macAddress.getBytes(), 0, this.macAddress, 0, min);
		}
	}

	public void setOneTimePassword(String oneTimePassword) {
		if(StringUtils.isNotEmpty(oneTimePassword)) {
			System.arraycopy(oneTimePassword.getBytes(), 0, this.oneTimePassword, 0, oneTimePassword.getBytes().length);
		}
	}

	public void setClientIPAddress(String clientIPAddress) {
		if(StringUtils.isNotEmpty(clientIPAddress)) {
			int min = Math.min(clientIPAddress.getBytes().length, this.clientIPAddress.length);
			System.arraycopy(clientIPAddress.getBytes(), 0, this.clientIPAddress, 0, min);
		}
	}

	public void setLoginRemark(String loginRemark) {
		if(StringUtils.isNotEmpty(loginRemark)) {
			int min = Math.min(loginRemark.getBytes().length, this.loginRemark.length);
			System.arraycopy(loginRemark.getBytes(), 0, this.loginRemark, 0, min);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(tradingDay);
		buffer.writeBytes(brokerID);
		buffer.writeBytes(userId);
		buffer.writeBytes(passwd);
		buffer.writeBytes(userProductInfo);
		buffer.writeBytes(interfaceProductInfo);
		buffer.writeBytes(protocolInfo);
		buffer.writeBytes(macAddress);
		buffer.writeBytes(oneTimePassword);
		buffer.writeBytes(clientIPAddress);
		buffer.writeBytes(loginRemark);
		buffer.writeBytes(PADDING);
		return buffer;
	}
	
	public ReqAuth parseReqAuth(RequestIdentity ri) {
		ReqAuth reqAuth = new ReqAuth();
		reqAuth.setBrokerID(ri.getBrokerId());
		reqAuth.setInvestorID(ri.getUserId());
		reqAuth.setProductInfo(new String(this.userProductInfo));
		return reqAuth;
	}
}
