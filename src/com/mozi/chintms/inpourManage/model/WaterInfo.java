package com.mozi.chintms.inpourManage.model;
import sunw.io.Serializable;

import com.mozi.chintms.common.BaseDomain;

public class WaterInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 389699507002599783L;
	private String rechargeId;	//水煤电ID
	private String accountNo;	//账户号
	private String cmdWater;	//水煤电缴费类型
	private String amount;		//充值金额
	private String cmdCity;   //城市
	private String cmdPublicService;    // 公共事业单位
	private String cmdProvince;  //省
	private String creatTime;   //订单创建时间
	private String changeType;   //
	private String state;      //状态
	private String remark;     //备注
	private String subAccountType;    //子帐户类型
	private String user_id;          //用户ID
	private String password;       //密码
	private String rechargeType;   //充值类型：如手机充值、宽带充值、水煤电充值
	
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
	public String getCmdWater() {
		return cmdWater;
	}
	public void setCmdWater(String cmdWater) {
		this.cmdWater = cmdWater;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getCmdProvince() {
		return cmdProvince;
	}
	public void setCmdProvince(String cmdProvince) {
		this.cmdProvince = cmdProvince;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
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
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}
	
	
	
}
