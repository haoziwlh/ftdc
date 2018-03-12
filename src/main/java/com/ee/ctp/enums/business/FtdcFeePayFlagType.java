package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:40:52
 *
 */
public enum FtdcFeePayFlagType {
	/**
	 * 由受益方支付费用
	 */
	THOST_FTDC_FPF_BEN("0"),
	/**
	 * 由发送方支付费用
	 */
	THOST_FTDC_FPF_OUR("1"),
	/**
	 * 由发送方支付发起的费用，受益方支付接受的费用
	 */
	THOST_FTDC_FPF_SHA("2");
	
	private String type;
	private FtdcFeePayFlagType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}

	public static FtdcFeePayFlagType parseFrom(String type) {
		FtdcFeePayFlagType retType = null;
		for (FtdcFeePayFlagType t : FtdcFeePayFlagType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
