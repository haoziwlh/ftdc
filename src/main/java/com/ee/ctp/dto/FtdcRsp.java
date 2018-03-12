package com.ee.ctp.dto;

import io.netty.buffer.ByteBuf;
/**
 * ftdc rsp
 * @author ee
 * 2017年11月9日 下午5:12:16
 *
 */
public interface FtdcRsp {
	/**
	 * 反序列化
	 * @param body
	 * @param error
	 * @return
	 */
	FtdcRsp parseFrom(ByteBuf body, RspError error);
}
