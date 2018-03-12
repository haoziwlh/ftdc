package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:44:02
 *
 */
public enum FtdcFutureAccType {
	/**
	 * 银行存折
	 */
	THOST_FTDC_BankBook("1"),
	/**
	 * 储蓄卡
	 */
	THOST_FTDC_SavingCard("2"),
	/**
	 * 信用卡
	 */
	THOST_FTDC_CreditCard("3");
	
	private String type;
	private FtdcFutureAccType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcFutureAccType parseFrom(String type) {
		FtdcFutureAccType retType = null;
		for (FtdcFutureAccType t : FtdcFutureAccType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
