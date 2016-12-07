package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

/**
 * 用户账户表
 * @author wx
 *
 */
public class SubAccountInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3376827935582426995L;
	/**
	 * id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String personID;

	/**
	 * 账户类型
	 */
	private String subAccountType;
	/**
	 * 总金额
	 */
	private String amount;
	/**
	 * 账户性质
	 */
	private String property;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 最后修改时间
	 */
	private String lastUpdateTime;
	/**
	 * 电话号码
	 */
	private String phoneNum;
	

	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
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
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
