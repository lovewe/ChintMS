package com.mozi.chintms.rechargeManage.model;
import sunw.io.Serializable;

import com.mozi.chintms.common.BaseDomain;
public class WideRecharge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 389699507002599783L;
	private String rechargeId;	//宽带充值ID
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String wideNo;	//手机号
	private int amount;		//充值金额
	private String cmdCity;   //地址
	private String cmdPublicService;    // 公共事业单位
	private String cmdProvince;
	public String getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}           
	
	public String getWideNo() {
		return wideNo;
	}
	public void setWideNo(String wideNo) {
		this.wideNo = wideNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	

	public String getCmdProvince() {
		return cmdProvince;
	}
	public void setCmdProvince(String cmdProvince) {
		this.cmdProvince = cmdProvince;
	}
	public String getCmdCity() {
		return cmdCity;
	}
	public void setCmdCity(String cmdCity) {
		this.cmdCity = cmdCity;
	}
	public String getCmdPublicService() {
		return cmdPublicService;
	}
	public void setCmdPublicService(String cmdPublicService) {
		this.cmdPublicService = cmdPublicService;
	}


	
	
}
