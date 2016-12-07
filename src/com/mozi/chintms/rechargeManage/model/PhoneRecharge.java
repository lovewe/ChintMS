package com.mozi.chintms.rechargeManage.model;

import com.mozi.chintms.common.BaseDomain;

public class PhoneRecharge extends BaseDomain{
	
	
	private String rechargeId;	//充值ID
	
	private String phoneNo;	//手机号
	private int amount;		//充值金额
	private float price;	//售价
	public String getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	}

	
	

	

