package com.mozi.chintms.basicDataManage.model;

import java.io.Serializable;
import java.util.List;

public class RegionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5047826832160731593L;
	/**
	 * 省份ID
	 */
	private String proID;
	/**
	 * 省份
	 */
	private String proName;
	/**
	 * 市级ID
	 */
	private String cityID;
	/**
	 * 省下包含市级
	 */
	private String cityName;
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProID() {
		return proID;
	}
	public void setProID(String proID) {
		this.proID = proID;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

}
