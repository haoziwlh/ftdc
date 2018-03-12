package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:02:19
 *
 */
public enum FtdcPriceSourceType {
	/**
	 * 前成交价
	 */
	THOST_FTDCPSRC_LastPrice("0"),
	/**
	 * 买委托价
	 */
	THOST_FTDCPSRC_Buy("1"),
	/**
	 * 卖委托价
	 */
	THOST_FTDCPSRC_Sell("2");
	
	private String type;
	private FtdcPriceSourceType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcPriceSourceType parseFrom(String type) {
		FtdcPriceSourceType retType = null;
		for (FtdcPriceSourceType t : FtdcPriceSourceType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
