package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午6:25:59
 *
 */
public enum FtdcActionFlagType {
	/**
	 * 删除
	 */
	THOST_FTDCAF_Delete("0"),
	/**
	 * 修改
	 */
	THOST_FTDCAF_Modify("3");
	
	private String type;
	private FtdcActionFlagType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcActionFlagType parseFrom(String type) {
		FtdcActionFlagType retType = null;
		for (FtdcActionFlagType t : FtdcActionFlagType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
	
}
