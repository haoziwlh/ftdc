package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspQryMarginRate;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:55
 *
 */
public class QryMarginRateTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspQryMarginRate qryMarginRate = new RspQryMarginRate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryMarginRate(qryMarginRate, (int)ftdc.getReqId(), true);
	}
}
