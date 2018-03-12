package com.ee.ctp.pool;

import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:44:26
 *
 */
public class DefaultSocketAddressChooserFactory implements SocketAddressChooserFactory {
	public static final DefaultSocketAddressChooserFactory INSTANCE = new DefaultSocketAddressChooserFactory();
	private DefaultSocketAddressChooserFactory() {}
	@Override
	public SocketAddressChooser newChooser(int size) {
		if(isPowerOfTwo(size)) {
			return new PowerOfTwoSocketAddressChooser();
		}else {
			return new GenericSocketAddressChooser();
		}
	}

	static boolean isPowerOfTwo(int val) {
		return (val & -val) == val;
	}
	
	static final class PowerOfTwoSocketAddressChooser implements SocketAddressChooser{
		private final AtomicInteger idx = new AtomicInteger();
		
		@Override
		public SocketAddress next(List<ConnectAddrProperty> sas) {
			return sas.get(idx.getAndIncrement() & sas.size() - 1).getSocketAddress();
		}
		
	}
	
	static final class GenericSocketAddressChooser implements SocketAddressChooser{
		private final AtomicInteger idx = new AtomicInteger();
		
		@Override
		public SocketAddress next(List<ConnectAddrProperty> sas) {
			return sas.get(Math.abs(idx.getAndIncrement() % sas.size())).getSocketAddress();
		}
	}

}
