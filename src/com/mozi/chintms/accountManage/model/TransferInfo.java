package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

public class TransferInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3297028744602508733L;

	/**
	 * 付款人密码
	 */
	private String password; 
	/**
	 * 付款人ID
	 */
	private String payerUserID;
	/**
	 * 收款人ID
	 */
	private String payeeUserID;

	/**\
	 * 发生额
	 */
	private String amount ;
	/**
	 * 发生时间
	 */
	private String createTime;
	/**
	 * 订单号
	 */
	private String orderID;
	
	/**
	 * 备注
	 */
	private String remark;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPayerUserID() {
		return payerUserID;
	}
	public void setPayerUserID(String payerUserID) {
		this.payerUserID = payerUserID;
	}
	public String getPayeeUserID() {
		return payeeUserID;
	}
	public void setPayeeUserID(String payeeUserID) {
		this.payeeUserID = payeeUserID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
