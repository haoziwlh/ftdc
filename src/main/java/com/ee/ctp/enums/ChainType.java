package com.ee.ctp.enums;
/**
 * 当长包被分包时，一种区分方式通过chain来区分
 * @author ee
 *
 */
public enum ChainType {
	/**
	 * 结束
	 */
	END(0x4c),
	/**
	 * 未结束
	 */
	MID(0x43);
	
	private int chain;
	private ChainType(int chain) {
		this.chain = chain;
	}
	
	public int chain() {
		return this.chain;
	}
}
