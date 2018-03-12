package com.ee.ctp.enums.business;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ee.ctp.enums.business.FtdcActionFlagType;

public class FtdcActionFlagTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdcActionFlagType.THOST_FTDCAF_Delete, FtdcActionFlagType.parseFrom("0"));
		assertEquals(null, FtdcActionFlagType.parseFrom("2"));
	}

}
