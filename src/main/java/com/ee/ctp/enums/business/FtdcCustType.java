package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午7:38:59
 *
 */
public enum FtdcCustType {
	/**
	 * 自然人
	 */
	THOST_FTDC_CUSTT_Person("0"),
	/**
	 * 机构户
	 */
	THOST_FTDC_CUSTT_Institution("1");
	
	private String type;
	private FtdcCustType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}

	public static FtdcCustType parseFrom(String type) {
		FtdcCustType retType = null;
		for (FtdcCustType t : FtdcCustType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
