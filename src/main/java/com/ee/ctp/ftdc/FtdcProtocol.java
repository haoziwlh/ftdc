package com.ee.ctp.ftdc;

import com.ee.ctp.enums.FtdType;
import com.ee.ctp.enums.FtdcType;
import com.ee.ctp.enums.Sequence;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * 格式:|--type--|--ext length--|--body length--|---ext body---|---body-----|</br>
 * 
 * 长度:|--1-----|-- 1 ---------|--2------------|-- 0~127------|--0~65535---|
 * 
 * @author ee
 * 2017年10月17日 下午8:27:04
 *
 */
public class FtdcProtocol {
	private static final int MAX_COMPRESS_SIZE_ONCE = 0x0f;
	private FtdType type;
	private int extLength;
	private int bodyLength;
	private ByteBuf extBuf;
	private ByteBuf bodyBuf;
	
	private Ext ext;
	private Ftdc ftdc;
	@SuppressWarnings("unused")
	private FtdcProtocol() { 
		// nop
	}
	
	/**
	 * 用于构造编码协议,特用于登录
	 * @param type
	 * @param ftdcBody
	 * @param ftdcType
	 * @param reqId
	 * @param tid
	 */
	public FtdcProtocol(FtdType type, ByteBuf ftdcBody, int ftdcType, int reqId, int tid) {
		this.type = type;
		this.ftdc = new Ftdc(ftdcBody, ftdcType, reqId, tid);
		this.bodyLength = ftdc.len();
	}
	/**
	 * 用于构造编码协议
	 * @param type
	 * @param ftdcBody
	 * @param ftdcType 协议类型：REQ或者RSP，根据此类型的不同，sequence不同
	 * @param reqId
	 * @param tid
	 * @param sequence
	 */
	public FtdcProtocol(FtdType type, ByteBuf ftdcBody, int ftdcType, int reqId, int tid, Sequence sequence) {
		this.type = type;
		this.ftdc = new Ftdc(ftdcBody, ftdcType, reqId, tid, sequence);
		this.bodyLength = ftdc.len();
	}
	
	/**
	 * 用于构造心跳
	 * @param type
	 */
	public FtdcProtocol(FtdType type) {
		this.type = type;
	}
	
	/**
	 * 用于构造解析协议
	 * @param byteType
	 * @param extLength
	 * @param bodyLength
	 * @param buf
	 * @param compress
	 */
	public FtdcProtocol(short byteType, short extLength, int bodyLength, ByteBuf buf, boolean compress) {
		this.type = FtdType.parseFrom(byteType);
		this.extLength = extLength;
		this.bodyLength = bodyLength;
		extractBodyAndExt(extLength, bodyLength, buf, compress);
	}

	public void write(ByteBuf buffer) {
		try {
			int length = extLength;
			ByteBuf extBuffer = Unpooled.buffer();
			while(length > 0) {
				extBuffer.writeByte(ext.getTagType());
				extBuffer.writeByte(ext.getTagLength());
				switch(ext.getTagLength()) {
				case 1:
					extBuffer.writeByte(ext.getTagValue());
					break;
				case 2:
					extBuffer.writeShort(ext.getTagValue());
					break;
				case 4:
					extBuffer.writeInt(ext.getTagValue());
					break;
				default:
					break;
				}
				length = length - 2 - ext.getTagLength();
				ext = ext.next();
			}
			
			buffer.writeByte(this.type.type());
			buffer.writeByte(extLength);
			ByteBuf ftdcBytes = null;
			if(ftdc != null) {
				ftdcBytes = this.ftdc.write();
				if(FtdcType.RSP.type() == this.ftdc.type) {
					ftdcBytes = compress(ftdcBytes);
				}
				buffer.writeShort(ftdcBytes.readableBytes());
			}else {
				buffer.writeShort(0);
			}
			buffer.writeBytes(extBuffer);
			if(ftdcBytes != null) {
				buffer.writeBytes(ftdcBytes);
			}
		} finally {
			ReferenceCountUtil.release(buffer);
		}
	}
	
	private void extractBodyAndExt(int extLength, int bodyLength, ByteBuf buf, boolean compress) {
		try{
			if(hasExt()) {
				extBuf = buf.retainedSlice(buf.readerIndex(), extLength);
				buf.skipBytes(extLength);
				readExt();
			}
			if(hasBody()) {
				bodyBuf = buf.retainedSlice(buf.readerIndex(), bodyLength);
				buf.skipBytes(bodyLength);
				if(compress) {
					readFtdc();
				}else {
					readFtdcUnUnCompress();
				}
			}
		}finally {
			ReferenceCountUtil.release(buf);
		}
	}
	
	public void addExt(int tagType, int tagLength, int tagValue) {
		ext = new Ext(tagType, tagLength, tagValue, ext);
		extLength = extLength + 2 + tagLength;
	}
	
	private void releaseExtBuf() {
		if(hasExt()) {
			ReferenceCountUtil.release(extBuf);
		}
	}
	
	private void releaseFtdcBuf() {
		if(hasBody()) {
			ReferenceCountUtil.release(bodyBuf);
		}
	}
	
	private void readExt() {
		try{
			int length = extLength;
			while(length > 0) {
				int tagType = extBuf.readByte();
				int tagLength = extBuf.readUnsignedByte();
				int tagValue;
				switch(tagLength) {
				case 1:
					tagValue = extBuf.readByte();
					break;
				case 2:
					tagValue = extBuf.readShort();
					break;
				case 4:
					tagValue = extBuf.readInt();
					break;
				default:
					tagValue = 0;
				}
				ext = new Ext(tagType, tagLength, tagValue, ext);
				length = length - 1 - 1 - tagLength;
			}
		} finally {
			releaseExtBuf();
		}
	}
	
	
	private void readFtdc() {
		try{
			ByteBuf buffer = uncompress();
			ftdc = new Ftdc(buffer);
		} finally {
			releaseFtdcBuf();
		}
	}
	
	private void readFtdcUnUnCompress() {
		try{
			ftdc = new Ftdc(bodyBuf);
		} finally {
			releaseFtdcBuf();
		}
	}

	private ByteBuf uncompress() {
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
		int size = 0;
		boolean isZero = false;
		for(;buf.readerIndex() < buf.writerIndex();) {
			short temp = buf.readUnsignedByte();
			if(temp != 0) {
				if(isZero) {
					compressedBuffer.writeByte(0xe0 + (size & 0xff));
					size = 0;
					isZero = false;	
				}
				
				if((temp >> 4) == 14) {
					compressedBuffer.writeByte(0xe0);
				}
				compressedBuffer.writeByte(temp);
				
			}else {
				size ++;
				if(size == MAX_COMPRESS_SIZE_ONCE) {
					compressedBuffer.writeByte(0xef);
					size = 0;
					isZero = false;
				}else {
					isZero = true;
				}
				
			}
		}
		if(isZero && size > 0) {
			compressedBuffer.writeByte(0xe0 + (size & 0xff));
		}
		return compressedBuffer;
	}
	
	public Ftdc ftdc() {
		return ftdc;
	}
	
	public Ext ext() {
		return ext;
	}
	
	public FtdType getType() {
		return type;
	}
	
	public void setType(FtdType type) {
		this.type = type;
	}
	
	public int getExtLength() {
		return extLength;
	}
	
	public void setExtLength(int extLength) {
		this.extLength = extLength;
	}
	
	public int getBodyLength() {
		return bodyLength;
	}
	
	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}
	
	public boolean hasExt() {
		return extLength > 0;
	}
	
	public boolean hasBody() {
		return bodyLength > 0;
	}
	
	/**
	 * 总体格式|-------------------------------------------------ftdc head----------------------------------------------------|------ftdc body-------|<br/>
	 * 头部格式|--version--|--type--|--ul--|--chain--|--stype--|--sequence--|--cs--|--num--|--len--|--reqid--|--tid--|--tlen--|------ftdc body-------|<br/>
	 * 长度大小|-----1-----|----1---|---1--|----1----|----2----|------4-----|---4--|---2---|----2--|-----4---|---2---|----2---|------0~65535---------|
	 * @author ee
	 * 2017年11月10日 下午5:41:06
	 *
	 */
	public static class Ftdc {
		private static final int FTDC_HEAD_LEN = 22;
		//1
		private int version = 1;
		//1
		private int type;
		//1
		private int unencodeLength = 0x0c;
		//1
		private int chain = 0x4c;
		//2
		private int sequenceType = 0;
		//4
		private int sequence = 0x00003000;
		//4
		private int currentSequence = 0x1;
		//2
		private int numData = 0x1;
		//2
		private int ftdcLen;
		//4
		private long reqId;
		//4 前两个字节是业务TID，后两个是业务结构体长度
		private long tid;
		
		private ByteBuf ftdcBody;
		
		public Ftdc(ByteBuf ftdcBody, int type, int reqId, int tid, Sequence sequence) {
			this.ftdcBody = ftdcBody;
			this.type = type;
			this.reqId = reqId;
			this.tid = tid;
			this.ftdcLen = ftdcBody.readableBytes();
			if(tid != 0) {
				this.ftdcLen = ftdcLen + 4;
			}
			if(FtdcType.REQ.type() == type) {
				this.sequence = sequence.sequence();
			}else {
				this.sequence = sequence.rspSequence();
			}
			this.sequenceType = sequence.sequenceType();
		}
		
		public Ftdc(ByteBuf ftdcBody, int type, int reqId, int tid) {
			this(ftdcBody, type, reqId, tid, Sequence.UserLogin);
		}
		
		public int len() {
			return this.ftdcLen + FTDC_HEAD_LEN;
		}
		
		public boolean hasBody() {
			return len() > FTDC_HEAD_LEN;
		}
		
		public ByteBuf write() {
			try {
				ByteBuf buffer = Unpooled.buffer();
				buffer.writeByte(this.version);
				buffer.writeByte(this.type);
				buffer.writeByte(this.unencodeLength);
				buffer.writeByte(this.chain);
				buffer.writeShort(this.sequenceType);
				buffer.writeInt(this.sequence);
				buffer.writeInt(this.currentSequence);
				buffer.writeShort(this.numData);
				buffer.writeShort(this.ftdcLen);
				buffer.writeInt((int)this.reqId);
				buffer.writeInt((int)this.getTid());
				buffer.writeBytes(ftdcBody);
				return buffer;
			} finally {
				release();
			}
			
		}
		
		public Ftdc(ByteBuf body) {
			this.version = body.readByte();
			this.type = body.readByte();
			this.unencodeLength = body.readByte();
			this.chain = body.readByte();
			this.sequenceType = body.readShort();
			this.sequence = body.readInt();
			this.currentSequence = body.readInt();
			this.numData = body.readShort();
			this.ftdcLen = body.readUnsignedShort();
			this.reqId = body.readUnsignedInt();
			//没有数据的情况是不存在tid的
			if(ftdcLen != 0) {
				this.tid = body.readUnsignedInt();
				int actualLen = this.ftdcLen - 4;
				if(body.readableBytes() < actualLen) {
					throw new IllegalArgumentException("bad body length");
				}
				ftdcBody = body.retainedSlice(body.readerIndex(), actualLen);
			}
			
		}
		
		public ByteBuf ftdcBody() {
			return ftdcBody;
		}
		
		public void release() {
			while(ftdcBody != null && ftdcBody.refCnt() > 0) {
				ReferenceCountUtil.release(ftdcBody);
			}
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getUnencodeLength() {
			return unencodeLength;
		}

		public void setUnencodeLength(int unencodeLength) {
			this.unencodeLength = unencodeLength;
		}

		public int getChain() {
			return chain;
		}

		public void setChain(int chain) {
			this.chain = chain;
		}

		public int getSequenceType() {
			return sequenceType;
		}

		public void setSequenceType(int sequenceType) {
			this.sequenceType = sequenceType;
		}

		public int getSequence() {
			return sequence;
		}

		public void setSequence(int sequence) {
			this.sequence = sequence;
		}

		public int getCurrentSequence() {
			return currentSequence;
		}

		public void setCurrentSequence(int currentSequence) {
			this.currentSequence = currentSequence;
		}

		public int getNumData() {
			return numData;
		}

		public void setNumData(int numData) {
			this.numData = numData;
		}

		public int getFtdcLen() {
			return ftdcLen;
		}

		public void setFtdcLen(int ftdcLen) {
			this.ftdcLen = ftdcLen;
		}

		public long getReqId() {
			return reqId;
		}

		public void setReqId(long reqId) {
			this.reqId = reqId;
		}

		public long getTid() {
			return tid;
		}

		public void setTid(long tid) {
			this.tid = tid;
		}
		
		public int getStructOfTidLen() {
			return (int)(this.tid & 0xffff);
		}

		@Override
		public String toString() {
			return "Ftdc [version=" + version + ", type=" + type + ", unencodeLength=" + unencodeLength + ", chain="
					+ chain + ", sequenceType=" + sequenceType + ", sequence=" + sequence + ", unknown=" + currentSequence
					+ ", numData=" + numData + ", ftdcLen=" + ftdcLen + ", reqId=" + reqId + ", tid=" + tid + "]";
		}
		
		
	}
	
	/**
	 * 格式: |---tagType---|---tagLength---|---tagValue---| </br>
	 * 长度: |-----1-------|------1--------|--0~255-------|
	 * @author ee
	 * 2017年11月10日 下午5:36:45
	 *
	 */
	public static class Ext {
		private int tagType;
		private int tagLength;
		private int tagValue;
		private Ext next;
		public Ext(int tagType, int tagLength, int tagValue, Ext next) {
			this.tagType = tagType;
			this.tagLength = tagLength;
			this.tagValue = tagValue;
			this.next = next;
		}
		public int getTagType() {
			return tagType;
		}
		public void setTagType(int tagType) {
			this.tagType = tagType;
		}
		public int getTagLength() {
			return tagLength;
		}
		public void setTagLength(int tagLength) {
			this.tagLength = tagLength;
		}
		public int getTagValue() {
			return tagValue;
		}
		public void setTagValue(int tagValue) {
			this.tagValue = tagValue;
		}
		
		public Ext next() {
			return next;
		}
		@Override
		public String toString() {
			return "Ext [tagType=" + tagType + ", tagLength=" + tagLength + ", tagValue=" + tagValue + "]";
		}
	}
}
