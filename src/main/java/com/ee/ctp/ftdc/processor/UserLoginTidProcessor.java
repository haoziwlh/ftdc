package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspUserLogin;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:24:15
 *
 */
public class UserLoginTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspUserLogin rspUserLogin = new RspUserLogin().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspUserLogin(rspUserLogin, (int)ftdc.getReqId(), true);
	}
}
