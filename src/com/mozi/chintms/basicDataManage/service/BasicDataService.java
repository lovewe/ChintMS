package com.mozi.chintms.basicDataManage.service;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;


/**
 * 基础数据操作
 * @author wx
 *
 */
public interface BasicDataService {

	
	/**
	 * 获取省市信息
	 * @return
	 */
	public List<IdentityHashMap<String,Object>> getRegion();
	
	/**
	 * 自定义用户唯一KEY
	 * @param map
	 * @return
	 */
	public Map<String,String> GetUserUniqueKey(Map<String,String> map);
}
