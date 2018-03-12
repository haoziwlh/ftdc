package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:05:35
 *
 */
public enum FtdcTradeCode {
	/**
	 * 银转期
	 */
	BankToFuture("202001"),
	/**
	 * 期转银
	 */
	FutureToBank("202002");
	
	private String code;

	private FtdcTradeCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
