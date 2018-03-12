package com.ee.ctp.enums.business;

/**
 * 期权类型类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcOptionsType {
	/**
	 * 看涨
	 */
	THOST_FTDC_CP_CallOptions("1"),
	/**
	 * 看跌
	 */
	THOST_FTDC_CP_PutOptions("2");
	
	private String type;
	private FtdcOptionsType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcOptionsType parseFrom(String type) {
		FtdcOptionsType retType = null;
		for (FtdcOptionsType t : FtdcOptionsType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
