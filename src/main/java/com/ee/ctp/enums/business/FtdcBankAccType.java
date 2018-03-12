package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:34:51
 *
 */
public enum FtdcBankAccType {
	/**
	 * 银行存折
	 */
	THOST_FTDCBAT_BankBook("1"),
	/**
	 * 储蓄卡 
	 */
	THOST_FTDCBAT_SavingCard("2"),
	/**
	 * 信用卡
	 */
	THOST_FTDCBAT_CreditCard("3");
	
	private String type;
	private FtdcBankAccType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}

	public static FtdcBankAccType parseFrom(String type) {
		FtdcBankAccType retType = null;
		for (FtdcBankAccType t : FtdcBankAccType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
