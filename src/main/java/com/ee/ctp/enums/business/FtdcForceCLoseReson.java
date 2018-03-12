package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:41:04
 *
 */
public enum FtdcForceCLoseReson {
	/**
	 * 非强平
	 */
	THOST_FTDCFCC_NotForceClose("0"),
	/**
	 * 资金不足
	 */
	THOST_FTDCFCC_LackDeposit("1"),
	/**
	 * 客户超仓
	 */
	THOST_FTDCFCC_ClientOverPositionLimit("2"),
	/**
	 * 会员超仓
	 */
	THOST_FTDCFCC_MemberOverPositionLimit("3"),
	/**
	 * 持仓非整数倍
	 */
	THOST_FTDCFCC_NotMultiple("4"),
	/**
	 * 违规
	 */
	THOST_FTDCFCC_Violation("5"),
	/**
	 * 其它
	 */
	THOST_FTDCFCC_Other("6"),
	/**
	 * 自然人临近交割
	 */
	THOST_FTDCFCC_PersonDeliv("7");
	
	private String type;
	private FtdcForceCLoseReson(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcForceCLoseReson parseFrom(String reson) {
		FtdcForceCLoseReson retReason = null;
		switch(reson) {
		case "0":
			retReason = THOST_FTDCFCC_NotForceClose;
			break;
		case "1":
			retReason = THOST_FTDCFCC_LackDeposit;
			break;
		case "2":
			retReason = THOST_FTDCFCC_ClientOverPositionLimit;
			break;
		case "3":
			retReason = THOST_FTDCFCC_MemberOverPositionLimit;
			break;
		case "4":
			retReason = THOST_FTDCFCC_NotMultiple;
			break;
		case "5":
			retReason = THOST_FTDCFCC_Violation;
			break;
		case "6":
			retReason = THOST_FTDCFCC_Other;
			break;
		case "7":
			retReason = THOST_FTDCFCC_PersonDeliv;
			break;
		default:
			break;
		}
		return retReason;
	}
	
}
