package com.ee.ctp.enums.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ee.ctp.enums.business.FtdcAvailabilityFlagType;

public class FtdcAvailabilityFlagTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdcAvailabilityFlagType.THOST_FTDC_Valid, FtdcAvailabilityFlagType.parseFrom("1"));
		assertEquals(null, FtdcAvailabilityFlagType.parseFrom("3"));
	}

}
