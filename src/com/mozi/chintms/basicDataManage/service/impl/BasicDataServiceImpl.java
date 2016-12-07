package com.mozi.chintms.basicDataManage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.basicDataManage.dao.BasicDataDao;
import com.mozi.chintms.basicDataManage.model.RegionInfo;
import com.mozi.chintms.basicDataManage.service.BasicDataService;
import com.mozi.chintms.common.BaseService;

import org.apache.log4j.Logger;


@Service("basicDataService")
public class BasicDataServiceImpl extends BaseService implements BasicDataService{

	private static final Logger logger = Logger.getLogger(BasicDataServiceImpl.class);
	@Autowired
	private BasicDataDao basicDataServiceImpl;

	@Override
	public List<IdentityHashMap<String,Object>> getRegion() {
		// TODO Auto-generated method stub
		JSONArray json;
		String strJson = null;
		//获取省份列表
		List<RegionInfo> listProvince = basicDataServiceImpl.getProvince();
		//获取城市列表 
		List<RegionInfo> listCity = basicDataServiceImpl.getCity();

		IdentityHashMap<String,Object> mapRegion= null;
		List<IdentityHashMap<String,Object>> listGroup = new ArrayList<IdentityHashMap<String,Object>>();
		//省份及城市
		List<String> cityInfo =null;
		//遍历省
		for (RegionInfo province : listProvince) {
			cityInfo =new ArrayList<String>();

			mapRegion =new IdentityHashMap<String,Object> ();
			//遍历市
			for (RegionInfo city : listCity) {
				if(province.getProID().equals(city.getProID())){
					cityInfo.add(city.getCityName());
	            }
			}
			//添加省份
			mapRegion.put(new String("province"), province.getProName());
			//添加省及下属市
			mapRegion.put(new String("cities"), cityInfo);
			//打包组
			listGroup.add(mapRegion);
		}

		try {
			json = JSONArray.fromObject(listGroup);
			strJson = json.toString();
		} catch (Exception ce) {
			logger.error(ce.getMessage());
		}

		return listGroup;
		
	}

	@Override
	public Map<String, String> GetUserUniqueKey(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
