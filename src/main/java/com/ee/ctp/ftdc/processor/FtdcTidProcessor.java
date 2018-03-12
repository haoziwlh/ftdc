package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * tid 处理器
 * @author ee
 * 2017年11月12日 下午11:20:48
 *
 */
public interface FtdcTidProcessor {
	/**
	 * tid 处理
	 * @param ftdc
	 * @param spi
	 * @param error
	 */
	void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error);
}
