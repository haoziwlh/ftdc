package com.ee.ctp.enums.business;

/**
 * 合约生命周期状态类型
 * @author zlj
 * @date 2017年10月13日
 * 
 * @modifyBy zlj 2017年10月13日
 */
public enum FtdcInstLifePhaseType {
	/**
	 * 未上市
	 */
	THOST_FTDC_IP_NotStart("0"),
	/**
	 * 上市
	 */
	THOST_FTDC_IP_Started("1"),
	/**
	 * 停牌
	 */
	THOST_FTDC_IP_Pause("2"),
	/**
	 * 到期
	 */
	THOST_FTDC_IP_Expired("3");
	
	private String type;
	private FtdcInstLifePhaseType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcInstLifePhaseType parseFrom(String type) {
		FtdcInstLifePhaseType retType = null;
		for (FtdcInstLifePhaseType t : FtdcInstLifePhaseType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
