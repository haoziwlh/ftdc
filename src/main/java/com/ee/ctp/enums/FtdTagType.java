package com.ee.ctp.enums;
/**
 * ftd扩展报文头类型
 * @author ee
 * 2017年10月17日 下午5:57:54
 *
 */
public enum FtdTagType {
	/**
	 * 无
	 */
	FTDTagNone(0x00, 0),
	/**
	 * 时间戳
	 */
	FTDTagDatetime(0x01, 4),
	/**
	 * 信息正文压缩
	 */
	FTDTagCompressMethod(0x02, 1),
	/**
	 * 发送端状态
	 */
	FTDTagSessionState(0x03, 1),
	/**
	 * 心跳
	 */
	FTDTagKeepAlive(0x05, 0),
	/**
	 * 交易所当前交易日期
	 */
	FTDTagTradedate(0x06, 4),
	/**
	 * 报文目标
	 */
	FTDTagTarget(0x07, 2);

	private int type;
	private int length;

	private FtdTagType(int type, int length) {
		this.type = type;
		this.length = length;
	}

	public int type() {
		return type;
	}

	public int length() {
		return length;
	}

	public static FtdTagType parseFrom(int type) {
		switch (type) {
		case 0:
			return FTDTagNone;
		case 1:
			return FTDTagDatetime;
		case 2:
			return FTDTagCompressMethod;
		case 3:
			return FTDTagSessionState;
		case 5:
			return FTDTagKeepAlive;
		case 6:
			return FTDTagTradedate;
		case 7:
			return FTDTagTarget;
		default:
			throw new IllegalArgumentException();
		}
	}
}
