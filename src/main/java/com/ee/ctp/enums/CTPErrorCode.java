package com.ee.ctp.enums;

/**
 * 
 * @author ee
 * 2017年11月3日 上午10:32:14
 *
 */
public enum CTPErrorCode {
	/**
	 * 成功
	 */
	CTP_OK(0, "CTP:正确"),
	/**
	 * 以下三个对应的code，channel已经认证通过
	 */
	CTP_WEAK_PASSWORD(131, "CTP:弱密码过期，请修改密码后重新登录"),
	CTP_FIRST_LOGIN(140, "CTP:首次登陆，请修改密码后重新登录"),
	CTP_PWD_OUT_OF_DATE(141, "CTP:密码过期，请修改密码后重新登录")
	;
	
	private int code;
	private String msg;
	private CTPErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static CTPErrorCode parseFrom(int code) {
		CTPErrorCode retCode = null;
		for (CTPErrorCode t : CTPErrorCode.values()) {
			if(t.code == code) {
				retCode = t;
				break;
			}
		}
		return retCode;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}

	public static boolean isChannelAuthPassed(CTPErrorCode code) {
		boolean passed = false;
		if(code == CTP_OK || CTP_WEAK_PASSWORD == code || CTP_FIRST_LOGIN == code || CTP_PWD_OUT_OF_DATE == code) {
			passed = true;
		}
		return passed;
	}
}
