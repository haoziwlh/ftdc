package com.ee.ctp.security;
/**
 * AES认证异常
 * @author ee
 * 2017年11月2日 下午5:47:57
 *
 */
public class KAESException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KAESException(String message, Throwable cause) {
		super(message, cause);
	}

	public KAESException(String message) {
		super(message);
	}

	public KAESException(Throwable cause) {
		super(cause);
	}
	
	

}
