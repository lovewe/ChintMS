package com.mozi.chintms.rechargeManage.dao;
import java.util.List;

import com.mozi.chintms.rechargeManage.model.WaterRecharge;



		public interface WaterRechargeDao {

			public List<WaterRecharge> selectWaterRechargeList(WaterRecharge waterRecharge);
			
			public int insertWaterRecharge(WaterRecharge waterRecharge);
		
			public int updateWaterRecharge(WaterRecharge model);
			
			public int deleteWaterRecharge(String[] rechargeId);

	}


