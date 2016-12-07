package com.mozi.chintms.rechargeManage.dao;

import java.util.List;

import com.mozi.chintms.rechargeManage.model.PhoneRecharge;
import com.mozi.chintms.safeManage.model.Permission;

	public interface PhoneRechargeDao {

		public List<PhoneRecharge> selectPhoneRechargeList(PhoneRecharge phoneRecharge);
		
		public int insertPhoneRecharge(PhoneRecharge phoneRecharge);
	
		public int updatePhoneRecharge(PhoneRecharge model);
		
		public int deletePhoneRecharge(String[] rechargeId);

}
