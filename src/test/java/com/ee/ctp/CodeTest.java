package com.ee.ctp;

import junit.framework.TestCase;

public class CodeTest extends TestCase{

	//191, 205, 187, 167, 182, 203 BFcdbb
	public void testCode() throws Exception {
		byte[] b1 = "客户端".getBytes("gbk");
		byte[] btransfered = new byte[b1.length];
		for (int i = 0; i < b1.length; i++) {
			byte b = b1[i];
			btransfered[i] = (byte)(b & 0xff);
		}
		assertEquals("客户端", new String(btransfered, "gbk"));
	}
	
	public void testB() throws Exception {
		//d5fdc8b7
		byte b1 = (byte)(0xb6 & 0xff);
		byte b2 = (byte)(0xcb & 0xff);
		byte b3 = (byte)(0xc8 & 0xff);
		byte b4 = (byte)(0xcf & 0xff);
		byte[] b = new byte[]{b1,b2,b3,b4};
		System.out.println(new String(b,"gbk"));
	}
	
}
