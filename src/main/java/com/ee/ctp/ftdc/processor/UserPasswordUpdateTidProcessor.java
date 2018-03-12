package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspUserPasswordUpdate;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:24:25
 *
 */
public class UserPasswordUpdateTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspUserPasswordUpdate userPasswordUpdate = new RspUserPasswordUpdate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspUserPasswordUpdate(userPasswordUpdate, (int)ftdc.getReqId(), true);
	}
}
