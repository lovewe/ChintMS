package com.mozi.chintms.rechargeManage.dao;
import java.util.List;

import com.mozi.chintms.rechargeManage.model.WideRecharge;

		public interface WideRechargeDao {

			public List<WideRecharge> selectWideRechargeList(WideRecharge wideRecharge);
			
			public int insertWideRecharge(WideRecharge wideRecharge);
		
			public int updateWideRecharge(WideRecharge model);
			
			public int deleteWideRecharge(String[] rechargeId);

	}


