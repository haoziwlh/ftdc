package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.ErrRtnOrderAction;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:01
 *
 */
public class ErrRtnOrderActionTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		ErrRtnOrderAction errRtnOrderAction = new ErrRtnOrderAction().parseFrom(ftdc.ftdcBody(), error);
		spi.onErrRtnOrderAction(errRtnOrderAction, (int)ftdc.getReqId(), true);
	}
}
