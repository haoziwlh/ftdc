package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:50:45
 *
 */
public enum FtdcOpenOrDestroyType {
	/**
	 * 开户
	 */
	THOST_FTDC_OOD_Destroy("0"),
	/**
	 * 销户
	 */
	THOST_FTDC_OOD_Open("1");
	
	private String type;
	private FtdcOpenOrDestroyType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcOpenOrDestroyType parseFrom(String type) {
		FtdcOpenOrDestroyType retType = null;
		for (FtdcOpenOrDestroyType t : FtdcOpenOrDestroyType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
