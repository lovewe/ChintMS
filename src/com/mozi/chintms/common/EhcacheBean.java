package com.mozi.chintms.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用于触发缓存
 * ehcache service 层无法通过string对象触发缓存,添加本实体bean储存并触发
 * @author wx
 *
 */
public class EhcacheBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1807995334299351273L;
	
	private List<Map> objEhcache;   //缓存对象

	public List<Map> getObjEhcache() {
		return objEhcache;
	}

	public void setObjEhcache(List<Map> objEhcache) {
		this.objEhcache = objEhcache;
	}

}
