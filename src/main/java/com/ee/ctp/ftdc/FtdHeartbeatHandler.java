package com.ee.ctp.ftdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.dto.HeartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:27:13
 *
 */
public class FtdHeartbeatHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(FtdHeartbeatHandler.class);
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
    		IdleStateEvent event = (IdleStateEvent) evt;
    		if(event.state() == IdleState.READER_IDLE) {
    			//超过约定时间没有收到数据认为链路有问题
    			logger.warn("not recieve heartbeat , exceed {}", ApplicationRuntime.conf().getFtdcReaderIdle());
    			ctx.close();
    		}else if(event.state() == IdleState.WRITER_IDLE) {
    			//超过约定时间没有写数据发心跳保活
    			ctx.writeAndFlush(HeartBeat.getHeartBeat());
    		}
		}
	}
}
