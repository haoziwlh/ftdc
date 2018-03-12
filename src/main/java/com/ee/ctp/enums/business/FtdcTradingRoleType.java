package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:08:38
 *
 */
public enum FtdcTradingRoleType {
	/**
	 * 代理
	 */
	THOST_FTDCER_Broker("1"),
	/**
	 * 自营
	 */
	THOST_FTDCER_Host("2"),
	/**
	 * 做市商
	 */
	THOST_FTDCER_Maker("3");
	
	private String type;
	private FtdcTradingRoleType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcTradingRoleType parseFrom(String type) {
		FtdcTradingRoleType retType = null;
		for (FtdcTradingRoleType t : FtdcTradingRoleType.values()) {
			if(t.type().equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
