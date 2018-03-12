package com.ee.ctp.enums.business;
/**
 * 
 * @author wyz
 * 2017年10月17日 下午7:49:27
 *
 */
public enum FtdcLastFragmentType {
	/**
	 * 是最后分片
	 */
	THOST_FTDC_LF_Yes("0"),
	/**
	 * 不是最后分片
	 */
	THOST_FTDC_LF_No("1");
	
	private String type;
	private FtdcLastFragmentType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}


	public static FtdcLastFragmentType parseFrom(String type) {
		FtdcLastFragmentType retType = null;
		for (FtdcLastFragmentType t : FtdcLastFragmentType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
