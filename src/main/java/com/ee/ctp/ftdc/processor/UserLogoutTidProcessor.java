package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspUserLogout;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:24:20
 *
 */
public class UserLogoutTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspUserLogout lout = new RspUserLogout().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspUserLogout(lout, (int)ftdc.getReqId(), true);
	}
}
