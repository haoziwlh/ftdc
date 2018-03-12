package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:50:37
 *
 */
public enum FtdcOffsetFlagType {
	/**
	 * 开仓
	 */
	FTDC_Open("0"),
	/**
	 * 平仓
	 */
	FTDC_Close("1"),
	/**
	 * 强平
	 */
	FTDC_ForceClose("2"),
	/**
	 * 平今
	 */
	FTDC_CloseToday("3"),
	/**
	 * 平昨
	 */
	FTDC_CloseYesterday("4"),
	/**
	 * 强减
	 */
	FTDC_ForceOff("5"),
	/**
	 * 本地强平
	 */
	FTDC_LocalForceClose("6");
	
	private String offset;

	private FtdcOffsetFlagType(String offset) {
		this.offset = offset;
	}

	public String getOffset() {
		return offset;
	}
	
	public static FtdcOffsetFlagType parseFrom(String flag) {
		FtdcOffsetFlagType retFlag = null;
		switch(flag) {
		case "0":
			retFlag = FTDC_Open;
			break;
		case "1":
			retFlag = FTDC_Close;
			break;
		case "2":
			retFlag = FTDC_ForceClose;
			break;
		case "3":
			retFlag = FTDC_CloseToday;
			break;
		case "4":
			retFlag = FTDC_CloseYesterday;
			break;
		case "5":
			retFlag = FTDC_ForceOff;
			break;
		case "6":
			retFlag = FTDC_LocalForceClose;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
