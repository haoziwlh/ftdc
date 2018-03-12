package com.ee.ctp.enums;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:49:39
 *
 */
public enum FtdcType {
	/**
	 * 请求
	 */
	REQ(0),
	/**
	 * 应答
	 */
	RSP(3),
	/**
	 * 请求应答都兼容
	 */
	REQ_RSP(-1);
	
	private int type;
	private FtdcType(int type) {
		this.type = type;
	}
	
	public int type() {
		return type;
	}
}
