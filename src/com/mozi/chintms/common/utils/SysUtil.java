package com.mozi.chintms.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本系统
 * @author zlm
 *
 */
public class SysUtil {
	
	/**
	 * 业务类型列表
	 */
	private static Map<String,String> bussTypeMap=null;	
	/**
	 * 公司列表
	 */
	private static Map<String,String> companyMap=null;
	
	/**
	 * 渠道列表
	 */
	private static Map<String,String> channelMap=null;
	
	/**
	 * 初始化业务列表
	 */
	private static void initBussTypeMap(){
		bussTypeMap = new HashMap<String,String>();
		bussTypeMap.put("1", "销售");
		bussTypeMap.put("2", "财务");
	}
	
	/**
	 * 初始化公司列表
	 */
	private static void initCompanyMap(){
		companyMap = new HashMap<String,String>();
		companyMap.put("1", "低压");
		companyMap.put("2", "诺雅克");
		companyMap.put("3", "正泰电工");
	}
	
	/**
	 * 初始化公司列表
	 */
	private static void initChannelMap(){
		channelMap = new HashMap<String,String>();
		channelMap.put("1", "国内");
		channelMap.put("2", "国贸");
	}
	
	/**
	 * 根据ID,获取业务
	 * @param id
	 * @return
	 */
	public static String getBussType(String id){
		if(bussTypeMap != null){
			return bussTypeMap.get(id);
		}else{
			initBussTypeMap();
			return bussTypeMap.get(id);
		}
	}
	
	/**
	 * 根据ID,获取公司
	 * @param id
	 * @return
	 */
	public static String getCompany(String id){
		if(companyMap != null){
			return companyMap.get(id);
		}else{
			initCompanyMap();
			return companyMap.get(id);
		}
	}
	
	/**
	 * 根据ID,获取渠道
	 * @param id
	 * @return
	 */
	public static String getChannel(String id){
		if(channelMap != null){
			return channelMap.get(id);
		}else{
			initChannelMap();
			return channelMap.get(id);
		}
	}
}
