package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspFundPasswordUpdate;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:22:09
 *
 */
public class FundPasswordUpdateTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspFundPasswordUpdate rspFund = new RspFundPasswordUpdate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspFundPasswordUpdate(rspFund, (int)ftdc.getReqId(), true);
	}
}
