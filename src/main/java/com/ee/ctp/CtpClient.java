package com.ee.ctp;

import com.ee.ctp.ftdc.FtdcInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * 单次连接端，仅仅用作测试
 * @author ee
 * 2017年10月17日 下午8:11:43
 *
 */
public class CtpClient {
	private CtpClient() {}

	/**
	 * 
	 * @param host 180.168.146.187
	 * @param port 10030|10000
	 * @throws Exception
	 */
	public static void ctp(String host, int port, ChannelFutureListener listener) throws Exception {
		NioEventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bs = new Bootstrap();
		bs.group(group);
		bs.channel(NioSocketChannel.class);
		bs.handler(new FtdcInitializer());
		
		ChannelFuture channelFuture = bs.connect(host, port);
		channelFuture.addListener(listener);
		channelFuture.sync();
	}
}
