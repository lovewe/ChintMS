package com.mozi.chintms.smsManage.model;

import java.io.Serializable;

/**
 * 手机用户信息 
 * Model
 * @author wx
 *
 */
public class PhoneInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2413897742651716366L;
	
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String phoneNum;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户状态
	 */
	private Integer status;
	
	/**
	 * 编辑短信
	 */
	private String smsMessage;
	/**
	 * 验证码
	 */
	private String vCode;
	/**
	 * 头像地址
	 */
	private Integer imgFilePath;
	
	public Integer getImgFilePath() {
		return imgFilePath;
	}
	public void setImgFilePath(Integer imgFilePath) {
		this.imgFilePath = imgFilePath;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	public String getvCode() {
		return vCode;
	}
	public void setvCode(String vCode) {
		this.vCode = vCode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	


	
}
