package com.ee.ctp.ftdc;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:26:22
 *
 */
public class FtdcEncoder extends MessageToMessageEncoder<FtdcProtocol>{

	@Override
	protected void encode(ChannelHandlerContext ctx, FtdcProtocol msg, List<Object> out) throws Exception {
		ByteBuf buffer = null;
		try {
			buffer = ctx.alloc().buffer();
			msg.write(buffer.retain());
			out.add(buffer.retain());
		} finally {
			ReferenceCountUtil.release(buffer);
		}
	}

}
