package com.mozi.chintms.inpourManage.model;
import sunw.io.Serializable;

import com.mozi.chintms.common.BaseDomain;
public class WideInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 389699507002599783L;
	private String rechargeId;	//宽带充值ID
	private String wideNo;	//手机号

	private String amount;		//充值金额
	private String cmdCity;   //地址
	private String cmdPublicService;    // 公共事业单位
	private String cmdProvince;
	private String creatTime;
	private String changeType;
	private String state;
	private String remark;
	private String subAccountType;
	private String user_id;
	private String password;
	private String rechargeType;
	
	public  String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
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
