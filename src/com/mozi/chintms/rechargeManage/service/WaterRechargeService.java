
package com.mozi.chintms.rechargeManage.service;

import java.util.List;

import com.mozi.chintms.rechargeManage.model.WaterRecharge;



public interface WaterRechargeService {
	
	public String insertWaterRecharge(WaterRecharge waterRecharge);
	
	public List<WaterRecharge> selectWaterRechargeList();
	
	public String updateWaterRecharge(WaterRecharge waterRecharge);

	public String deleteWaterRecharge(String[] rechargeId);
}


