package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspQryCommissionRate;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:42
 *
 */
public class QryCommissionRateTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspQryCommissionRate qryCommissionRate = new RspQryCommissionRate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryCommissionRate(qryCommissionRate, (int)ftdc.getReqId(), true);
	}
}
