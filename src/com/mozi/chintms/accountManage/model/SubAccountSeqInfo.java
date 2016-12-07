package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

/**
 * 子账户资金变动流水表
 * @author wx
 *
 */
public class SubAccountSeqInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3586424902118048846L;

	/**
	 * 流水号
	 */
	private String flowID;

	/**
	 * 子账户ID
	 */
	private String subAccountID;
	/**
	 * 用户ID
	 */
	private String userID;
	/**
	 * 账户类型
	 */
	private String subAccountType;
	
	
	/**
	 * 交易前总额
	 */
	private String preAmount;
	/**
	 * 发生额
	 */
	private String tradeAmount;

	/**
	 * 余额
	 */
	private String amount;
	/**
	 * 账务方向
	 */
	private String seqFlag;
	/**
	 * 类型
	 * 01充值
	 * 02支付
	 * 03提现
	 * 04原交易退款
	 * 05原交易撤销
	 */
	private String changeType;
	/**
	 * 
	 */
	private String orderID;
	/**
	 * 备注
	 */
	private String tradeRemark;

	/**
	 * 交易创建时间
	 */
	private String createTime;
	/**
	 * 时间分组
	 */
	private String days;
	/**
	 * 交易日期
	 */
	private String tradeDate;
	/**
	 * 交易时间
	 */
	private String tradeTime;
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getFlowID() {
		return flowID;
	}
	public void setFlowID(String flowID) {
		this.flowID = flowID;
	}
	public String getSubAccountID() {
		return subAccountID;
	}
	public void setSubAccountID(String subAccountID) {
		this.subAccountID = subAccountID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getPreAmount() {
		return preAmount;
	}
	public void setPreAmount(String preAmount) {
		this.preAmount = preAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSeqFlag() {
		return seqFlag;
	}
	public void setSeqFlag(String seqFlag) {
		this.seqFlag = seqFlag;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getTradeRemark() {
		return tradeRemark;
	}
	public void setTradeRemark(String tradeRemark) {
		this.tradeRemark = tradeRemark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
}
