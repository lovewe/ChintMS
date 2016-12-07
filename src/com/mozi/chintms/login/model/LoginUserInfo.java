package com.mozi.chintms.login.model;

import com.mozi.chintms.common.BaseDomain;

/**
 * 用户Bean 与用户表映射
 * 
 * @author wx
 */
public class LoginUserInfo extends BaseDomain {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1559676504713417277L;

	/**
	 * 用户ID
	 */
	private String user_id;

	/**
	 * 用户名
	 */
	private String user_name;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 部门
	 */
	private String department;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}