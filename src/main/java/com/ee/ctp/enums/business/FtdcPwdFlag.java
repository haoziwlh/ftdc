package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:04:48
 *
 */
public enum FtdcPwdFlag {
	/**
	 * 不核对
	 */
	FTDC_NoCheck("0"),
	/**
	 * 明文核对
	 */
	FTDC_BlankCheck("1"),
	/**
	 * 密文核对
	 */
	FTDC_EncryptCheck("2");
	
	private String flag;

	private FtdcPwdFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}
	
}
