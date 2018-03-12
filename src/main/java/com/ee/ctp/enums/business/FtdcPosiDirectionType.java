package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:00:27
 *
 */
public enum FtdcPosiDirectionType {
	/**
	 * 净
	 */
	THOST_FTDCPD_Net("1"),
	/**
	 * 多头
	 */
	THOST_FTDCPD_Long("2"),
	/**
	 * 空头
	 */
	THOST_FTDCPD_Short("3");
	
	private String direction;
	private FtdcPosiDirectionType(String direction) {
		this.direction = direction;
	}
	
	public String direction() {
		return this.direction;
	}
	
	public static FtdcPosiDirectionType parseFrom(String direction) {
		FtdcPosiDirectionType retDirection = null;
		for (FtdcPosiDirectionType type : FtdcPosiDirectionType.values()) {
			if(type.direction().equals(direction)) {
				retDirection = type;
				break;
			}
		}
		return retDirection;
	}
}
