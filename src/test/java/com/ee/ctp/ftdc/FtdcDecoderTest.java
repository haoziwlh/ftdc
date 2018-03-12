package com.ee.ctp.ftdc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ee.ctp.enums.FtdType;
import com.ee.ctp.ftdc.FtdcDecoder;
import com.ee.ctp.ftdc.FtdcProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;

public class FtdcDecoderTest {

	@Test
	public void testNoExt() {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(2);
		buf.writeByte(0);
		buf.writeShort(22);
		buf.writeZero(22);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcDecoder());
		channel.writeInbound(buf);
		
		assertTrue(channel.finish());

		FtdcProtocol ftdcProtocol = channel.readInbound();
		assertEquals(FtdType.FTDTypeCompressed, ftdcProtocol.getType());
		assertEquals(22, ftdcProtocol.getBodyLength());
		assertEquals(0, ftdcProtocol.getExtLength());
		assertNull(channel.readInbound());
		channel.finish();
	}
	
	@Test
	public void testWithExt() {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(2);
		buf.writeByte(3);
		buf.writeShort(22);
		buf.writeByte(1);
		buf.writeByte(1);
		buf.writeByte(1);
		buf.writeZero(22);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcDecoder());
		channel.writeInbound(buf);
		
		assertTrue(channel.finish());

		FtdcProtocol ftdcProtocol = channel.readInbound();
		assertEquals(FtdType.FTDTypeCompressed, ftdcProtocol.getType());
		assertEquals(22, ftdcProtocol.getBodyLength());
		assertEquals(3, ftdcProtocol.getExtLength());
		assertEquals(1, ftdcProtocol.ext().getTagType());
		
		assertNull(channel.readInbound());
		channel.finish();
	}
	
	@Test(expected = TooLongFrameException.class)
	public void testTooLongFrameException() {
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(2);
		buf.writeByte(3);
		buf.writeShort(65535);
		buf.writeByte(1);
		buf.writeByte(1);
		buf.writeByte(1);
		buf.writeZero(65535);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcDecoder());
		channel.writeInbound(buf);
		
		assertTrue(channel.finish());

		FtdcProtocol ftdcProtocol = channel.readInbound();
		assertEquals(FtdType.FTDTypeCompressed, ftdcProtocol.getType());
		assertEquals(22, ftdcProtocol.getBodyLength());
		assertEquals(0, ftdcProtocol.getExtLength());
		assertNull(channel.readInbound());
		channel.finish();
	}

}
