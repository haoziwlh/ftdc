package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:52:22
 *
 */
public enum FtdcOrderSourceType {
	/**
	 * 来自参与者
	 */
	THOST_FTDCOSRC_Participant("0"),
	/**
	 * 来自管理员
	 */
	THOST_FTDCOSRC_Administrator("1");
	
	private String source;
	private FtdcOrderSourceType(String source) {
		this.source = source;
	}
	
	public String source() {
		return this.source;
	}
	
	public static FtdcOrderSourceType parseFrom(String source) {
		FtdcOrderSourceType retSource = null;
		switch(source) {
		case "0":
			retSource = THOST_FTDCOSRC_Participant;
			break;
		case "1":
			retSource = THOST_FTDCOSRC_Administrator;
			break;
		default:
			break;
		}
		return retSource;
	}
}
