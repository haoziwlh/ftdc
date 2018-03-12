package com.ee.ctp.enums.business;

/**
 * 大额单边保证金算法类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcMaxMarginSideAlgorithmType {
	/**
	 * 不使用大额单边保证金算法
	 */
	THOST_FTDC_MMSA_NO("0"),
	/**
	 * 使用大额单边保证金算法
	 */
	THOST_FTDC_MMSA_YES("1");
	
	private String type;
	private FtdcMaxMarginSideAlgorithmType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcMaxMarginSideAlgorithmType parseFrom(String type) {
		FtdcMaxMarginSideAlgorithmType retType = null;
		for (FtdcMaxMarginSideAlgorithmType t : FtdcMaxMarginSideAlgorithmType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
