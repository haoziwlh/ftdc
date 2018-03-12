package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RtnBankOrFuture;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:23:08
 *
 */
public class RtnBankOrFutureTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RtnBankOrFuture rtnBankOrFuture = new RtnBankOrFuture().parseFrom(ftdc.ftdcBody(), error);
		spi.onRtnBankOrFuture(rtnBankOrFuture, (int)ftdc.getReqId(), true);
	}
}