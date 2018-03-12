package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:34:39
 *
 */
public enum FtdcAvailabilityFlagType {
	/**
	 * 未确认
	 */
	THOST_FTDC_Invalid("0"),
	/**
	 * 有效
	 */
	THOST_FTDC_Valid("1"),
	/**
	 * 冲正
	 */
	THOST_FTDC_Repeal("2");
	
	private String type;
	private FtdcAvailabilityFlagType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcAvailabilityFlagType parseFrom(String type) {
		FtdcAvailabilityFlagType retType = null;
		for (FtdcAvailabilityFlagType t : FtdcAvailabilityFlagType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
	
}
