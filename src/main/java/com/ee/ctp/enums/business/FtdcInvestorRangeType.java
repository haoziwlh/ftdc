package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:49:18
 *
 */
public enum FtdcInvestorRangeType {
	/**
	 * 所有
	 */
	THOST_IR_All("1"),
	/**
	 * 投资者组
	 */
	THOST_IR_Group("2"),
	/**
	 * 单一投资者
	 */
	THOST_IR_Single("3");
	
	private String type;
	private FtdcInvestorRangeType(String type) {
		this.type = type;
	}
	
	public String type() {
		return this.type;
	}
	
	public static FtdcInvestorRangeType parseFrom(String type) {
		FtdcInvestorRangeType retType = null;
		for (FtdcInvestorRangeType t : FtdcInvestorRangeType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
 }
