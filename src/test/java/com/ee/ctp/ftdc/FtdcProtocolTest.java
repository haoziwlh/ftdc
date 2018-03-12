package com.ee.ctp.ftdc;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ee.ctp.dto.ReqUserLogin;
import com.ee.ctp.enums.FtdTagType;
import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;
import com.ee.ctp.enums.TID;
import com.ee.ctp.ftdc.FtdcProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class FtdcProtocolTest {

	@Test
	public void testWrite() {
		ByteBuf buffer = Unpooled.buffer();
		ReqUserLogin reqUserLogin = new ReqUserLogin();
		reqUserLogin.setBrokerID("9999");
		reqUserLogin.setPasswd("12345678");
		reqUserLogin.write(buffer.retain());
		
		FtdcProtocol ftdcProtocol = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 1,
				TID.UserLoginReq.id(), Sequence.UserLogin);
		
		ByteBuf buffer2 = Unpooled.buffer();
		ftdcProtocol.write(buffer2.retain());
		
		assertEquals(2, buffer2.readUnsignedByte());
		assertEquals(0, buffer2.readUnsignedByte());
		assertEquals(290, buffer2.readUnsignedShort());
	}
	
	@Test
	public void testWriteAndRead() {
		ByteBuf buffer = Unpooled.buffer();
		ReqUserLogin reqUserLogin = new ReqUserLogin();
		reqUserLogin.setBrokerID("9999");
		reqUserLogin.setPasswd("12345678");
		reqUserLogin.setUserId("1");
		reqUserLogin.write(buffer.retain());
		
		FtdcProtocol ftdcProtocol = new FtdcProtocol(FtdType.FTDTypeCompressed, buffer, FtdcType.REQ.type(), 1,
				TID.UserLoginReq.id(), Sequence.UserLogin);
		
		ByteBuf buffer2 = Unpooled.buffer();
		ftdcProtocol.write(buffer2.retain());
		
		ByteBuf byteBuf = buffer2.slice(26 + 4, buffer2.readableBytes());
		byte[] t1 = new byte[9];
		byteBuf.readBytes(t1);
		assertEquals("", StringUtils.trimToEmpty(new String(t1)));
		byte[] t2 = new byte[11];
		byteBuf.readBytes(t2);
		assertEquals("9999", StringUtils.trimToEmpty(new String(t2)));
		byte[] t3 = new byte[16];
		byteBuf.readBytes(t3);
		assertEquals("1", StringUtils.trimToEmpty(new String(t3)));
		byte[] t4 = new byte[41];
		byteBuf.readBytes(t4);
		assertEquals("12345678", StringUtils.trimToEmpty(new String(t4)));
	}

	@Test
	public void testAddExt() {
		FtdcProtocol ftdcProtocol = new FtdcProtocol(FtdType.FTDTypeNone);
		FtdTagType tagType = FtdTagType.FTDTagKeepAlive;
		ftdcProtocol.addExt(tagType.type(), tagType.length(), 0);
		
		assertEquals(tagType.type(), ftdcProtocol.ext().getTagType());
	}
	
	@Test
	public void testCompress() {
		byte[] b = new byte[] {1, 3, 12, 76, 0, 0, 0, 0, 48, 1, 0, 0, 0, 1, 0, 1, 0, -11, 0, 0, 20, 122, 0, 0, 0, 85, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 48, 49, 55, 48, 49, 48, 49, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 57, 57, 57, 57, 0, 0, 0, 0, 0, 0, 0, 48, 57, 49, 53, 56, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 109, 111, 99, 107, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 4, 106, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 49, 51, 58, 49, 50, 58, 49, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(b);
		ByteBuf compress = compress(wrappedBuffer);
		ByteBuf uncompress = uncompress(compress);
		System.out.println();
		assertEquals(b.length, uncompress.readableBytes());
	}
	
	private ByteBuf uncompress(ByteBuf bodyBuf) {
		ByteBuf buffer = Unpooled.buffer();
		for(;bodyBuf.readerIndex() < bodyBuf.writerIndex();) {
			short b = bodyBuf.readUnsignedByte();
			if((b >> 4) != 14) {
				buffer.writeByte(b);
			}else {
				int size = b & 15;
				if(size == 0) {
					buffer.writeByte(bodyBuf.readByte());
				} else {
					buffer.writeZero(size);
				}
			}
		}
		return buffer;
	}
	
	private ByteBuf compress(ByteBuf buf) {
		ByteBuf compressedBuffer = Unpooled.buffer();
		for(;buf.readerIndex() < buf.writerIndex();) {
			short temp;
			int size = 0;
			while((temp = buf.readUnsignedByte()) == 0 && buf.readerIndex() < buf.writerIndex() && size < 0x0f) {
				size ++;
			}
			if(buf.readerIndex() >= buf.writerIndex()) {
				compressedBuffer.writeZero(1);
			}
			
			if(size == 0x0f) {
				buf.readerIndex(buf.readerIndex() - 1);
			}
			if(size != 0) {
				compressedBuffer.writeByte(0xe0 + (size & 0xff));
			}
			if(temp != 0) {
				if((temp >> 4) == 14) {
					compressedBuffer.writeByte(0xe0);
				}
				compressedBuffer.writeByte(temp);
			}
		}
		return compressedBuffer;
	}

}
