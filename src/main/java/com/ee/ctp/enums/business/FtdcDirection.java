package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:39:31
 *
 */
public enum FtdcDirection {
	/**
	 * 买
	 */
	BUY("0"),
	/**
	 * 卖
	 */
	SELL("1");
	
	private String direction;
	
	private FtdcDirection(String direction) {
		this.direction = direction;
	}
	public String getDirection() {
		return direction;
	}
	
	public static FtdcDirection parseFrom(String direction) {
		FtdcDirection retDirection = null;
		switch(direction) {
		case "0":
			retDirection = FtdcDirection.BUY;
			break;
		case "1":
			retDirection = FtdcDirection.SELL;
			break;
		default:
			break;
		}
		return retDirection;
	}
}
