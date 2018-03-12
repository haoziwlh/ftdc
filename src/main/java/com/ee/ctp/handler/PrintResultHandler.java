package com.ee.ctp.handler;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.ErrRtnOrderAction;
import com.ee.ctp.dto.RspAccountRegister;
import com.ee.ctp.dto.RspAuth;
import com.ee.ctp.dto.RspContractBank;
import com.ee.ctp.dto.RspError;
import com.ee.ctp.dto.RspFundPasswordUpdate;
import com.ee.ctp.dto.RspFutureOrBank;
import com.ee.ctp.dto.RspInputOrder;
import com.ee.ctp.dto.RspInvestorPosition;
import com.ee.ctp.dto.RspOrderAction;
import com.ee.ctp.dto.RspQryCommissionRate;
import com.ee.ctp.dto.RspQryInstrument;
import com.ee.ctp.dto.RspQryMarginRate;
import com.ee.ctp.dto.RspQrySettlementInfoConfirm;
import com.ee.ctp.dto.RspQryTransferSerial;
import com.ee.ctp.dto.RspSettlementInfo;
import com.ee.ctp.dto.RspSettlementInfoConfirm;
import com.ee.ctp.dto.RspTradingAccount;
import com.ee.ctp.dto.RspUserLogin;
import com.ee.ctp.dto.RspUserLogout;
import com.ee.ctp.dto.RspUserPasswordUpdate;
import com.ee.ctp.dto.RtnBankOrFuture;
import com.ee.ctp.dto.RtnOrder;
import com.ee.ctp.dto.RtnTrade;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:43:12
 *
 */
public class PrintResultHandler extends BaseFtdcTraderSpiAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PrintResultHandler.class);
	private static final String LOGGR_TEMPLATE_DEBUG = "recieve msg: reqid {}, {}";
	private ConcurrentHashMap<Integer, byte[]> multipartMap = new ConcurrentHashMap<>();
	
	@Override
	protected void doRspUserLogin(RspUserLogin rspUserLogin, RequestIdentity ri, boolean authPassed) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, ri.getReqId(), rspUserLogin);
	}

	@Override
	protected void doNodata(RequestIdentity ri) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, ri.getReqId(), null);
	}


	@Override
	protected void doRspUserLogout(RequestIdentity requestIdentity, RspUserLogout lout) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), lout);
	}


	@Override
	protected void doRspSettlementInfo(RequestIdentity requestIdentity, RspSettlementInfo info, boolean isLast) {
		if(!isLast) {
			if(multipartMap.containsKey(requestIdentity.getReqId())) {
				byte[] part = multipartMap.get(requestIdentity.getReqId());
				
				multipartMap.put(requestIdentity.getReqId(), ArrayUtils.addAll(part, info.getContent()));
			}else {
				multipartMap.put(requestIdentity.getReqId(), info.getContent());
			}
		}else {
			byte[] part = multipartMap.get(requestIdentity.getReqId());
			try {
				logger.debug(requestIdentity.getReqId() + ":" + new String(part, ApplicationRuntime.conf().defaultEncoding()));
			} catch (UnsupportedEncodingException e) {
				//nop
			}
		}
	}


	@Override
	protected void doRspSettlementInfoConfirm(RequestIdentity requestIdentity, RspSettlementInfoConfirm info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspQrySettlementInfoConfirm(RequestIdentity requestIdentity, RspQrySettlementInfoConfirm info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspTradingAccount(RequestIdentity requestIdentity, RspTradingAccount tradingAccount,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), tradingAccount);
	}

	@Override
	protected void doRspOrderInput(RequestIdentity requestIdentity, RspInputOrder inputOrder, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), inputOrder);
	}

	@Override
	protected void doRtnOrder(RequestIdentity requestIdentity, RtnOrder rtnOrder, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnOrder);
	}

	@Override
	protected void doRtnTrade(RequestIdentity requestIdentity, RtnTrade rtnTrade, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnTrade);		
	}

	@Override
	protected void doRspInvestorPosition(RequestIdentity requestIdentity, RspInvestorPosition investorPisition,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), investorPisition);		
	}

	@Override
	protected void doRspError(RequestIdentity requestIdentity, RspError error) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), error);		
	}

	@Override
	protected void doRspQryMarginRate(RequestIdentity requestIdentity, RspQryMarginRate qryMarginRate, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), qryMarginRate);		
	}

	@Override
	protected void doRspQryCommissionRate(RequestIdentity requestIdentity, RspQryCommissionRate qryCommissionRate,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), qryCommissionRate);		
	}

	@Override
	protected void doErrRtnOrderAction(RequestIdentity requestIdentity, ErrRtnOrderAction errRtnOrderAction,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), errRtnOrderAction);		
	}

	@Override
	protected void doRspQryAccountRegister(RequestIdentity requestIdentity, RspAccountRegister accountRegister,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), accountRegister);		
	}

	@Override
	protected void doRspQryContractBank(RequestIdentity requestIdentity, RspContractBank contractBank, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), contractBank);		
	}

	@Override
	protected void doRspOrderAction(RequestIdentity requestIdentity, RspOrderAction rspOrderAction, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rspOrderAction);		
	}

	@Override
	protected void doRspQryTransferSerial(RequestIdentity requestIdentity, RspQryTransferSerial info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);		
	}

	@Override
	protected void doRspQryInstrument(RequestIdentity requestIdentity, RspQryInstrument info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);		
	}

	@Override
	protected void doRtnBankOrFuture(RequestIdentity requestIdentity, RtnBankOrFuture rtnBankOrFuture, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnBankOrFuture);		
	}

	@Override
	protected void doRspUserPasswordUpdate(RequestIdentity requestIdentity, RspUserPasswordUpdate info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);		
	}

	@Override
	protected void doRspQryOrder(RequestIdentity requestIdentity, RtnOrder order, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), order);
	}

	@Override
	protected void doRspQryTrade(RequestIdentity requestIdentity, RtnTrade trade, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), trade);	
	}

	@Override
	protected void doRspFundPwdUpdtae(RequestIdentity requestIdentity, RspFundPasswordUpdate info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);	
	}

	@Override
	protected void doRspUserAuth(RequestIdentity requestIdentity, RspAuth info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspFutureOrBank(RequestIdentity requestIdentity, RspFutureOrBank info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}
}
