package com.ee.ctp.enums.business;
/**
 * 
 * @author ee
 * 2017年10月17日 下午7:56:08
 *
 */
public enum FtdcOrderSubmitStatusType {
	/**
	 * 已经提交
	 */
	THOST_FTDCOSS_InsertSubmitted("0"),
	/**
	 * 撤单已经提交
	 */
	THOST_FTDCOSS_CancelSubmitted("1"),
	/**
	 * 修改已经提交
	 */
	THOST_FTDCOSS_ModifySubmitted("2"),
	/**
	 * 已经接受
	 */
	THOST_FTDCOSS_Accepted("3"),
	/**
	 * 报单已经被拒绝
	 */
	THOST_FTDCOSS_InsertRejected("4"),
	/**
	 * 撤单已经倍决绝
	 */
	THOST_FTDCOSS_CancelRejected("5"),
	/**
	 * 改单已经被拒绝
	 */
	THOST_FTDCOSS_ModifyRejected("6");
	
	private String status;
	private FtdcOrderSubmitStatusType(String status) {
		this.status = status;
	}
	
	public String status() {
		return this.status;
	}
	
	public static FtdcOrderSubmitStatusType parseFrom(String status) {
		FtdcOrderSubmitStatusType retStatus = null;
		switch (status) {
		case "0":
			retStatus = THOST_FTDCOSS_InsertSubmitted;
			break;
		case "1":
			retStatus = THOST_FTDCOSS_CancelSubmitted;
			break;
		case "2":
			retStatus = THOST_FTDCOSS_ModifySubmitted;
			break;
		case "3":
			retStatus = THOST_FTDCOSS_Accepted;
			break;
		case "4":
			retStatus = THOST_FTDCOSS_InsertRejected;
			break;
		case "5":
			retStatus = THOST_FTDCOSS_CancelRejected;
			break;
		case "6":
			retStatus = THOST_FTDCOSS_ModifyRejected;
			break;
		default:
			break;
		}
		return retStatus;
	}
}
