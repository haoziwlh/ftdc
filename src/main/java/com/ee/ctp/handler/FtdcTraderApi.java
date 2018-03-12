package com.ee.ctp.handler;

import java.util.List;

import com.ee.ctp.RequestIdentity;
import com.ee.ctp.dto.FtdcReq;
import com.ee.ctp.pool.FtdClientPool.ConnectAddrProperty;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:29:05
 *
 */
public interface FtdcTraderApi {
	/**
	 * 注册ftd地址
	 * @param sas
	 */
	void registerFront(List<ConnectAddrProperty> sas);
	/**
	 * 注册消息处理器
	 * @param requestIdentity
	 * @param spi
	 */
	void registerSpi(RequestIdentity requestIdentity, FtdcTraderSpi spi);
	/**
	 * 请求登录
	 * @param requestIdentity
	 * @param userLogin
	 * @param spi
	 */
	void reqUserLogin(RequestIdentity requestIdentity, FtdcReq userLogin);
	/**
	 * 请求登出
	 * @param requestIdentity
	 * @param userLogout
	 */
	void reqUserLogout(RequestIdentity requestIdentity, FtdcReq userLogout);
	/**
	 * 请求结算单信息
	 * @param requestIdentity
	 * @param info
	 */
	void reqSettlementInfo(RequestIdentity requestIdentity, FtdcReq info);
	/**
	 * 请求结算单确认
	 * @param requestIdentity
	 * @param info
	 */
	void reqSettlementInfoConfirm(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求查询结算单确认
	 * @param requestIdentity
	 * @param info
	 */
	void reqQrySettlementInfoConfirm(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求查询资金信息
	 * @param requestIdentity
	 * @param tradingAccount
	 */
	void reqTradingAccount(RequestIdentity requestIdentity,FtdcReq tradingAccount);
	/**O
	 * 请求报单
	 * @param requestIdentity
	 * @param inputOrder
	 */
	void reqOrderInput(RequestIdentity requestIdentity,FtdcReq inputOrder);
	/**
	 * 请求持仓
	 * @param requestIdentity
	 * @param investorPisition
	 */
	void reqInvestorPosition(RequestIdentity requestIdentity,FtdcReq investorPisition);
	/**
	 * 请求查询保证金比例
	 * @param requestIdentity
	 * @param qryMarginRate
	 */
	void reqQryMarginRate(RequestIdentity requestIdentity,FtdcReq qryMarginRate);
	/**
	 * 请求查询手续费
	 * @param requestIdentity
	 * @param qryCommissionRate
	 */
	void reqQryCommissionRate(RequestIdentity requestIdentity,FtdcReq qryCommissionRate);
	/**
	 * 请求查询银期签约关系
	 * @param requestIdentity
	 * @param accountRegister
	 */
	void reqQryAccountRegister(RequestIdentity requestIdentity,FtdcReq accountRegister);
	/**
	 * 请求查询签约银行
	 * @param requestIdentity
	 * @param contractBank
	 */
	void reqQryContractBank(RequestIdentity requestIdentity,FtdcReq contractBank);
	/**
	 * 报单操作
	 * @param requestIdentity
	 * @param reqOrderAction
	 */
	void reqOrderAction(RequestIdentity requestIdentity,FtdcReq reqOrderAction);
	/**
	 * 查询转账流水
	 * @param requestIdentity
	 * @param info
	 */
	void reqQryTransferSerial(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 查询合约
	 * @param requestIdentity
	 * @param info
	 */
	void reqQryInstrument(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求银行转期货
	 * @param requestIdentity
	 * @param info
	 */
	void reqFromBankToFuture(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求期货转银行
	 * @param requestIdentity
	 * @param info
	 */
	void reqFromFutureToBank(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求交易密码修改
	 * @param requestIdentity
	 * @param info
	 */
	void reqUserPasswordUpdate(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求报单查询
	 * @param requestIdentity
	 * @param info
	 */
	void reqQryOrder(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 成交查询
	 * @param requestIdentity
	 * @param info
	 */
	void reqQryTrade(RequestIdentity requestIdentity,FtdcReq info);
	/**
	 * 请求资金密码修改
	 * @param requestIdentity
	 * @param info
	 */
	void reqFundPasswordUpdate(RequestIdentity requestIdentity,FtdcReq info);
	
	/**
	 * 用户认证和登录
	 * @param requestIdentity
	 * @param userLogin
	 * @param spi
	 */
	void reqAuthAndLogin(RequestIdentity requestIdentity, FtdcReq userLogin);
}
