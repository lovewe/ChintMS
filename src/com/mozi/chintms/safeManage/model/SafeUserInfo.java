package com.mozi.chintms.safeManage.model;

import java.io.Serializable;

/**
 * 用户管理bean
 * @author zlm
 *
 */
public class SafeUserInfo implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1559676504713417277L;

	/**
	 * 用户ID
	 */
	private String personID;
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 昵称(用户名)
	 */
	private String nickname;


	/**
	 * 真实名 fullName
	 */
	private String fullname;
	/**
	 * 省份
	 */
	private String provinceName;


	/**
     * 市
     */
	private String cityName;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 旧密码
	 */
	private String oldPwd;

	/**
	 * 部门
	 */
	private String department;
	
	/**
	 * 所属角色ID
	 */
	private String roleId;
	/**
	 * 所属角色名
	 */
	private String roleName;
	
	/**
	 * 生日
	 */
	private String birthday;

	/**
	 * 证件类型
	 */
	private String certifiType ;
	/**
	 * 证件号码
	 */
	private String certifiNo;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 手机号码
	 */
	private String phoneNum;

	/**
	 * 性别 
	 */
	private String gender;
	/**
	 * 手机简称 (推送代码)
	 */
	private String alias;
	/**
	 * E-mail
	 */
	private String email;
	/**
	 * 头像
	 */
	private String imgFilePath;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 注册时间
	 */
	private String createTime;
	
	/**
	 * 用户状态
	 */
	private Integer status;


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCertifiType() {
		return certifiType;
	}

	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType;
	}

	public String getCertifiNo() {
		return certifiNo;
	}

	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgFilePath() {
		return imgFilePath;
	}

	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
