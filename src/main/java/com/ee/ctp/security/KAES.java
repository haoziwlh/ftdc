package com.ee.ctp.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES认证
 * 
 * @author ee 2017年11月2日 下午5:32:34
 *
 */
public class KAES {
	public static final KAES DEFAULT_INSTANCE = new KAES();
	private static final String ENCRYT_MODE = "AES/ECB/NoPadding";
	private static final String ENCRYPT = "AES";
	private static final int DIL = 0x85;
	private static final int SIGN_BLOCK = 0xff;
	private static final int BOUND_LOW = 0x9;
	private static final int BOUND_HIGH = 0x23;
	
	private static final int BOUND_MID_ADD = 0x57;
	private static final int BOUND_HIGH_ADD = 0x1d;
	private static final int BOUND_LOW_ADD = 0x30;

	/**
	 * 认证
	 * @param src
	 * @param key
	 * @return
	 */
	public byte[] encrypt(byte[] src, byte[] key) {
		byte[] aesEncrypt = aesEncrypt(src, key);
		return auth(aesEncrypt);
	}

	private byte[] aesEncrypt(byte[] src, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance(ENCRYT_MODE);
			SecretKeySpec skeySpec = new SecretKeySpec(key, ENCRYPT);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			return cipher.doFinal(src);
		} catch (Exception e) {
			throw new KAESException(e);
		}
	}
	
	private byte[] auth(byte[] aesData) {
		byte [] authData = new byte[aesData.length]; 
		for (int i = 0; i < aesData.length; i++) {
			int unsignData = aesData[i] & SIGN_BLOCK;
			int edx = unsignData;
			edx >>= 1;
			int eax = edx;
			eax *= DIL;
			edx = eax >> 12;
			int ecx = edx * 2;
			edx <<= 6;
			edx -= ecx;
			unsignData -= edx;
			if(unsignData > BOUND_LOW && unsignData <= BOUND_HIGH) {
				unsignData += BOUND_MID_ADD;
			}else if(unsignData > BOUND_HIGH){
				unsignData += BOUND_HIGH_ADD;
			}else {
				unsignData += BOUND_LOW_ADD;
			}
			authData[i] = (byte)unsignData;
		}
		return authData;
	}
	
}
