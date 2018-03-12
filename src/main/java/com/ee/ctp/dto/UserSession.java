package com.ee.ctp.dto;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录成功存储用户信息
 * @author zlj
 * @date 2017年9月27日
 * 
 * @modifyBy zlj 2017年9月27日
 */
public class UserSession {
	private int frontId;
	private String userID;
	private String brokerId;
	private int sessionId;
	private AtomicInteger orderRef = new AtomicInteger(10);
	
	public UserSession(){
	}
	public UserSession(int frontId, String userID, int sessionId, String brokerId) {
		this.frontId = frontId;
		this.userID = userID;
		this.sessionId = sessionId;
		this.brokerId = brokerId;
	}
	
	public UserSession(RspUserLogin rspUserLogin) {
		this.frontId = rspUserLogin.getFrontId();
		this.userID = rspUserLogin.getUserID();
		this.sessionId = rspUserLogin.getSessionId();
		this.brokerId = rspUserLogin.getBrokerID();
	}
	public int getFrontId() {
		return frontId;
	}
	public void setFrontId(int frontId) {
		this.frontId = frontId;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	
	public int orderRef() {
		return this.orderRef.getAndIncrement();
	}
	
}
