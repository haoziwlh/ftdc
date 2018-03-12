package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspInputOrder;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:36
 *
 */
public class OrderInsertTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspInputOrder inputOrder = new RspInputOrder().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspOrderInput(inputOrder, (int)ftdc.getReqId(), true);
	}
}
