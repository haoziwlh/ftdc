package com.ee.ctp.enums;
/**
 * ftd报文类型
 * @author ee
 * 2017年10月17日 下午5:58:13
 *
 */
public enum FtdType {
	/**
	 * 无
	 */
	FTDTypeNone(0),
	/**
	 * 信息正文正常数据域
	 */
	FTDTypeFTDC(1),
	/**
	 * 信息正文压缩数据
	 */
	FTDTypeCompressed(2);
	
	private int type;
	private FtdType(int type) {
		this.type = type;
	}
	
	public int type() {
		return this.type;
	}
	
	public static FtdType parseFrom(int type) {
		switch(type) {
		case 0:
			return FTDTypeNone;
		case 1:
			return FTDTypeFTDC;
		case 2:
			return FTDTypeCompressed;
		default:
			throw new IllegalArgumentException("unknown type of " + type);
		}
	}
}
