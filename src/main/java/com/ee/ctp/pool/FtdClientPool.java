package com.ee.ctp.pool;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.ftdc.FtdHeartbeatHandler;
import com.ee.ctp.ftdc.FtdcDecoder;
import com.ee.ctp.ftdc.FtdcEncoder;
import com.ee.ctp.ftdc.FtdcHandler;
import com.ee.ctp.pool.SocketAddressChooserFactory.SocketAddressChooser;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelHealthChecker;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.FixedChannelPool.AcquireTimeoutAction;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:44:35
 *
 */
public class FtdClientPool {
	private static final Logger logger = LoggerFactory.getLogger(FtdClientPool.class);
	private NbtsChannelPoolMap pollMap;
	private static volatile FtdClientPool clientPool;
	private static final ConcurrentHashMap<Integer, SocketAddressChooser> CHOOSER_MAP = new ConcurrentHashMap<>();
	public static FtdClientPool getPool() {
		if(clientPool == null) {
			synchronized (FtdClientPool.class) {
				if(clientPool == null) {
					clientPool = new FtdClientPool();
				}
			}
		}
		return clientPool;
	}
	
	private FtdClientPool() {
		initPoolMap();
	}
	
	/**
	 * 循环取出可用连接一个使用
	 * @return
	 * @throws VerifyException
	 */
	public Future<Channel> acquire(List<ConnectAddrProperty> sas) {
		Verify.verify(pollMap != null && !sas.isEmpty(), "ftdc connection pool init failure or sas is null");
		SocketAddressChooser newChooser = DefaultSocketAddressChooserFactory.INSTANCE.newChooser(sas.size());
		SocketAddressChooser oldChooser = CHOOSER_MAP.putIfAbsent(sas.hashCode(), newChooser);
		if(oldChooser == null) {
			oldChooser = newChooser;
		}
		InetSocketAddress socketAddress = (InetSocketAddress)oldChooser.next(sas);
		if(logger.isDebugEnabled()) {
			logger.debug("use socket address {}", socketAddress);
		}
		FixedChannelPool fixedChannelPool = pollMap.get(socketAddress);
		return fixedChannelPool.acquire();
	}
	
	/**
	 * 释放连接
	 * @param channel
	 * @return
	 */
	public Future<Void> release(Channel channel) {
		Verify.verifyNotNull(channel, "channel不允许为NULL");
		InetSocketAddress remoteAddress = (InetSocketAddress)channel.remoteAddress();
		if(logger.isDebugEnabled()) {
			logger.debug("{} channel released", remoteAddress);
		}
		FixedChannelPool fixedChannelPool = pollMap.get(remoteAddress);
		Future<Void> releaseFuture = fixedChannelPool.release(channel);
		if(!releaseFuture.isSuccess()) {
			Throwable cause = releaseFuture.cause();
			if(cause != null) {
				logger.error("rlease local channel {}, remote channel {}, happens error {}", channel.localAddress(),
						channel.remoteAddress(), ExceptionUtils.getStackTrace(releaseFuture.cause()));
			}
		}
		return releaseFuture;
	}
	
	/**
	 * 根据配置初始化连接池
	 */
	private void initPoolMap() {
		this.pollMap = new NbtsChannelPoolMap(ApplicationRuntime.conf().getMaxConnections(), ApplicationRuntime.conf().getMaxPending());
	}
	
	/**
	 * 
	 * DESC:连接属性
	 *
	 * @author ee
	 * 2017年3月15日
	 */
	public static class ConnectAddrProperty {
		
		private InetSocketAddress socketAddress;
		private int priority;
		
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		public InetSocketAddress getSocketAddress() {
			return socketAddress;
		}
		public void setSocketAddress(InetSocketAddress socketAddress) {
			this.socketAddress = socketAddress;
		}
		
		@Override
		public int hashCode() {
			return socketAddress.hashCode();
		}
	}
	
	static class NbtsChannelPoolMap extends AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool> {

		private int maxConnections;
		private int maxPending;
		public NbtsChannelPoolMap(int maxConnections, int maxPending) {
			this.maxConnections = maxConnections;
			this.maxPending = maxPending;
		}
		@Override
		protected FixedChannelPool newPool(InetSocketAddress key) {
			return new FixedChannelPool(initBootStrap().remoteAddress(key), new InitChannelPoolHandler(), new DefaultChannelHealthChecker(),
	                AcquireTimeoutAction.NEW, 500, maxConnections, maxPending);
		}
		
		private static Bootstrap initBootStrap() {
			Bootstrap cb = new Bootstrap();
			Verify.verifyNotNull(cb);
			cb.group(ApplicationRuntime.FTDC_LOOP_GROUP);
			cb.channel(NioSocketChannel.class);
			cb.option(ChannelOption.SO_KEEPALIVE, false);
			return cb;
		}
		
	}
	
	static class DefaultChannelHealthChecker implements ChannelHealthChecker {

		@Override
		public Future<Boolean> isHealthy(Channel channel) {
			EventLoop loop = channel.eventLoop();
			if(!channel.isActive()) {
				logger.warn("local addr: {}, remote addr {} , channel not active, closed..", channel.localAddress(), channel.remoteAddress());
			}
			return channel.isActive() ? loop.newSucceededFuture(Boolean.TRUE) : loop.newSucceededFuture(Boolean.FALSE);
		}
		
	}
	
	static class InitChannelPoolHandler implements ChannelPoolHandler {
	    @Override
	    public void channelCreated(Channel ch) {
	    	ch.pipeline().addLast(new FtdcDecoder());
			ch.pipeline().addLast(new FtdcEncoder());
			ch.pipeline().addLast(new IdleStateHandler(ApplicationRuntime.conf().getFtdcReaderIdle(), ApplicationRuntime.conf().getFtdcWriterIdle(), ApplicationRuntime.conf().getFtdcAllIdle(), TimeUnit.SECONDS));
			ch.pipeline().addLast(new FtdHeartbeatHandler());
			ch.pipeline().addLast(ApplicationRuntime.EEG, new FtdcHandler());
	    }

	    @Override
	    public void channelReleased(Channel ch) {
	    	//ignore
	    }

	    @Override
	    public void channelAcquired(Channel ch) {
	    	//ignore
	    }
	}
}
