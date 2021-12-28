package com.vote.MyHelper;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
	public static String getMD5Str(String str){
		byte[] digest = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			digest  = md5.digest(str.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String md5Str = new BigInteger(1, digest).toString(16);
		return md5Str;
	}
}
