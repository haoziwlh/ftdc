package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午7:39:45
 *
 */
public enum FtdcExchange {
	/**
	 * 上海期货交易所
	 */
	FTDC_SHFE("SHFE"),
	/**
	 * 郑州商品交易所
	 */
	FTDC_CZCE("CZCE"),
	/**
	 * 大连商品交易所
	 */
	FTDC_DCE("DCE"),
	/**
	 * 中国金融期货交易所
	 */
	FTDC_CFFEX("CFFEX");
	
	private String exchange;

	private FtdcExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExchange() {
		return exchange;
	}
	
	public static FtdcExchange parseFrom(String flag) {
		FtdcExchange retFlag = null;
		switch(flag) {
		case "SHFE":
			retFlag = FTDC_SHFE;
			break;
		case "CZCE":
			retFlag = FTDC_CZCE;
			break;
		case "DCE":
			retFlag = FTDC_DCE;
			break;
		case "CFFEX":
			retFlag = FTDC_CFFEX;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
