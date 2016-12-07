package com.mozi.chintms.transactionManage.model;

import java.io.Serializable;


public class TransactionInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1823140495176553001L;

	/**
	 * 用户名
	 */
	private String personID;
	
	/**
	 * 密码
	 */
	private String password;

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
	 * 交易类型
	 */
	private String changeType;
	/**
	 * 订单ID
	 */
	private String OrderID;
	
	/**
	 * 备注 交易信息
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 最后修改时间
	 */
	private String lastUpdateTime;
	
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
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
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
