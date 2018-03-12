package com.ee.ctp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:10:51
 *
 */
public class ApplicationRuntime {
	private ApplicationRuntime() {}
	private static final Conf INNER_CONF = new Conf();
	private static final int CORES = Runtime.getRuntime().availableProcessors();
	public static final EventExecutorGroup EEG;
	public static final EventLoopGroup FTDC_LOOP_GROUP;
	public static final EventLoopGroup CLIENT_BOSS_LOOP_GROUP;
	public static final EventLoopGroup CLIENT_WORKER_LOOP_GROUP;
	private static final ConcurrentHashMap<String, Channel> FTDC_CHANNEL_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, RequestIdentity> REQUESTIDENTITY_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Channel> CLIENT_CHANNEL_MAP = new ConcurrentHashMap<>();
	private static final String CHANNEL_MAP_SPLIT = ":";
	private static final HashedWheelTimer TIMER = new HashedWheelTimer();

	static {
		int totalThreadSize = (int)(CORES / (1 - INNER_CONF.getIoRatio()));
		int ioThreadSize = (int)(totalThreadSize * INNER_CONF.getIoRatio());
		int eegThreadSize = totalThreadSize - ioThreadSize;
		int pbThreadSize = (int)(ioThreadSize * INNER_CONF.getFront2backendRatio());
		int ftdThreadSize = ioThreadSize - pbThreadSize;
		EEG = new DefaultEventExecutorGroup(eegThreadSize);
		FTDC_LOOP_GROUP = new NioEventLoopGroup(ftdThreadSize);
		CLIENT_BOSS_LOOP_GROUP = new NioEventLoopGroup(pbThreadSize);
		CLIENT_WORKER_LOOP_GROUP = new NioEventLoopGroup(pbThreadSize);
	}
	
	public static Channel putFtdcChannel(String brokerid, String userid, Channel channel) {
		String key = brokerid + CHANNEL_MAP_SPLIT + userid;
		return FTDC_CHANNEL_MAP.put(key, channel);
	}
	
	public static Channel removeFtdcChannel(String brokerid, String userid) {
		String key = brokerid + CHANNEL_MAP_SPLIT + userid;
		return FTDC_CHANNEL_MAP.remove(key);
	} 
	public static Channel getFtdcChannel(String brokerid, String userid) {
		String key = brokerid + CHANNEL_MAP_SPLIT + userid;
		return FTDC_CHANNEL_MAP.get(key);
	}
	
	public static RequestIdentity bindRequestIdentiity(RequestIdentity ri) {
		String key = ri.getBrokerId() + CHANNEL_MAP_SPLIT + ri.getUserId() + CHANNEL_MAP_SPLIT + ri.getReqId();
		String clientKey = ri.getBrokerId() + CHANNEL_MAP_SPLIT + ri.getUserId();
		if(ri.getClientChannel() != null) {
			CLIENT_CHANNEL_MAP.put(clientKey, ri.getClientChannel());
		}
		return REQUESTIDENTITY_MAP.put(key, ri);
	}
	
	public static Channel rtnChannel(String brokerid, String userid) {
		String clientKey = brokerid + CHANNEL_MAP_SPLIT + userid;
		return CLIENT_CHANNEL_MAP.get(clientKey);
	}
	
	public static RequestIdentity unbindRequestIdentity(String brokerid, String userid, long reqId) {
		String key = brokerid + CHANNEL_MAP_SPLIT + userid + CHANNEL_MAP_SPLIT + reqId;
		return REQUESTIDENTITY_MAP.remove(key);
	} 
	public static RequestIdentity getRequestIdentity(String brokerid, String userid, long reqId) {
		String key = brokerid + CHANNEL_MAP_SPLIT + userid + CHANNEL_MAP_SPLIT + reqId;
		return REQUESTIDENTITY_MAP.get(key);
	}
	
	public static void newTimeout(TimerTask task, int second) {
		TIMER.newTimeout(task, second, TimeUnit.SECONDS);
	}
	
	public static Conf conf() {
		return INNER_CONF;
	}
	
	public static class Conf {
		/**
		 * io intensive close to 1, cpu intensive close to 0
		 */
		private double ioRatio = 0.8;
		/**
		 * client端与ftdc端线程比例
		 */
		private double front2backendRatio = 0.5;
		
		private int maxConnections = 1000;
		private int maxPending = 1000;
		private int ftdcReaderIdle = 120;
		private int ftdcWriterIdle = 80;
		private int ftdcAllIdle = 120;
		private int messageTimeout = 3;
		private String defaultEncoding = "gbk";
		
		public double getIoRatio() {
			return ioRatio;
		}
		public void setIoRatio(double ioRatio) {
			this.ioRatio = ioRatio;
		}
		public double getFront2backendRatio() {
			return front2backendRatio;
		}
		public void setFront2backendRatio(double front2backendRatio) {
			this.front2backendRatio = front2backendRatio;
		}
		public int getMaxConnections() {
			return maxConnections;
		}
		public void setMaxConnections(int maxConnections) {
			this.maxConnections = maxConnections;
		}
		public int getMaxPending() {
			return maxPending;
		}
		public void setMaxPending(int maxPending) {
			this.maxPending = maxPending;
		}
		public int getFtdcReaderIdle() {
			return ftdcReaderIdle;
		}
		public void setFtdcReaderIdle(int ftdcReaderIdle) {
			this.ftdcReaderIdle = ftdcReaderIdle;
		}
		public int getFtdcWriterIdle() {
			return ftdcWriterIdle;
		}
		public void setFtdcWriterIdle(int ftdcWriterIdle) {
			this.ftdcWriterIdle = ftdcWriterIdle;
		}
		public int getFtdcAllIdle() {
			return ftdcAllIdle;
		}
		public void setFtdcAllIdle(int ftdcAllIdle) {
			this.ftdcAllIdle = ftdcAllIdle;
		}
		public int getMessageTimeout() {
			return messageTimeout;
		}
		public void setMessageTimeout(int messageTimeout) {
			this.messageTimeout = messageTimeout;
		}
		public String defaultEncoding() {
			return this.defaultEncoding;
		}
	}
}
