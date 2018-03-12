package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspFutureOrBank;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:21:48
 *
 */
public class BankOrFurureProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspFutureOrBank rspFutureToBank = new RspFutureOrBank().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspFutureOrBank(rspFutureToBank, (int)ftdc.getReqId(), true);
	}
}
