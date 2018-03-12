package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspTradingAccount;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:24:03
 *
 */
public class TradingAccountTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspTradingAccount tradingAccount = new RspTradingAccount().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspTradingAccount(tradingAccount, (int)ftdc.getReqId(), true);
	}
}
