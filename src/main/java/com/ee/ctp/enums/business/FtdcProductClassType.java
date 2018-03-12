package com.ee.ctp.enums.business;

/**
 * 产品类型类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcProductClassType {
	/**
	 * 期货
	 */
	THOST_FTDC_PC_Futures("1"),
	/**
	 * 期货期权
	 */
	THOST_FTDC_PC_Options("2"),
	/**
	 * 组合
	 */
	THOST_FTDC_PC_Combination("3"),
	/**
	 * 即期
	 */
	THOST_FTDC_PC_Spot("4"),
	/**
	 * 期转现
	 */
	THOST_FTDC_PC_EFP("5"),
	/**
	 * 现货期权
	 */
	THOST_FTDC_PC_SpotOption("6");
	
	private String type;
	private FtdcProductClassType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcProductClassType parseFrom(String type) {
		FtdcProductClassType retType = null;
		for (FtdcProductClassType t : FtdcProductClassType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
