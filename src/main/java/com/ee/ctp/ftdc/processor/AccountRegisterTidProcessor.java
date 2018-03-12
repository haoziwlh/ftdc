package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspAccountRegister;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:21:30
 *
 */
public class AccountRegisterTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspAccountRegister rspAccountRegister = new RspAccountRegister().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryAccountRegister(rspAccountRegister, (int)ftdc.getReqId(), true);
	}
}
