package com.ee.ctp.enums.business;
/**
 * 
 * @author lyb
 * 2017年10月17日 下午8:09:13
 *
 */
public enum FtdcTransferStatusType {
	/**
	 * 正常
	 */
	THOST_FTDC_TRFS_Normal("0"),
	/**
	 * 做冲正
	 */
	THOST_FTDC_TRFS_Repealed("1");
	
	private String type;
	private FtdcTransferStatusType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}

	public static FtdcTransferStatusType parseFrom(String type) {
		FtdcTransferStatusType retType = null;
		for (FtdcTransferStatusType t : FtdcTransferStatusType.values()) {
			if(t.type.equals(type)) {
				retType = t;
				break;
			}
		}
		return retType;
	}
}
