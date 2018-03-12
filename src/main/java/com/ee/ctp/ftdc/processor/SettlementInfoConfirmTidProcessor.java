package com.ee.ctp.ftdc.processor;

import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspQrySettlementInfoConfirm;
import com.ee.ctp.dto.RspSettlementInfoConfirm;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.ftdc.FtdcProtocol.Ftdc;
import com.ee.ctp.handler.FtdcTraderSpi;
/**
 * 
 * @author ee
 * 2017年11月12日 下午11:23:27
 *
 */
public class SettlementInfoConfirmTidProcessor implements FtdcTidProcessor{

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		if(Sequence.QrySettlementInfoConfirm.rspSequence() == ftdc.getSequence()) {
			//QrySettlementInfoConfirmRsp
			RspQrySettlementInfoConfirm qrySettlementInfoConfirm = new RspQrySettlementInfoConfirm().parseFrom(ftdc.ftdcBody(), error);
			spi.onRspQrySettlementInfoConfirm(qrySettlementInfoConfirm, (int)ftdc.getReqId(), true);
		}else if(Sequence.SettlementInfoConfirm.rspSequence() == ftdc.getSequence()){
			RspSettlementInfoConfirm info = new RspSettlementInfoConfirm().parseFrom(ftdc.ftdcBody(), error);
			spi.onRspSettlementInfoConfirm(info, (int)ftdc.getReqId(), true);
		}
	}
}
