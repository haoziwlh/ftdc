package com.ee.ctp.enums;
/**
 * 当长包被分包时，另外一种区分方式是：
 * sequenceType 拆包的个数
 * currentSequence 表示当前应答到了第几个
 * @author ee
 *
 */
public enum Sequence {
	/**
	 * 错误应答
	 */
	RspError(0x0001, 0x00000000),
	UserLogin(0x0000, 0x00003000),
	AfterUserLogin(0x0000, 0x0000f102),
	UserLogout(0x0001, 0x00003002),
	ContractBank(0x0004, 0x00008103),
	SettlementInfo(0x0004, 0x0000803c),
	SettlementInfoConfirm(0x0001, 0x00004013),
	QrySettlementInfoConfirm(0x0004, 0x00008056),
	TradingAccount(0x0004, 0x00008006),
	OrderInsert(0x0001, 0x00004000),
	InvestorPosition(0x0004, 0x00008004),
	QryOrder(0x0004, 0x00008000),
	QryTrade(0x0004, 0x00008002),
	QryMarginRate(0x0004, 0x0000800e),
	QryCommissionRate(0x0004, 0x00008010),
	OrderAction(0x0001, 0x00004006),
	AccountRegister(0x0004, 0x00008202),
	/**
	 * 转账流水查询
	 */
	QryTransferSerial(0x0004, 0x00008200),
	
	QryInstrument(0x0004, 0x0000802e),
	/**
	 * 银转期
	 */
	FromBankToFuture(0x0001, 0x0001800b),
	/**
	 * 期转银
	 */
	FromFutureToBank(0x0001, 0x0001800d),
	RtnBankOrFuture(0x0002, 0x0000f204),
	UserPasswordUpdate(0x0002, 0x0000300a),
	/**
	 * 资金密码更新
	 */
	FundPassworeUpdate(0x0001, 0x0000300e),
	/**
	 * 客户端认证
	 */
	Auth(0x0001, 0x00003010);
	
	private int sequenceType;
	private int sequence;
	private int rspSequence;
	
	private Sequence(int sequenceType, int sequence) {
		this.sequence = sequence;
		this.sequenceType = sequenceType;
		this.rspSequence = this.sequence + 1;
	}
	
	public int sequence() {
		return this.sequence;
	}
	
	public int rspSequence() {
		return this.rspSequence;
	}
	
	public int sequenceType() {
		return this.sequenceType;
	}
	
}
