package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:55:50
 *
 */
public enum FtdcOrderStatusType {
	/**
	 * 全部成交
	 */
	THOST_FTDCOST_AllTraded("0"),
	/**
	 * 部分成交还在队列中
	 */
	THOST_FTDCOST_PartTradedQueueing("1"),
	/**
	 * 部分成交不在队列中
	 */
	THOST_FTDCOST_PartTradedNotQueueing("2"),
	/**
	 * 未成交还在队列中
	 */
	THOST_FTDCOST_NoTradeQueueing("3"),
	/**
	 * 未成交不在队列中
	 */
	THOST_FTDCOST_NoTradeNotQueueing("4"),
	/**
	 * 撤单
	 */
	THOST_FTDCOST_Canceled("5"),
	/**
	 * 未知
	 */
	THOST_FTDCOST_Unknown("a"),
	/**
	 * 尚未触发
	 */
	THOST_FTDCOST_NotTouched("b"),
	/**
	 * 已触发
	 */
	THOST_FTDCOST_Touched("c");
	
	private String status;
	private FtdcOrderStatusType(String status) {
		this.status = status;
	}
	
	public String status() {
		return this.status;
	}
	
	public static FtdcOrderStatusType parseFrom(String status) {
		FtdcOrderStatusType retStatus = null;
		switch (status) {
		case "0":
			retStatus = THOST_FTDCOST_AllTraded;
			break;
		case "1":
			retStatus = THOST_FTDCOST_PartTradedQueueing;
			break;
		case "2":
			retStatus = THOST_FTDCOST_PartTradedNotQueueing;
			break;
		case "3":
			retStatus = THOST_FTDCOST_NoTradeQueueing;
			break;
		case "4":
			retStatus = THOST_FTDCOST_NoTradeNotQueueing;
			break;
		case "5":
			retStatus = THOST_FTDCOST_Canceled;
			break;
		case "a":
			retStatus = THOST_FTDCOST_Unknown;
			break;
		case "b":
			retStatus = THOST_FTDCOST_NotTouched;
			break;
		case "c":
			retStatus = THOST_FTDCOST_Touched;
			break;
		default:
			break;
		}
		return retStatus;
	}
}
