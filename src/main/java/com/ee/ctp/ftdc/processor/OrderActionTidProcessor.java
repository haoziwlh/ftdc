package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspOrderAction;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:30
 *
 */
public class OrderActionTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspOrderAction rspOrderAction = new RspOrderAction().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspOrderAction(rspOrderAction, (int)ftdc.getReqId(), true);
	}
}
