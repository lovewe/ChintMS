package com.mozi.chintms.login.model;

import com.mozi.chintms.common.BaseDomain;

public class PhoneLoginUserInfo extends BaseDomain {

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
	private String username;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 手机
	 */
	private String phoneNum;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



}
