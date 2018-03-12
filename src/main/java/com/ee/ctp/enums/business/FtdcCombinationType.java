package com.ee.ctp.enums.business;

/**
 * 组合类型类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcCombinationType {
	/**
	 * 期货组合
	 */
	THOST_FTDC_COMBT_Future("0"),
	/**
	 * 垂直价差BUL
	 */
	THOST_FTDC_COMBT_BUL("1"),
	/**
	 * 垂直价差BER
	 */
	THOST_FTDC_COMBT_BER("2"),
	/**
	 * 跨式组合
	 */
	THOST_FTDC_COMBT_STD("3"),
	/**
	 * 宽跨式组合
	 */
	THOST_FTDC_COMBT_STG("4"),
	/**
	 * 备兑组合
	 */
	THOST_FTDC_COMBT_PRT("5"),
	/**
	 * 时间价差组合
	 */
	THOST_FTDC_COMBT_CLD("6");
	
	private String type;
	private FtdcCombinationType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcCombinationType parseFrom(String type) {
		FtdcCombinationType retType = null;
		for (FtdcCombinationType t : FtdcCombinationType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
