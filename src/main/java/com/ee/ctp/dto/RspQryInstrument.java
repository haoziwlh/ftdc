package com.ee.ctp.dto;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ee.ctp.ApplicationRuntime;
import com.ee.ctp.enums.business.FtdcCombinationType;
import com.ee.ctp.enums.business.FtdcExchange;
import com.ee.ctp.enums.business.FtdcInstLifePhaseType;
import com.ee.ctp.enums.business.FtdcMaxMarginSideAlgorithmType;
import com.ee.ctp.enums.business.FtdcOptionsType;
import com.ee.ctp.enums.business.FtdcPositionDateType;
import com.ee.ctp.enums.business.FtdcPositionType;
import com.ee.ctp.enums.business.FtdcProductClassType;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:19:41
 *
 */
public class RspQryInstrument implements FtdcRsp{
	// 31
	private String instrumentID;
	// 9
	private FtdcExchange exchangeID;
	// 21
	private String instrumentName;
	// 31
	private String exchangeInstID;
	// 31
	private String productID;
	// 1
	private FtdcProductClassType productClass;
	// 4
	private int deliveryYear;
	// 4
	private int deliveryMonth;
	// 4
	private int maxMarketOrderVolume;
	// 4
	private int minMarketOrderVolume;
	// 4
	private int maxLimitOrderVolume;
	// 4
	private int minLimitOrderVolume;
	// 4
	private int volumeMultiple;
	// 8
	private double priceTick;
	// 9
	private String createDate;
	// 9
	private String openDate;
	// 9
	private String expireDate;
	// 9
	private String startDelivDate;
	// 9
	private String endDelivDate;
	// 1
	private FtdcInstLifePhaseType instLifePhase;
	// 4
	private int isTrading;
	// 1
	private FtdcPositionType positionType;
	// 1
	private FtdcPositionDateType positionDateType;
	// 8
	private double longMarginRatio;
	// 8
	private double shortMarginRatio;
	// 1
	private FtdcMaxMarginSideAlgorithmType maxMarginSideAlgorithm;
	// 31
	private String underlyingInstrID;
	// 8
	private double strikePrice;
	// 1
	private FtdcOptionsType optionsType;
	// 8
	private double underlyingMultiple;
	// 1
	private FtdcCombinationType combinationType;

	/**
	 * @return the instrumentID
	 */
	public String getInstrumentID() {
		return instrumentID;
	}

	/**
	 * @param instrumentID the instrumentID to set
	 */
	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}

	/**
	 * @return the exchangeID
	 */
	public FtdcExchange getExchangeID() {
		return exchangeID;
	}

	/**
	 * @param exchangeID the exchangeID to set
	 */
	public void setExchangeID(FtdcExchange exchangeID) {
		this.exchangeID = exchangeID;
	}

	/**
	 * @return the instrumentName
	 */
	public String getInstrumentName() {
		return instrumentName;
	}

	/**
	 * @param instrumentName the instrumentName to set
	 */
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	/**
	 * @return the exchangeInstID
	 */
	public String getExchangeInstID() {
		return exchangeInstID;
	}

	/**
	 * @param exchangeInstID the exchangeInstID to set
	 */
	public void setExchangeInstID(String exchangeInstID) {
		this.exchangeInstID = exchangeInstID;
	}

	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * @return the productClass
	 */
	public FtdcProductClassType getProductClass() {
		return productClass;
	}

	/**
	 * @param productClass the productClass to set
	 */
	public void setProductClass(FtdcProductClassType productClass) {
		this.productClass = productClass;
	}

	/**
	 * @return the deliveryYear
	 */
	public int getDeliveryYear() {
		return deliveryYear;
	}

	/**
	 * @param deliveryYear the deliveryYear to set
	 */
	public void setDeliveryYear(int deliveryYear) {
		this.deliveryYear = deliveryYear;
	}

	/**
	 * @return the deliveryMonth
	 */
	public int getDeliveryMonth() {
		return deliveryMonth;
	}

	/**
	 * @param deliveryMonth the deliveryMonth to set
	 */
	public void setDeliveryMonth(int deliveryMonth) {
		this.deliveryMonth = deliveryMonth;
	}

	/**
	 * @return the maxMarketOrderVolume
	 */
	public int getMaxMarketOrderVolume() {
		return maxMarketOrderVolume;
	}

	/**
	 * @param maxMarketOrderVolume the maxMarketOrderVolume to set
	 */
	public void setMaxMarketOrderVolume(int maxMarketOrderVolume) {
		this.maxMarketOrderVolume = maxMarketOrderVolume;
	}

	/**
	 * @return the minMarketOrderVolume
	 */
	public int getMinMarketOrderVolume() {
		return minMarketOrderVolume;
	}

	/**
	 * @param minMarketOrderVolume the minMarketOrderVolume to set
	 */
	public void setMinMarketOrderVolume(int minMarketOrderVolume) {
		this.minMarketOrderVolume = minMarketOrderVolume;
	}

	/**
	 * @return the maxLimitOrderVolume
	 */
	public int getMaxLimitOrderVolume() {
		return maxLimitOrderVolume;
	}

	/**
	 * @param maxLimitOrderVolume the maxLimitOrderVolume to set
	 */
	public void setMaxLimitOrderVolume(int maxLimitOrderVolume) {
		this.maxLimitOrderVolume = maxLimitOrderVolume;
	}

	/**
	 * @return the minLimitOrderVolume
	 */
	public int getMinLimitOrderVolume() {
		return minLimitOrderVolume;
	}

	/**
	 * @param minLimitOrderVolume the minLimitOrderVolume to set
	 */
	public void setMinLimitOrderVolume(int minLimitOrderVolume) {
		this.minLimitOrderVolume = minLimitOrderVolume;
	}

	/**
	 * @return the volumeMultiple
	 */
	public int getVolumeMultiple() {
		return volumeMultiple;
	}

	/**
	 * @param volumeMultiple the volumeMultiple to set
	 */
	public void setVolumeMultiple(int volumeMultiple) {
		this.volumeMultiple = volumeMultiple;
	}

	/**
	 * @return the priceTick
	 */
	public double getPriceTick() {
		return priceTick;
	}

	/**
	 * @param priceTick the priceTick to set
	 */
	public void setPriceTick(double priceTick) {
		this.priceTick = priceTick;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the openDate
	 */
	public String getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return the expireDate
	 */
	public String getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return the startDelivDate
	 */
	public String getStartDelivDate() {
		return startDelivDate;
	}

	/**
	 * @param startDelivDate the startDelivDate to set
	 */
	public void setStartDelivDate(String startDelivDate) {
		this.startDelivDate = startDelivDate;
	}

	/**
	 * @return the endDelivDate
	 */
	public String getEndDelivDate() {
		return endDelivDate;
	}

	/**
	 * @param endDelivDate the endDelivDate to set
	 */
	public void setEndDelivDate(String endDelivDate) {
		this.endDelivDate = endDelivDate;
	}

	/**
	 * @return the instLifePhase
	 */
	public FtdcInstLifePhaseType getInstLifePhase() {
		return instLifePhase;
	}

	/**
	 * @param instLifePhase the instLifePhase to set
	 */
	public void setInstLifePhase(FtdcInstLifePhaseType instLifePhase) {
		this.instLifePhase = instLifePhase;
	}

	/**
	 * @return the isTrading
	 */
	public int getIsTrading() {
		return isTrading;
	}

	/**
	 * @param isTrading the isTrading to set
	 */
	public void setIsTrading(int isTrading) {
		this.isTrading = isTrading;
	}

	/**
	 * @return the positionType
	 */
	public FtdcPositionType getPositionType() {
		return positionType;
	}

	/**
	 * @param positionType the positionType to set
	 */
	public void setPositionType(FtdcPositionType positionType) {
		this.positionType = positionType;
	}

	/**
	 * @return the positionDateType
	 */
	public FtdcPositionDateType getPositionDateType() {
		return positionDateType;
	}

	/**
	 * @param positionDateType the positionDateType to set
	 */
	public void setPositionDateType(FtdcPositionDateType positionDateType) {
		this.positionDateType = positionDateType;
	}

	/**
	 * @return the longMarginRatio
	 */
	public double getLongMarginRatio() {
		return longMarginRatio;
	}

	/**
	 * @param longMarginRatio the longMarginRatio to set
	 */
	public void setLongMarginRatio(double longMarginRatio) {
		this.longMarginRatio = longMarginRatio;
	}

	/**
	 * @return the shortMarginRatio
	 */
	public double getShortMarginRatio() {
		return shortMarginRatio;
	}

	/**
	 * @param shortMarginRatio the shortMarginRatio to set
	 */
	public void setShortMarginRatio(double shortMarginRatio) {
		this.shortMarginRatio = shortMarginRatio;
	}

	/**
	 * @return the maxMarginSideAlgorithm
	 */
	public FtdcMaxMarginSideAlgorithmType getMaxMarginSideAlgorithm() {
		return maxMarginSideAlgorithm;
	}

	/**
	 * @param maxMarginSideAlgorithm the maxMarginSideAlgorithm to set
	 */
	public void setMaxMarginSideAlgorithm(FtdcMaxMarginSideAlgorithmType maxMarginSideAlgorithm) {
		this.maxMarginSideAlgorithm = maxMarginSideAlgorithm;
	}

	/**
	 * @return the underlyingInstrID
	 */
	public String getUnderlyingInstrID() {
		return underlyingInstrID;
	}

	/**
	 * @param underlyingInstrID the underlyingInstrID to set
	 */
	public void setUnderlyingInstrID(String underlyingInstrID) {
		this.underlyingInstrID = underlyingInstrID;
	}

	/**
	 * @return the strikePrice
	 */
	public double getStrikePrice() {
		return strikePrice;
	}

	/**
	 * @param strikePrice the strikePrice to set
	 */
	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}

	/**
	 * @return the optionsType
	 */
	public FtdcOptionsType getOptionsType() {
		return optionsType;
	}

	/**
	 * @param optionsType the optionsType to set
	 */
	public void setOptionsType(FtdcOptionsType optionsType) {
		this.optionsType = optionsType;
	}

	/**
	 * @return the underlyingMultiple
	 */
	public double getUnderlyingMultiple() {
		return underlyingMultiple;
	}

	/**
	 * @param underlyingMultiple the underlyingMultiple to set
	 */
	public void setUnderlyingMultiple(double underlyingMultiple) {
		this.underlyingMultiple = underlyingMultiple;
	}

	/**
	 * @return the combinationType
	 */
	public FtdcCombinationType getCombinationType() {
		return combinationType;
	}

	/**
	 * @param combinationType the combinationType to set
	 */
	public void setCombinationType(FtdcCombinationType combinationType) {
		this.combinationType = combinationType;
	}

	@Override
	public RspQryInstrument parseFrom(ByteBuf body, RspError error) {
		try {
			RspQryInstrument info = new RspQryInstrument();
			byte[] instrumentID = new byte[31];
			body.readBytes(instrumentID);
			info.setInstrumentID(StringUtils.trimToEmpty(new String(instrumentID)));
			byte[] exchangeID = new byte[9];
			body.readBytes(exchangeID);
			info.setExchangeID(FtdcExchange.parseFrom(StringUtils.trimToEmpty(new String(exchangeID))));
			byte[] instrumentName = new byte[21];
			body.readBytes(instrumentName);
			try {
				info.setInstrumentName(StringUtils.trimToEmpty(new String(instrumentName, ApplicationRuntime.conf().defaultEncoding())));
			} catch (UnsupportedEncodingException e) {
				// nop
			}
			byte[] exchangeInstID = new byte[31];
			body.readBytes(exchangeInstID);
			info.setExchangeInstID(StringUtils.trimToEmpty(new String(exchangeInstID)));
			byte[] productID = new byte[31];
			body.readBytes(productID);
			info.setProductID(StringUtils.trimToEmpty(new String(productID)));
			byte[] productClass = new byte[1];
			body.readBytes(productClass);
			info.setProductClass(FtdcProductClassType.parseFrom(StringUtils.trimToEmpty(new String(productClass))));
			info.setDeliveryYear(body.readInt());
			info.setDeliveryMonth(body.readInt());
			info.setMaxMarketOrderVolume(body.readInt());
			info.setMinMarketOrderVolume(body.readInt());
			info.setMaxLimitOrderVolume(body.readInt());
			info.setMinLimitOrderVolume(body.readInt());
			info.setVolumeMultiple(body.readInt());
			info.setPriceTick(body.readDouble());
			byte[] createDate = new byte[9];
			body.readBytes(createDate);
			info.setCreateDate(StringUtils.trimToEmpty(new String(createDate)));
			byte[] openDate = new byte[9];
			body.readBytes(openDate);
			info.setOpenDate(StringUtils.trimToEmpty(new String(openDate)));
			byte[] expireDate = new byte[9];
			body.readBytes(expireDate);
			info.setExpireDate(StringUtils.trimToEmpty(new String(expireDate)));
			byte[] startDelivDate = new byte[9];
			body.readBytes(startDelivDate);
			info.setStartDelivDate(StringUtils.trimToEmpty(new String(startDelivDate)));
			byte[] endDelivDate = new byte[9];
			body.readBytes(endDelivDate);
			info.setEndDelivDate(StringUtils.trimToEmpty(new String(endDelivDate)));
			byte[] instLifePhase = new byte[1];
			body.readBytes(instLifePhase);
			info.setInstLifePhase(FtdcInstLifePhaseType.parseFrom(StringUtils.trimToEmpty(new String(instLifePhase))));
			info.setIsTrading(body.readInt());
			byte[] positionType = new byte[1];
			body.readBytes(positionType);
			info.setPositionType(FtdcPositionType.parseFrom(StringUtils.trimToEmpty(new String(positionType))));
			byte[] positionDateType = new byte[1];
			body.readBytes(positionDateType);
			info.setPositionDateType(
					FtdcPositionDateType.parseFrom(StringUtils.trimToEmpty(new String(positionDateType))));
			info.setLongMarginRatio(body.readDouble());
			info.setShortMarginRatio(body.readDouble());
			byte[] maxMarginSideAlgorithm = new byte[1];
			body.readBytes(maxMarginSideAlgorithm);
			info.setMaxMarginSideAlgorithm(FtdcMaxMarginSideAlgorithmType
					.parseFrom(StringUtils.trimToEmpty(new String(maxMarginSideAlgorithm))));
			byte[] underlyingInstrID = new byte[31];
			body.readBytes(underlyingInstrID);
			info.setUnderlyingInstrID(StringUtils.trimToEmpty(new String(underlyingInstrID)));
			info.setStrikePrice(body.readDouble());
			byte[] optionsType = new byte[1];
			body.readBytes(optionsType);
			info.setOptionsType(FtdcOptionsType.parseFrom(StringUtils.trimToEmpty(new String(optionsType))));
			info.setUnderlyingMultiple(body.readDouble());
			byte[] combinationType = new byte[1];
			body.readBytes(combinationType);
			info.setCombinationType(
					FtdcCombinationType.parseFrom(StringUtils.trimToEmpty(new String(combinationType))));
			return info;
		} finally {
			ReferenceCountUtil.release(body);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RspQryInstrument [instrumentID=" + instrumentID + ", exchangeID=" + exchangeID + ", instrumentName="
				+ instrumentName + ", exchangeInstID=" + exchangeInstID + ", productID=" + productID + ", productClass="
				+ productClass + ", deliveryYear=" + deliveryYear + ", deliveryMonth=" + deliveryMonth
				+ ", maxMarketOrderVolume=" + maxMarketOrderVolume + ", minMarketOrderVolume=" + minMarketOrderVolume
				+ ", maxLimitOrderVolume=" + maxLimitOrderVolume + ", minLimitOrderVolume=" + minLimitOrderVolume
				+ ", volumeMultiple=" + volumeMultiple + ", priceTick=" + priceTick + ", createDate=" + createDate
				+ ", openDate=" + openDate + ", expireDate=" + expireDate + ", startDelivDate=" + startDelivDate
				+ ", endDelivDate=" + endDelivDate + ", instLifePhase=" + instLifePhase + ", isTrading=" + isTrading
				+ ", positionType=" + positionType + ", positionDateType=" + positionDateType + ", longMarginRatio="
				+ longMarginRatio + ", shortMarginRatio=" + shortMarginRatio + ", maxMarginSideAlgorithm="
				+ maxMarginSideAlgorithm + ", underlyingInstrID=" + underlyingInstrID + ", strikePrice=" + strikePrice
				+ ", optionsType=" + optionsType + ", underlyingMultiple=" + underlyingMultiple + ", combinationType="
				+ combinationType + "]";
	}
	
}
