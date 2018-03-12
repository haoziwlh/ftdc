package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午7:38:23
 *
 */
public enum FtdcContingentCondition {

	/**
	 * 立即
	 */
	FTDC_Immediately("1"),
	/**
	 * 止损
	 */
	FTDC_Touch("2"),
	/**
	 * 止赢
	 */
	FTDC_TouchProfit("3"),
	/**
	 * 预埋单
	 */
	FTDC_ParkedOrder("4"),
	/**
	 * 最新价大于条件价
	 */
	FTDC_LastPriceGreaterThanStopPrice("5"),
	/**
	 * 最新价大于等于条件价
	 */
	FTDC_LastPriceGreaterEqualStopPrice("6"),
	/**
	 * 最新价小于条件价
	 */
	FTDC_LastPriceLesserThanStopPrice("7"),
	/**
	 * 最新价小于等于条件价
	 */
	FTDC_LastPriceLesserEqualStopPrice("8"),
	/**
	 * 卖一价大于条件价
	 */
	FTDC_AskPriceGreaterThanStopPrice("9"),
	/**
	 * 卖一价大于等于条件价
	 */
	FTDC_AskPriceGreaterEqualStopPrice("A"),
	/**
	 * 卖一价小于条件价
	 */
	FTDC_AskPriceLesserThanStopPrice("B"),
	/**
	 * 卖一价小于等于条件价
	 */
	FTDC_AskPriceLesserEqualStopPrice("C"),
	/**
	 * 买一价大于条件价
	 */
	FTDC_BidPriceGreaterThanStopPrice("D"),
	/**
	 * 买一价大于等于条件价
	 */
	FTDC_BidPriceGreaterEqualStopPrice("E"),
	/**
	 * 买一价小于条件价
	 */
	FTDC_BidPriceLesserThanStopPrice("F"),
	/**
	 * 买一价小于等于条件价
	 */
	FTDC_BidPriceLesserEqualStopPrice("H");
	
	private String contingent;

	private FtdcContingentCondition(String contingent) {
		this.contingent = contingent;
	}

	public String getContingent() {
		return contingent;
	}
	
	public static FtdcContingentCondition parseFrom(String flag) {
		FtdcContingentCondition retFlag = null;
		switch(flag) {
		case "1":
			retFlag = FTDC_Immediately;
			break;
		case "2":
			retFlag = FTDC_Touch;
			break;
		case "3":
			retFlag = FTDC_TouchProfit;
			break;
		case "4":
			retFlag = FTDC_ParkedOrder;
			break;
		case "5":
			retFlag = FTDC_LastPriceGreaterThanStopPrice;
			break;
		case "6":
			retFlag = FTDC_LastPriceGreaterEqualStopPrice;
			break;
		case "7":
			retFlag = FTDC_LastPriceLesserThanStopPrice;
			break;
		case "8":
			retFlag = FTDC_LastPriceLesserEqualStopPrice;
			break;
		case "9":
			retFlag = FTDC_AskPriceGreaterThanStopPrice;
			break;
		case "A":
			retFlag = FTDC_AskPriceGreaterEqualStopPrice;
			break;
		case "B":
			retFlag = FTDC_AskPriceLesserThanStopPrice;
			break;
		case "C":
			retFlag = FTDC_AskPriceLesserEqualStopPrice;
			break;
		case "D":
			retFlag = FTDC_BidPriceGreaterThanStopPrice;
			break;
		case "E":
			retFlag = FTDC_BidPriceGreaterEqualStopPrice;
			break;
		case "F":
			retFlag = FTDC_BidPriceLesserThanStopPrice;
			break;
		case "H":
			retFlag = FTDC_BidPriceLesserEqualStopPrice;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
