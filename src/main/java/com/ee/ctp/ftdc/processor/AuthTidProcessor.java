package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspAuth;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:21:41
 *
 */
public class AuthTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		if(error == null) {
			RspAuth rspAuth = new RspAuth().parseFirstFrom(ftdc.ftdcBody());
			spi.onRspUserAuthFirst(rspAuth, (int)ftdc.getReqId(), true);
		}else {
			RspAuth rspAuth = new RspAuth().parseSecondFrom(ftdc.ftdcBody(), error);
			spi.onRspUserAuthSecond(rspAuth, (int)ftdc.getReqId(), true);
		}
	}
}
