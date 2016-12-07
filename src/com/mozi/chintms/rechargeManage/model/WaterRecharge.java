package com.mozi.chintms.rechargeManage.model;

import java.io.Serializable;
public class WaterRecharge implements Serializable{
	
	private static final long serialVersionUID = 389699507002599783L;
	private String rechargeId;	//宽带充值ID
	private String accountNo;	//手机号
	private int amount;		//充值金额
	private String cmdWater;   //地址
	private String cmdPublicService;    // 公共事业单位
	private String cmdCity;   //地址
	private String cmdProvince;
	
	
	
	public String getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCmdWater() {
		return cmdWater;
	}
	public void setCmdWater(String cmdWater) {
		this.cmdWater = cmdWater;
	}
	public String getCmdPublicService() {
		return cmdPublicService;
	}
	public void setCmdPublicService(String cmdPublicService) {
		this.cmdPublicService = cmdPublicService;
	}
	public String getCmdCity() {
		return cmdCity;
	}
	public void setCmdCity(String cmdCity) {
		this.cmdCity = cmdCity;
	}
	public String getCmdProvince() {
		return cmdProvince;
	}
	public void setCmdProvince(String cmdProvince) {
		this.cmdProvince = cmdProvince;
	}
	


	
	
}
