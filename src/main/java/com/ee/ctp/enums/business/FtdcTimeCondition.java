package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午8:05:12
 *
 */
public enum FtdcTimeCondition {
	/**
	 * 立即完成，否则撤销
	 */
	FTDC_IOC("1"),
	/**
	 * 本节有效
	 */
	FTDC_GFS("2"),
	/**
	 * 当日有效
	 */
	FTDC_GFD("3"),
	/**
	 * 指定日期前有效
	 */
	FTDC_GTD("4"),
	/**
	 * 撤销前有效
	 */
	FTDC_GTC("5"),
	/**
	 * 集合竞价有效
	 */
	FTDC_GFA("6");
	
	private String codition;

	private FtdcTimeCondition(String codition) {
		this.codition = codition;
	}

	public String getCodition() {
		return codition;
	}
	
	public static FtdcTimeCondition parseFrom(String flag) {
		FtdcTimeCondition retFlag = null;
		switch(flag) {
		case "1":
			retFlag = FTDC_IOC;
			break;
		case "2":
			retFlag = FTDC_GFS;
			break;
		case "3":
			retFlag = FTDC_GFD;
			break;
		case "4":
			retFlag = FTDC_GTD;
			break;
		case "5":
			retFlag = FTDC_GTC;
			break;
		case "6":
			retFlag = FTDC_GFA;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
