package com.ee.ctp;

import io.netty.channel.Channel;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:12:42
 *
 */
public class RequestIdentity {
	private int reqId;
	private String userId;
	private String brokerId;
	private Channel clientChannel;
	private String authCode;
	/**
	 * 请求消息
	 */
	private Object reqMsg;

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public Channel getClientChannel() {
		return clientChannel;
	}

	public void setClientChannel(Channel clientChannel) {
		this.clientChannel = clientChannel;
	}
	
	public Object getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(Object reqMsg) {
		this.reqMsg = reqMsg;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "RequestIdentity [reqId=" + reqId + ", userId=" + userId + ", brokerId=" + brokerId + ", clientChannel="
				+ clientChannel + ", authCode=" + authCode
				+ "]";
	}
	
}
