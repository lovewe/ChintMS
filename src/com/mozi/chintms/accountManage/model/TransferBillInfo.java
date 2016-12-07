package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

public class TransferBillInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162807045505355085L;
	
	/**
	 * 流水
	 */
	private String SN ;
	/**
	 * 交易创建人/发起人
	 */
	private String srcUserID ;
	/**
	 * 子账户类型
	 */
	private String subAccountType ;
	/**
	 * 付款方用户ID
	 */
	private String payerUserID ;
	/**
	 * 付款方用户名称
	 */
	private String payerUserName ;
	/**
	 * 付款子账户ID
	 */
	private String payerSubAccountID ;
	/**
	 * 订单
	 */
	private String orderID ;
	/**
	 * 订单备注
	 */
	private String orderNote ;
	/**
	 * 收款人ID
	 */
	private String payeeUserId ;
	/**
	 * 收款人用户名=
	 */
	private String payeeUserName ;
	/**
	 * 交易创建时间
	 */
	private String createTime ;
	/**
	 * 返回代码
	 */
	private String resultCode ;
	/**
	 * 返回信息
	 */
	private String resultNote ;
	/**
	 * 状态
	 */
	private String state ;
	/**
	 * 发生金额
	 */
	private String tranAmount;
	
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	public String getSrcUserID() {
		return srcUserID;
	}
	public void setSrcUserID(String srcUserID) {
		this.srcUserID = srcUserID;
	}
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getPayerUserID() {
		return payerUserID;
	}
	public void setPayerUserID(String payerUserID) {
		this.payerUserID = payerUserID;
	}
	public String getPayerUserName() {
		return payerUserName;
	}
	public void setPayerUserName(String payerUserName) {
		this.payerUserName = payerUserName;
	}
	public String getPayerSubAccountID() {
		return payerSubAccountID;
	}
	public void setPayerSubAccountID(String payerSubAccountID) {
		this.payerSubAccountID = payerSubAccountID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public String getPayeeUserId() {
		return payeeUserId;
	}
	public void setPayeeUserId(String payeeUserId) {
		this.payeeUserId = payeeUserId;
	}
	public String getPayeeUserName() {
		return payeeUserName;
	}
	public void setPayeeUserName(String payeeUserName) {
		this.payeeUserName = payeeUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultNote() {
		return resultNote;
	}
	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTranAmount() {
		return tranAmount;
	}
	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
	}
	
}
