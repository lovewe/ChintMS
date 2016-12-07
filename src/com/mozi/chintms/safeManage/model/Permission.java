package com.mozi.chintms.safeManage.model;

import com.mozi.chintms.common.BaseDomain;

/**
 * 菜单权限bean
 * 
 * @author zlm
 * 
 */
public class Permission extends BaseDomain {

	private static final long serialVersionUID = 6105381474758085215L;

	private String permId;		//菜单ID
	private String permName;	//菜单名
	private String pPermId;		//父菜单ID
	private String url;			//菜单URL
	private String menuClass;	//菜单Class
	private Integer orderNum;		//菜单顺序
	private String company;			//公司列表
	private int companyId;			//公司ID
	private String userName;        //用户名
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPermId() {
		return permId;
	}
	public void setPermId(String permId) {
		this.permId = permId;
	}
	public String getPermName() {
		return permName;
	}
	public void setPermName(String permName) {
		this.permName = permName;
	}
	public String getpPermId() {
		return pPermId;
	}
	public void setpPermId(String pPermId) {
		this.pPermId = pPermId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
