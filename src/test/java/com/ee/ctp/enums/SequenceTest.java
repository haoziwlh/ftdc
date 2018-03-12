package com.ee.ctp.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ee.ctp.enums.Sequence;

public class SequenceTest {

	@Test
	public void testRspSequence() {
		Sequence[] values = Sequence.values();
		for (Sequence sequence : values) {
			assertEquals(sequence.sequence() + 1, sequence.rspSequence());
		}
	}
}
