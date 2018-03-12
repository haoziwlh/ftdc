package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:59:37
 *
 */
public enum FtdcOrderType {
	/**
	 * 正常
	 */
	THOST_FTDCORDT_Normal("0"),
	/**
	 * 报价衍生
	 */
	THOST_FTDCORDT_DeriveFromQuote("1"),
	/**
	 * 组合衍生
	 */
	THOST_FTDCORDT_DeriveFromCombination("2"),
	/**
	 * 组合报单
	 */
	THOST_FTDCORDT_Combination("3"),
	/**
	 * 条件单
	 */
	THOST_FTDCORDT_ConditionalOrder("4"),
	/**
	 * 互换单
	 */
	THOST_FTDCORDT_Swap("5");
	
	private String type;
	private FtdcOrderType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcOrderType parseFrom(String type) {
		FtdcOrderType retType = null;
		switch (type) {
		case "0":
			retType = THOST_FTDCORDT_Normal;
			break;
		case "1":
			retType = THOST_FTDCORDT_DeriveFromQuote;
			break;
		case "2":
			retType = THOST_FTDCORDT_DeriveFromCombination;
			break;
		case "3":
			retType = THOST_FTDCORDT_Combination;
			break;
		case "4":
			retType = THOST_FTDCORDT_ConditionalOrder;
			break;
		case "5":
			retType = THOST_FTDCORDT_Swap;
			break;
		default:
			break;
		}
		return retType;
	}
}
