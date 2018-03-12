package com.ee.ctp.enums;
/**
 * 
 * 业务标志符：
 * 1.前三个字节表示业务结构类型
 * 2.最后一个字节表示业务结构的长度
 * @author ee
 * 2017年10月17日 下午8:49:30
 *
 */
public enum TID {
	/**
	 * 未使用的TID，专用于标志尚未识别的业务
	 */
	UNUSE(0xffffffff, FtdcType.REQ_RSP),
	UserLoginReq(0x100200e0, FtdcType.REQ),
	UserLoginRsp(0x10030098, FtdcType.RSP),
	UserLogoutReq(0x1004001b, FtdcType.REQ),
	UserLogoutRsp(0x1004001b, FtdcType.RSP),
	/**
	 * 该TID为公共使用的，代表的是CThostFtdcRspInfoField，包含错误码和错误消息
	 */
	CommonRsp(0x00000055, FtdcType.RSP),
	ContractbankReq(0x24690014, FtdcType.REQ),
	ContractbankRsp(0x24700079, FtdcType.RSP),
	SettlementInfoReq(0x070c0021, FtdcType.REQ),
	SettlementInfoRsp(0x001b021e, FtdcType.RSP),
	SettlementInfoConfirmReq(0x040f002a, FtdcType.REQ),
	SettlementInfoConfirmRsp(0x040f002a, FtdcType.RSP),
	QrySettlementInfoConfirmReq(0x24580018, FtdcType.REQ),
	QrySettlementInfoConfirmRsp(0x040f002a, FtdcType.RSP),
	TradingAccountReq(0x0703001c, FtdcType.REQ),
	TradingAccountRsp(0x000c0171, FtdcType.RSP),
	OrderInsertReq(0x04000105, FtdcType.REQ),
	OrderInsertRsp(0x04000105, FtdcType.RSP),
	/**
	 * 报单回报以及报单查询都是该TID
	 */
	RtnOrder(0x0401027b, FtdcType.RSP),
	/**
	 * 成交回报以及成交查询都是该TID
	 */
	RtnTrade(0x04090138, FtdcType.RSP),
	InvestorPositionReq(0x07020037, FtdcType.REQ),
	InvestorPositionRsp(0x000d012f, FtdcType.RSP),
	QryOrderReq(0x07000067, FtdcType.REQ),
	QryTradeReq(0x07010067, FtdcType.REQ),
	QryMarginRateReq(0x07070038, FtdcType.REQ),
	QryMarginRateRsp(0x000e005d, FtdcType.RSP),
	QryCommissionRateReq(0x07080037, FtdcType.REQ),
	QryCommissionRateRsp(0x000f0068, FtdcType.RSP),
	/**
	 * 报单操作请求
	 */
	OrderActionReq(0x040400c5, FtdcType.REQ),
	OrderActionRsp(0x040400c5, FtdcType.RSP),
	/**
	 * 报单操作请求响应
	 */
	/**
	 * 报单操作错误回报
	 */
	ErrRtnOrderAction(0x04050190, FtdcType.RSP),
	AccountRegisterReq(0x30110025, FtdcType.REQ),
	AccountRegisterRsp(0x30120197, FtdcType.RSP),
	QryTransferSerialReq(0x300d0020, FtdcType.REQ),
	QryTransferSerialRsp(0x300c0193, FtdcType.RSP),
	QryInstrumentReq(0x07170066, FtdcType.REQ),
	QryInstrumentRsp(0x00030116, FtdcType.RSP),
	FromBankToFutureReq(0x2807034a, FtdcType.REQ),
	BankOrFutureRsp1(0x100800a7, FtdcType.RSP),
	BankOrFutureRsp2(0x100a00a7, FtdcType.RSP),
	BankOrFutureRsp(0x2807034a, FtdcType.RSP),
	FromFutureToBankReq(0x2807034a, FtdcType.REQ),
	RtnBankOrFuture(0x2808039f, FtdcType.RSP),
	/**
	 * 用户口令更新请求
	 */
	UserPasswordUpdateReq(0x0304006d, FtdcType.REQ),
	UserPasswordUpdateRsp(0x0304006d, FtdcType.RSP),
	/**
	 * 用户资金密码更新请求
	 */
	FundPasswordUpdateReq(0x2460006e, FtdcType.REQ),
	FundPasswordUpdateRsp(0x2460006e, FtdcType.RSP),
	/**
	 * 客户端认证
	 */
	Auth(0x101200ab, FtdcType.RSP);
	
	private static final int UMASK = 0xffff0000;
	
	private int id;
	private FtdcType type;
	private TID(int id, FtdcType type) {
		this.id = id;
		this.type = type;
	}
	
	public int id() {
		return id;
	}
	
	public int validId() {
		return id & UMASK;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static TID parseFrom(long id, FtdcType type) {
		int validId = (int)(id & UMASK);
		TID retTID = TID.UNUSE;
		TID[] tids = TID.values();
		for (TID tid: tids) {
			if (tid.type != type) {
				continue;
			}
			if(tid.validId() == validId) {
				retTID = tid;
				break;
			}
		}
		return retTID;
	}
}
