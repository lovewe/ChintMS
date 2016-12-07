package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

/**
 * 根据手机号获取用户概要信息
 * @author wx
 *
 */
public class UserInfoByPhoneInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4387995709121806416L;

	private String personID;
	private String  nickname;
	private String  fullname;
	private String  phoneNum;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
