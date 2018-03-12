package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:35:38
 *
 */
public enum FtdcBillHedgeFlag {
	/**
	 * 投机
	 */
	FTDC_Speculation("1"),
	/**
	 * 套利
	 */
	FTDC_Arbitrage("2"),
	/**
	 * 套保
	 */
	FTDC_Hedge("3");
	
	private String flag;

	private FtdcBillHedgeFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}
	
	public static FtdcBillHedgeFlag parseFrom(String flag) {
		FtdcBillHedgeFlag retFlag = null;
		switch(flag) {
		case "1":
			retFlag = FTDC_Speculation;
			break;
		case "2":
			retFlag = FTDC_Arbitrage;
			break;
		case "3":
			retFlag = FtdcBillHedgeFlag.FTDC_Hedge;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
