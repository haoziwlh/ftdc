package com.ee.ctp.ftdc;

import java.nio.ByteOrder;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * 
 * 格式:|--type--|--ext length--|--body length--|---ext body---|---body-----|</br>
 * 
 * 长度:|--1-----|-- 1 ---------|--2------------|-- 0~127------|--0~65535---|
 * @author ee
 *
 */
public class FtdcDecoder extends ByteToMessageDecoder{

	 	private final ByteOrder byteOrder;
	    private final int maxFrameLength;
	    private final int lengthFieldOffset;
	    private final int lengthFieldEndOffset;
	    private final int lengthAdjustment;
	    private final int initialBytesToStrip;
	    private final boolean failFast;
	    private boolean discardingTooLongFrame;
	    private long tooLongFrameLength;
	    private long bytesToDiscard;
	    private boolean compress;

	    /**
	     * Creates a new instance.
	     *
	     * @param maxFrameLength
	     *        the maximum length of the frame.  If the length of the frame is
	     *        greater than this value, {@link TooLongFrameException} will be
	     *        thrown.
	     * @param lengthFieldOffset
	     *        the offset of the length field
	     * @param lengthFieldLength
	     *        the length of the length field
	     */
	    public FtdcDecoder(
	            int maxFrameLength,
	            int lengthFieldOffset, int lengthFieldLength) {
	        this(maxFrameLength, lengthFieldOffset, lengthFieldLength, 0, 0);
	    }
	    
	    public FtdcDecoder(boolean compress) {
			this(65535, 1, 3, 0, 4);
			this.compress = compress;
		}
	    
	    public FtdcDecoder() {
			this(true);
		}

	    /**
	     * Creates a new instance.
	     *
	     * @param maxFrameLength
	     *        the maximum length of the frame.  If the length of the frame is
	     *        greater than this value, {@link TooLongFrameException} will be
	     *        thrown.
	     * @param lengthFieldOffset
	     *        the offset of the length field
	     * @param lengthFieldLength
	     *        the length of the length field
	     * @param lengthAdjustment
	     *        the compensation value to add to the value of the length field
	     * @param initialBytesToStrip
	     *        the number of first bytes to strip out from the decoded frame
	     */
	    public FtdcDecoder(
	            int maxFrameLength,
	            int lengthFieldOffset, int lengthFieldLength,
	            int lengthAdjustment, int initialBytesToStrip) {
	        this(
	                maxFrameLength,
	                lengthFieldOffset, lengthFieldLength, lengthAdjustment,
	                initialBytesToStrip, true);
	    }

	    /**
	     * Creates a new instance.
	     *
	     * @param maxFrameLength
	     *        the maximum length of the frame.  If the length of the frame is
	     *        greater than this value, {@link TooLongFrameException} will be
	     *        thrown.
	     * @param lengthFieldOffset
	     *        the offset of the length field
	     * @param lengthFieldLength
	     *        the length of the length field
	     * @param lengthAdjustment
	     *        the compensation value to add to the value of the length field
	     * @param initialBytesToStrip
	     *        the number of first bytes to strip out from the decoded frame
	     * @param failFast
	     *        If <tt>true</tt>, a {@link TooLongFrameException} is thrown as
	     *        soon as the decoder notices the length of the frame will exceed
	     *        <tt>maxFrameLength</tt> regardless of whether the entire frame
	     *        has been read.  If <tt>false</tt>, a {@link TooLongFrameException}
	     *        is thrown after the entire frame that exceeds <tt>maxFrameLength</tt>
	     *        has been read.
	     */
	    public FtdcDecoder(
	            int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
	            int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
	        this(
	                ByteOrder.BIG_ENDIAN, maxFrameLength, lengthFieldOffset, lengthFieldLength,
	                lengthAdjustment, initialBytesToStrip, failFast);
	    }

	    /**
	     * Creates a new instance.
	     *
	     * @param byteOrder
	     *        the {@link ByteOrder} of the length field
	     * @param maxFrameLength
	     *        the maximum length of the frame.  If the length of the frame is
	     *        greater than this value, {@link TooLongFrameException} will be
	     *        thrown.
	     * @param lengthFieldOffset
	     *        the offset of the length field
	     * @param lengthFieldLength
	     *        the length of the length field
	     * @param lengthAdjustment
	     *        the compensation value to add to the value of the length field
	     * @param initialBytesToStrip
	     *        the number of first bytes to strip out from the decoded frame
	     * @param failFast
	     *        If <tt>true</tt>, a {@link TooLongFrameException} is thrown as
	     *        soon as the decoder notices the length of the frame will exceed
	     *        <tt>maxFrameLength</tt> regardless of whether the entire frame
	     *        has been read.  If <tt>false</tt>, a {@link TooLongFrameException}
	     *        is thrown after the entire frame that exceeds <tt>maxFrameLength</tt>
	     *        has been read.
	     */
	    public FtdcDecoder(
	            ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
	            int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
	        if (byteOrder == null) {
	            throw new NullPointerException("byteOrder");
	        }

	        if (maxFrameLength <= 0) {
	            throw new IllegalArgumentException(
	                    "maxFrameLength must be a positive integer: " +
	                    maxFrameLength);
	        }

	        if (lengthFieldOffset < 0) {
	            throw new IllegalArgumentException(
	                    "lengthFieldOffset must be a non-negative integer: " +
	                    lengthFieldOffset);
	        }

	        if (initialBytesToStrip < 0) {
	            throw new IllegalArgumentException(
	                    "initialBytesToStrip must be a non-negative integer: " +
	                    initialBytesToStrip);
	        }

	        if (lengthFieldOffset > maxFrameLength - lengthFieldLength) {
	            throw new IllegalArgumentException(
	                    "maxFrameLength (" + maxFrameLength + ") " +
	                    "must be equal to or greater than " +
	                    "lengthFieldOffset (" + lengthFieldOffset + ") + " +
	                    "lengthFieldLength (" + lengthFieldLength + ").");
	        }

	        this.byteOrder = byteOrder;
	        this.maxFrameLength = maxFrameLength;
	        this.lengthFieldOffset = lengthFieldOffset;
	        this.lengthAdjustment = lengthAdjustment;
	        lengthFieldEndOffset = lengthFieldOffset + lengthFieldLength;
	        this.initialBytesToStrip = initialBytesToStrip;
	        this.failFast = failFast;
	    }

	    @Override
	    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	        Object decoded = decode(ctx, in);
	        if (decoded != null) {
	            out.add(decoded);
	        }
	    }

	    /**
	     * Create a frame out of the {@link ByteBuf} and return it.
	     *
	     * @param   ctx             the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
	     * @param   in              the {@link ByteBuf} from which to read data
	     * @return  frame           the {@link ByteBuf} which represent the frame or {@code null} if no frame could
	     *                          be created.
	     */
		protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
	        if (discardingTooLongFrame) {
	            long bytesToDiscard = this.bytesToDiscard;
	            int localBytesToDiscard = (int) Math.min(bytesToDiscard, in.readableBytes());
	            in.skipBytes(localBytesToDiscard);
	            bytesToDiscard -= localBytesToDiscard;
	            this.bytesToDiscard = bytesToDiscard;

	            failIfNecessary(false);
	        }

	        if (in.readableBytes() < lengthFieldEndOffset) {
	            return null;
	        }

	        int actualLengthFieldOffset = in.readerIndex() + lengthFieldOffset;
	        in = in.order(byteOrder);
	        short type = in.getUnsignedByte(in.readerIndex());
	        short extFrameLength = in.getUnsignedByte(actualLengthFieldOffset);
	        int bodyFrameLength = in.getUnsignedShort(actualLengthFieldOffset + 1);
	        long frameLength = extFrameLength + bodyFrameLength;

	        if (frameLength < 0) {
	            in.skipBytes(lengthFieldEndOffset);
	            throw new CorruptedFrameException(
	                    "negative pre-adjustment length field: " + frameLength);
	        }

	        frameLength += lengthAdjustment + lengthFieldEndOffset;

	        if (frameLength < lengthFieldEndOffset) {
	            in.skipBytes(lengthFieldEndOffset);
	            throw new CorruptedFrameException(
	                    "Adjusted frame length (" + frameLength + ") is less " +
	                    "than lengthFieldEndOffset: " + lengthFieldEndOffset);
	        }

	        if (frameLength > maxFrameLength) {
	            long discard = frameLength - in.readableBytes();
	            tooLongFrameLength = frameLength;

	            if (discard < 0) {
	                // buffer contains more bytes then the frameLength so we can discard all now
	                in.skipBytes((int) frameLength);
	            } else {
	                // Enter the discard mode and discard everything received so far.
	                discardingTooLongFrame = true;
	                bytesToDiscard = discard;
	                in.skipBytes(in.readableBytes());
	            }
	            failIfNecessary(true);
	            return null;
	        }

	        // never overflows because it's less than maxFrameLength
	        int frameLengthInt = (int) frameLength;
	        if (in.readableBytes() < frameLengthInt) {
	            return null;
	        }

	        if (initialBytesToStrip > frameLengthInt) {
	            in.skipBytes(frameLengthInt);
	            throw new CorruptedFrameException(
	                    "Adjusted frame length (" + frameLength + ") is less " +
	                    "than initialBytesToStrip: " + initialBytesToStrip);
	        }
	        in.skipBytes(initialBytesToStrip);

	        // extract frame
	        int readerIndex = in.readerIndex();
	        int actualFrameLength = frameLengthInt - initialBytesToStrip;
	        ByteBuf frame = extractFrame(ctx, in, readerIndex, actualFrameLength);
	        in.readerIndex(readerIndex + actualFrameLength);
	        
	        //frame to object
	        return new FtdcProtocol(type, extFrameLength, bodyFrameLength, frame, this.compress);
	    }

	    private void failIfNecessary(boolean firstDetectionOfTooLongFrame) {
	        if (bytesToDiscard == 0) {
	            // Reset to the initial state and tell the handlers that
	            // the frame was too large.
	            long tooLongFrameLength = this.tooLongFrameLength;
	            this.tooLongFrameLength = 0;
	            discardingTooLongFrame = false;
	            if (!failFast ||
	                failFast && firstDetectionOfTooLongFrame) {
	                fail(tooLongFrameLength);
	            }
	        } else {
	            // Keep discarding and notify handlers if necessary.
	            if (failFast && firstDetectionOfTooLongFrame) {
	                fail(tooLongFrameLength);
	            }
	        }
	    }

	    /**
	     * Extract the sub-region of the specified buffer.
	     * <p>
	     * If you are sure that the frame and its content are not accessed after
	     * the current {@link #decode(ChannelHandlerContext, ByteBuf)}
	     * call returns, you can even avoid memory copy by returning the sliced
	     * sub-region (i.e. <tt>return buffer.slice(index, length)</tt>).
	     * It's often useful when you convert the extracted frame into an object.
	     * Refer to the source code of {@link ObjectDecoder} to see how this method
	     * is overridden to avoid memory copy.
	     */
	    protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer, int index, int length) {
	        return buffer.retainedSlice(index, length);
	    }

	    private void fail(long frameLength) {
	        if (frameLength > 0) {
	            throw new TooLongFrameException(
	                            "Adjusted frame length exceeds " + maxFrameLength +
	                            ": " + frameLength + " - discarded");
	        } else {
	            throw new TooLongFrameException(
	                            "Adjusted frame length exceeds " + maxFrameLength +
	                            " - discarding");
	        }
	    }

}
