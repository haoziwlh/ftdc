package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:01
 *
 */
public class RspError {
	// 4
	private int errorCode;
	// 81
	private String errorMsg;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static RspError parseFrom(ByteBuf body) {
		RspError error = new RspError();
		error.setErrorCode(body.readInt());
		byte[] errorMsg = new byte[81];
		body.readBytes(errorMsg);
		try {
			error.setErrorMsg(StringUtils.trimToEmpty(new String(errorMsg, ApplicationRuntime.conf().defaultEncoding())));
		} catch (UnsupportedEncodingException e) {
			// ignore
		}
		return error;

	}

	@Override
	public String toString() {
		return "RspError [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
	
	public static RspError buildConnectError() {
		RspError loginError = new RspError();
		loginError.setErrorCode(9999);
		loginError.setErrorMsg("超过重试次数，连接失败");
		return loginError;
	}
	
	public static RspError buildRecieveMessageError() {
		RspError loginError = new RspError();
		loginError.setErrorCode(9999);
		loginError.setErrorMsg("消息超时");
		return loginError;
	}
	
	public static RspError buildConnectionLostError() {
		RspError loginError = new RspError();
		loginError.setErrorCode(9999);
		loginError.setErrorMsg("连接已断开，请重新登录");
		return loginError;
	}
	
}
