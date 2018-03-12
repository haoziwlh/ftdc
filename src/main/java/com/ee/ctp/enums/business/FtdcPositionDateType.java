package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:00:40
 *
 */
public enum FtdcPositionDateType {
	/**
	 * 今日持仓
	 */
	THOST_FTDCPSD_Today("1"),
	/**
	 * 历史持仓
	 */
	THOST_FTDCPSD_History("2");
	
	private String type;
	private FtdcPositionDateType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcPositionDateType parseFrom(String direction) {
		FtdcPositionDateType retType = null;
		for (FtdcPositionDateType type : FtdcPositionDateType.values()) {
			if(type.type().equals(direction)) {
				retType = type;
				break;
			}
		}
		return retType;
	}
}
