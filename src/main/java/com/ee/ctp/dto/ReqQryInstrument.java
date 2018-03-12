package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.enums.business.FtdcExchange;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:58
 *
 */
public class ReqQryInstrument implements FtdcReq{
	private byte[] instrumentID = new byte[31];
	private byte[] exchangeID = new byte[9];
	private byte[] exchangeInstID = new byte[31];
	private byte[] productID = new byte[31];

	public void setInstrumentID(String instrumentID) {
		if (StringUtils.isNotEmpty(instrumentID)) {
			System.arraycopy(instrumentID.getBytes(), 0, this.instrumentID, 0, instrumentID.getBytes().length);
		}
	}

	public void setExchangeID(FtdcExchange exchangeID) {
		if (exchangeID != null) {
			System.arraycopy(exchangeID.getExchange().getBytes(), 0, this.exchangeID, 0,
					exchangeID.getExchange().getBytes().length);
		}
	}

	public void setExchangeInstID(String exchangeInstID) {
		if (StringUtils.isNotEmpty(exchangeInstID)) {
			System.arraycopy(exchangeInstID.getBytes(), 0, this.exchangeInstID, 0, exchangeInstID.getBytes().length);
		}
	}

	public void setProductID(String productID) {
		if (StringUtils.isNotEmpty(productID)) {
			System.arraycopy(productID.getBytes(), 0, this.productID, 0, productID.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(instrumentID);
		buffer.writeBytes(exchangeID);
		buffer.writeBytes(exchangeInstID);
		buffer.writeBytes(productID);

		return buffer;
	}
}
