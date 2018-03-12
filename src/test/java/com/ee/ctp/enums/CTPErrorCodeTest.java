package com.ee.ctp.enums;

import com.ee.ctp.enums.CTPErrorCode;

import junit.framework.TestCase;

public class CTPErrorCodeTest extends TestCase{

	public void testParseFrom() {
		assertEquals(CTPErrorCode.CTP_OK, CTPErrorCode.parseFrom(0));
		assertEquals(CTPErrorCode.CTP_WEAK_PASSWORD, CTPErrorCode.parseFrom(131));
		assertEquals(null, CTPErrorCode.parseFrom(100));
	}
	
	public void testIsChannelAuthPassed() {
		assertEquals(true, CTPErrorCode.isChannelAuthPassed(CTPErrorCode.CTP_OK));
	}
}
