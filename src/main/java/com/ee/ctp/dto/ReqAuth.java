package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.security.KAES;

import io.netty.buffer.ByteBuf;

/**
 * 
 * @author lyb
 *
 */
public class ReqAuth implements FtdcReq{

	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[16];
	private byte[] productInfo = new byte[11];
	private byte[] authCode = new byte[17];
	private byte[] authInfo = new byte[133];
	
	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}
	public void setInvestorID(String investorID) {
		if (StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}
	public void setProductInfo(String productInfo) {
		if (StringUtils.isNotEmpty(productInfo)) {
			System.arraycopy(productInfo.getBytes(), 0, this.productInfo, 0, productInfo.getBytes().length);
		}
	}
	public void setAuthCode(String authCode) {
		if (StringUtils.isNotEmpty(authCode)) {
			System.arraycopy(authCode.getBytes(), 0, this.authCode, 0, authCode.getBytes().length);
		}
	}
	
	public void setAuthInfo(byte[] authInfo) {
		System.arraycopy(authInfo, 0, this.authInfo, 0, authInfo.length);
	}
	
	
	public ByteBuf writeFirst(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(productInfo);
		buffer.writeBytes(authInfo);
		
		return buffer;
	}
	
	public ByteBuf writeSecond(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(productInfo);
		buffer.writeBytes(authInfo);

		return buffer;
	}
	
	public static ReqAuth buildSecondFrom(RspAuth auth, String authCode) {
		ReqAuth reqAuth = new ReqAuth();
		byte[] authInfo = KAES.DEFAULT_INSTANCE.encrypt(auth.getAuthInfo(), authCode.getBytes());
		reqAuth.setBrokerID(auth.getBrokerID());
		reqAuth.setInvestorID(auth.getUserID());
		reqAuth.setProductInfo(auth.getUserProductInfo());
		reqAuth.setAuthInfo(authInfo);
		return reqAuth;
	}
	
	@Override
	public ByteBuf write(ByteBuf buffer) {
		return null;
	}
}
