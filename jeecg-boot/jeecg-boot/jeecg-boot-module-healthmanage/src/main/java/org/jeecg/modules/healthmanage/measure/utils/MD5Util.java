package org.jeecg.modules.healthmanage.measure.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 */
public class MD5Util {

	private static final String SALT_USER_PASSWORD = "md5-password-salt";

	public static String encode(String text) {
		byte[] md5Bytes = getMd5Bytes(text);
		return bytesToHex3(md5Bytes);
	}

	private static byte[] getMd5Bytes(String text) {
		text = text + SALT_USER_PASSWORD;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = null;
		try {
			byteArray = text.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(byteArray);
		return md5Bytes;
	}

	static String bytesToHex1(byte[] md5Array) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < md5Array.length; i++) {
			int temp = 0xff & md5Array[i];// TODO:此处为什么添加 0xff & ?
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1) {// 如果是十六进制的0f，默认只显示f，此时要补上0
				strBuilder.append("0").append(hexString);
			} else {
				strBuilder.append(hexString);
			}
		}
		return strBuilder.toString();
	}

	// 通过java提供的BigInteger 完成byte->HexString
	static String bytesToHex2(byte[] md5Array) {
		BigInteger bigInt = new BigInteger(1, md5Array);
		return bigInt.toString(16);
	}

	// 通过位运算 将字节数组到十六进制的转换
	static String bytesToHex3(byte[] byteArray) {
		char[] hexDigits = "0123456789abcdef".toCharArray();
		char[] charArray = new char[32];
		int index = 0;
		for (byte b : byteArray) {
			charArray[index++] = hexDigits[b >>> 4 & 0xf];
			charArray[index++] = hexDigits[b & 0xf];
		}
		return new String(charArray);
	}
}