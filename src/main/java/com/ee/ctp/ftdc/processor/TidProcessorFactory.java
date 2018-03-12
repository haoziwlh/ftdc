package com.ee.ctp.ftdc.processor;

import java.util.concurrent.ConcurrentHashMap;

import com.ee.ctp.enums.TID;
/**
 * TID processor 工厂
 * @author ee
 * 2017年11月12日 下午11:23:43
 *
 */
public class TidProcessorFactory {
	private static final ConcurrentHashMap<TID, FtdcTidProcessor> tid_processors = new ConcurrentHashMap<>();
	
	public static class Holder {
		public static final TidProcessorFactory INSTANCE = new TidProcessorFactory();
		private Holder() {}
	}
	
	private TidProcessorFactory() {
		loadProcessors();
	}
	
	private void loadProcessors() {
		processor(TID.UserLoginRsp, UserLoginTidProcessor.class);
		processor(TID.UserLogoutRsp, UserLogoutTidProcessor.class);
		processor(TID.SettlementInfoConfirmRsp, SettlementInfoConfirmTidProcessor.class);
		processor(TID.OrderInsertRsp, OrderInsertTidProcessor.class);
		processor(TID.OrderActionRsp, OrderActionTidProcessor.class);
		processor(TID.BankOrFutureRsp, BankOrFurureProcessor.class);
		processor(TID.UserPasswordUpdateRsp, UserPasswordUpdateTidProcessor.class);
		processor(TID.FundPasswordUpdateRsp, FundPasswordUpdateTidProcessor.class);
		processor(TID.SettlementInfoRsp, SettlementInfoTidProcessor.class);
		processor(TID.TradingAccountRsp, TradingAccountTidProcessor.class);
		processor(TID.RtnOrder, RtnOrderTidProcessor.class);
		processor(TID.RtnTrade, RtnTradeTidProcessor.class);
		processor(TID.InvestorPositionRsp, InvestorPoisitionTidProcessor.class);
		processor(TID.QryMarginRateRsp, QryMarginRateTidProcessor.class);
		processor(TID.QryCommissionRateRsp, QryCommissionRateTidProcessor.class);
		processor(TID.AccountRegisterRsp, AccountRegisterTidProcessor.class);
		processor(TID.ContractbankRsp, ContractbankTidProcessor.class);
		processor(TID.ErrRtnOrderAction, ErrRtnOrderActionTidProcessor.class);
		processor(TID.QryInstrumentRsp, QryInstrumentTidProcessor.class);
		processor(TID.QryTransferSerialRsp, QryTransferSerialTidProcessor.class);
		processor(TID.RtnBankOrFuture, RtnBankOrFutureTidProcessor.class);
		processor(TID.Auth, AuthTidProcessor.class);
		processor(TID.UNUSE, UnuseTidProcessor.class);
		processor(TID.BankOrFutureRsp1, IgnoreTidProcessor.class);
		processor(TID.BankOrFutureRsp2, IgnoreTidProcessor.class);
		
	}
	
	private void processor(TID tid, Class<? extends FtdcTidProcessor> clazz) {
		try {
			FtdcTidProcessor ftdcTidProcessor = clazz.newInstance();
			tid_processors.putIfAbsent(tid, ftdcTidProcessor);
		} catch (Exception e) {
			throw new TidProcessorException(e);
		}
	}
	
	public void replaceProcessor(TID tid, FtdcTidProcessor processor) {
		try {
			tid_processors.put(tid, processor);
		} catch (Exception e) {
			throw new TidProcessorException(e);
		}
	}
	
	public FtdcTidProcessor processor(TID tid) {
		FtdcTidProcessor tidProcessor = tid_processors.get(tid);
		if(tidProcessor == null) {
			throw new TidProcessorException("can not find processor of " + tid);
		}
		return tidProcessor;
	}
}
