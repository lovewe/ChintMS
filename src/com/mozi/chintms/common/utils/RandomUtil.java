package com.mozi.chintms.common.utils;

import java.util.UUID;

/**
 * 用来生成随机号成
 * @author zlm
 *
 */
public class RandomUtil {

	/**
	 * 生成一个36的UUID号
	 * @return
	 */
	public static String randomUUID(){
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * 生成一个32的UUID号，只包含数字与大写字母
	 * @return
	 */
	public static String randomString(){
		return RandomUtil.randomUUID().replaceAll("-","");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(RandomUtil.randomString());
	}

}
