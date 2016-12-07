package com.mozi.chintms.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * DM5加密工具类
 * @author zlm
 *
 */
public class Md5Util {
	/**
	 * ma5加密
	 * 
	 * @param plainText
	 * @return
	 */
	String salt = null;

	public static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString().substring(8, 24);
	}

	/**
	 * 获取加密后的密码
	 * 
	 * @param password
	 * @return
	 */
	public Map<String, String> md5Salt(String password,String salt) {
		if (salt == null || salt.equals("")) {
			salt = Md5Util.getSalt();
		} else {
			System.out.println("salt:" + salt);
		}
		password = md5Hex(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("salt", salt);
		map.put("password", new String(cs));
		return map;
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要
	 */
	public String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 校验密码是否正确
	 */
	public boolean verify(String password, String md5) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(password + salt).equals(new String(cs1));
	}

	public static void main(String[] args) {
		Md5Util md5Util = new Md5Util();
		System.out.println(md5Util.md5Salt("111111", "8759642526924260").get("password"));
		System.out.println(md5Util.md5Salt("111111", "").get("password"));

	}
	
	/**
	 * 生成MD5密钥
	 * @return
	 */
	public static String getSalt(){
		String salt = null;
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		salt = sb.toString();
		return salt;
	}
	
}
