package com.ee.ctp.pool;

import java.net.SocketAddress;
import java.util.List;

import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:45:45
 *
 */
public interface SocketAddressChooserFactory {
	/**
	 * 创建一个选择器
	 * @param size
	 * @return
	 */
	SocketAddressChooser newChooser(int size);
	
	interface SocketAddressChooser {
		/**
		 * 获得下一个地址
		 * @param sas
		 * @return
		 */
		SocketAddress next(List<ConnectAddrProperty> sas);
	}
}
