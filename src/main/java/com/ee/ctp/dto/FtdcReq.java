package com.ee.ctp.dto;

import io.netty.buffer.ByteBuf;
/**
 * ftdc req
 * @author ee
 * 2017年11月9日 下午5:11:50
 *
 */
public interface FtdcReq {
	/**
	 * 序列化
	 * @param buffer
	 * @return
	 */
	ByteBuf write(ByteBuf buffer);
}
