package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

public class SubAccountSeqForListInfo implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6073608521872341779L;

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
	private String personID;

	/**
	 * 发生额
	 */
	private String tradeAmount;
	/**
	 * 
	 */
	private String orderID;
	/**
	 * 备注
	 */
	private String tradeRemark;
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
	
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
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

	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
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
