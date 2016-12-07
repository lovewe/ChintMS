package com.mozi.chintms.common;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheManager manager = CacheManager.create();
		manager.addCache("testCache");  
		Cache test = manager.getCache("testCache");  
		test.put(new Element("key1", "value1"));  
//		manager.shutdown(); 
		
		Element e =	test.get("key1");
		
		System.out.println(e.getObjectKey());
		System.out.println(e.getObjectValue());
	}
	
	/**
	 * 生成6位随机验证码
	 * @return
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}

}
