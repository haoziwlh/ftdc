package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:06:37
 *
 */
public enum FtdcTradeSourceType {
	/**
	 * 来自交易所普通回报
	 */
	THOST_FTDCTSRC_NORMAL("0"),
	/**
	 * 来自查询
	 */
	THOST_FTDCTSRC_QUERY("1");
	
	private String type;
	private FtdcTradeSourceType(String type) {
		this.type = type;
	}
	
	public static FtdcTradeSourceType parseFrom(String type) {
		FtdcTradeSourceType retType = null;
		for (FtdcTradeSourceType t : FtdcTradeSourceType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
	
}
