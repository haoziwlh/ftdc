package com.ee.ctp.enums.business;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:38:43
 *
 */
public enum FtdcCurrencyID {
	/**
	 * 人民币
	 */
	CNY("CNY");
	
	private String id;

	private FtdcCurrencyID(String id) {
		this.id = id;
	}

	public int getId() {
		byte[] idBytes = id.getBytes();
		int retId = 0;
		for (int i = 0; i < idBytes.length; i++) {
			retId += (int)idBytes[i] << ((3 - i) * 8);
		}
		return retId;
	}
	
	public String id() {
		return this.id;
	}
	
	public static FtdcCurrencyID parseFrom(String id) {
		FtdcCurrencyID currencyId = null;
		switch(id) {
		case "CNY":
			currencyId = CNY;
			break;
		default:
			currencyId = CNY;
			break;
		}
		return currencyId;
	}
	
	public static FtdcCurrencyID parseFrom(int id) {
		FtdcCurrencyID currencyId = null;
		byte[] bs = new byte[Integer.SIZE / 8];
		for(int i = 0; i < bs.length; i++) {
			bs[i] = (byte)(id >> ((3 - i) * 8));
		}
		String str = StringUtils.trimToEmpty(new String(bs));
		switch(str) {
		case "CNY":
			currencyId = CNY;
			break;
		default:
			currencyId = CNY;
			break;
		}
		return currencyId;
	}
}
