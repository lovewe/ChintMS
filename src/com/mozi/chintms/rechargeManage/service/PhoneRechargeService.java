package com.mozi.chintms.rechargeManage.service;

import java.util.List;

import com.mozi.chintms.rechargeManage.model.PhoneRecharge;

public interface PhoneRechargeService {
	
	public String insertPhoneRecharge(PhoneRecharge phoneRecharge);
	
	public List<PhoneRecharge> selectPhoneRechargeList();
	
	public String updatePhoneRecharge(PhoneRecharge phoneRecharge);

	public String deletePhoneRecharge(String[] rechargId);
}
