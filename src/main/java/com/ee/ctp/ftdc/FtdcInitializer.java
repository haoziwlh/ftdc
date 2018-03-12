package com.ee.ctp.ftdc;

import com.ee.ctp.ApplicationRuntime;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
/**
 * 仅用于ctp client
 * @author ee
 * 2017年10月17日 下午8:26:36
 *
 */
public class FtdcInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new FtdcDecoder());
		ch.pipeline().addLast(new FtdcEncoder());
		ch.pipeline().addLast(ApplicationRuntime.EEG, new FtdcHandler());
	}
	
}