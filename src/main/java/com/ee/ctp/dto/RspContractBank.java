package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:18:53
 *
 */
public class RspContractBank implements FtdcRsp{
	// 11
	private String brokerID;
	// 4
	private String bankID;
	// 5
	private String bankBrchID;
	// 101
	private String bankName;

	
	public String getBrokerID() {
		return brokerID;
	}


	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}


	public String getBankID() {
		return bankID;
	}


	public void setBankID(String bankID) {
		this.bankID = bankID;
	}


	public String getBankBrchID() {
		return bankBrchID;
	}


	public void setBankBrchID(String bankBrchID) {
		this.bankBrchID = bankBrchID;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	@Override
	public RspContractBank parseFrom(ByteBuf body, RspError error) {
		try {
			RspContractBank info = new RspContractBank();
			byte[] brokerID = new byte[11];
			body.readBytes(brokerID);
			info.setBrokerID(StringUtils.trimToEmpty(new String(brokerID)));
			
			byte[] bankId = new byte[4];
			body.readBytes(bankId);
			info.setBankID(StringUtils.trimToEmpty(new String(bankId)));
			
			byte[] bankBrchID = new byte[5];
			body.readBytes(bankBrchID);
			info.setBankBrchID(StringUtils.trimToEmpty(new String(bankBrchID)));
			byte[] bankName = new byte[101];
			body.readBytes(bankName);
			try {
				info.setBankName(StringUtils.trimToEmpty(new String(bankName, ApplicationRuntime.conf().defaultEncoding())));
			} catch (UnsupportedEncodingException e) {
				// ignore
			}
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}


	@Override
	public String toString() {
		return "RspContractBank [brokerID=" + brokerID + ", bankID=" + bankID + ", bankBrchID=" + bankBrchID
				+ ", bankName=" + bankName + "]";
	}
	
	
}
