package com.ee.ctp.ftdc.processor;
/**
 * Processor 异常
 * @author ee
 * 2017年11月2日 下午5:47:57
 *
 */
public class TidProcessorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public TidProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public TidProcessorException(String message) {
		super(message);
	}

	public TidProcessorException(Throwable cause) {
		super(cause);
	}
	
	

}
