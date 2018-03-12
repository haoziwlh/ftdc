package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午7:51:58
 *
 */
public enum FtdcOrderPriceType {

	/**
	 * 任意价
	 */
	FTDC_AnyPrice("1"),
	/**
	 * 限价
	 */
	FTDC_LimitPrice("2"),
	/**
	 * 最优价
	 */
	FTDC_BestPrice("3"),
	/**
	 * 最新价
	 */
	FTDC_LastPrice("4"),
	/**
	 * 最新价浮动上浮1个ticks
	 */
	FTDC_LastPricePlusOneTicks("5"),
	/**
	 * 最新价浮动上浮2个ticks
	 */
	FTDC_LastPricePlusTwoTicks("6"),
	/**
	 * 最新价浮动上浮3个ticks
	 */
	FTDC_LastPricePlusThreeTicks("7"),
	/**
	 * 卖一价
	 */
	FTDC_AskPrice1("8"),
	/**
	 * 卖一价浮动上浮1个ticks
	 */
	FTDC_AskPrice1PlusOneTicks("9"),
	/**
	 * 卖一价浮动上浮2个ticks
	 */
	FTDC_AskPrice1PlusTwoTicks("A"),
	/**
	 * 卖一价浮动上浮3个ticks
	 */
	FTDC_AskPrice1PlusThreeTicks("B"),
	/**
	 * 买一价
	 */
	FTDC_BidPrice1("C"),
	/**
	 * 买一价浮动上浮1个ticks
	 */
	FTDC_BidPrice1PlusOneTicks("D"),
	/**
	 * 买一价浮动上浮2个ticks
	 */
	FTDC_BidPrice1PlusTwoTicks("E"),
	/**
	 * 买一价浮动上浮3个ticks
	 */
	FTDC_BidPrice1PlusThreeTicks("F");
	
	private String type;

	private FtdcOrderPriceType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static FtdcOrderPriceType parseFrom(String type) {
		FtdcOrderPriceType retType = null;
		switch(type) {
		case "1":
			retType = FTDC_AnyPrice;
			break;
		case "2":
			retType = FTDC_LimitPrice;
			break;
		case "3":
			retType = FTDC_BestPrice;
			break;
		case "4":
			retType = FTDC_LastPrice;
			break;
		case "5":
			retType = FTDC_LastPricePlusOneTicks;
			break;
		case "6":
			retType = FTDC_LastPricePlusTwoTicks;
			break;
		case "7":
			retType = FTDC_LastPricePlusThreeTicks;
			break;
		case "8":
			retType = FTDC_AskPrice1;
			break;
		case "9":
			retType = FTDC_AskPrice1PlusOneTicks;
			break;
		case "A":
			retType = FTDC_AskPrice1PlusTwoTicks;
			break;
		case "B":
			retType = FTDC_AskPrice1PlusThreeTicks;
			break;
		case "C":
			retType = FTDC_BidPrice1;
			break;
		case "D":
			retType = FTDC_BidPrice1PlusOneTicks;
			break;
		case "E":
			retType = FTDC_BidPrice1PlusTwoTicks;
			break;
		case "F":
			retType = FTDC_BidPrice1PlusThreeTicks;
			break;
		default:
			break;
		}
		return retType;
	}
	
}
