package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:07:17
 *
 */
public enum FtdcTradeType {
	/**
	 * 组合持仓拆分为单一持仓,初始化不应包含该类型的持仓
	 */
	THOST_FTDCTRDT_SplitCombination("#"),
	/**
	 * 普通成交
	 */
	THOST_FTDCTRDT_Common("0"),
	/**
	 * 期权成交
	 */
	THOST_FTDCTRDT_OptionsExecution("1"),
	/**
	 * OTC成交
	 */
	THOST_FTDCTRDT_OTC("2"),
	/**
	 * 期转现衍生成交
	 */
	THOST_FTDCTRDT_EFPDerived("3"),
	/**
	 * 组合衍生成交
	 */
	THOST_FTDCTRDT_CombinationDerived("4");
	
	private String type;
	private FtdcTradeType(String type) {
		this.type = type;
	}
	

	public static FtdcTradeType parseFrom(String type) {
		FtdcTradeType retType = null;
		for (FtdcTradeType t : FtdcTradeType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
