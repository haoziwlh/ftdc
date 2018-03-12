package com.ee.ctp.enums.business;

/**
 * 持仓类型类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcPositionType {
	/**
	 * 净持仓
	 */
	THOST_FTDC_PT_Net("1"),
	/**
	 * 综合持仓
	 */
	THOST_FTDC_PT_Gross("2");
	
	private String type;
	private FtdcPositionType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcPositionType parseFrom(String type) {
		FtdcPositionType retType = null;
		for (FtdcPositionType t : FtdcPositionType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
