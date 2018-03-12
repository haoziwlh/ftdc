package com.ee.ctp.enums.business;

/**
 * 验证客户证件号码标志
 * @author lyb
 * 2017年10月17日 下午8:10:21
 *
 */
public enum FtdcYesNoIndicator {
	/**
	 * 是
	 */
	TDC_Yes("0"),
	/**
	 * 否
	 */
	TDC_No("1");
	
	private String flag;
	private FtdcYesNoIndicator(String flag) {
		this.flag = flag;
	}
	
	/**
	 * @return the type
	 */
	public String getFlag() {
		return flag;
	}
	
	public static FtdcYesNoIndicator parseFrom(String flag) {
		FtdcYesNoIndicator retType = null;
		for (FtdcYesNoIndicator t : FtdcYesNoIndicator.values()) {
			if(t.flag.equals(flag)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
