package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:09:48
 *
 */
public enum FtdcVolumeCondition {
	/**
	 * 任何数量
	 */
	HOST_AV("1"),
	/**
	 * 最小数量
	 */
	HOST_MV("2"),
	/**
	 * 全部数量
	 */
	HOST_CV("3");
	
	private String vCondition;

	private FtdcVolumeCondition(String vCondition) {
		this.vCondition = vCondition;
	}

	public String getvCondition() {
		return vCondition;
	}
	
	public static FtdcVolumeCondition parseFrom(String flag) {
		FtdcVolumeCondition retFlag = null;
		switch(flag) {
		case "1":
			retFlag = HOST_AV;
			break;
		case "2":
			retFlag = HOST_MV;
			break;
		case "3":
			retFlag = HOST_CV;
			break;
		default:
			break;
		}
		return retFlag;
	}
}
