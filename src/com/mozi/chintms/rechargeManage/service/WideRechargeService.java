package com.mozi.chintms.rechargeManage.service;

	import java.util.List;

	import com.mozi.chintms.rechargeManage.model.WideRecharge;

	public interface WideRechargeService {
		
		public String insertWideRecharge(WideRecharge wideRecharge);
		
		public List<WideRecharge> selectWideRechargeList();
		
		public String updateWideRecharge(WideRecharge wideRecharge);

		public String deleteWideRecharge(String[] rechargeId);
	}


