package com.mozi.chintms.basicDataManage.dao;

import java.util.List;
import java.util.Map;

import com.mozi.chintms.basicDataManage.model.RegionInfo;

/**
 * BasicData Dao
 * @author wx
 *
 */
public interface BasicDataDao {
	
	/**
	 * 获取省信息
	 * @return
	 */
	public List<RegionInfo> getProvince();
	
	/**
	 * 获取市信息
	 * @return
	 */
	public List<RegionInfo> getCity();
	
	/**
	 * 自定义用户唯一KEY
	 * @param map
	 * @return
	 */
	public Map<String,String> GetUserUniqueKey(Map<String,String> map);
}
