package com.mozi.chintms.rechargeManage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.rechargeManage.dao.WaterRechargeDao;
import com.mozi.chintms.rechargeManage.model.WaterRecharge;
import com.mozi.chintms.rechargeManage.service.WaterRechargeService;




@Service("waterRechargeService")
public class WaterRechargeServiceImpl implements WaterRechargeService  {
	
	@Autowired
	private WaterRechargeDao waterRechargeDao;

	@Override
	public String insertWaterRecharge(WaterRecharge waterRecharge) {
		
		waterRecharge.setRechargeId(RandomUtil.randomString());
		int result = waterRechargeDao.insertWaterRecharge(waterRecharge);
		if(result > 0){
				return "true";
			}else{
				//如果添加失败，则删除报表信息
				//phoneRechargeDao.deletePhoneRecharge(PhoneRecharge.getPhoneRechargeId());
				return "false";
			}
	}

	@Override
	public List<WaterRecharge> selectWaterRechargeList() {
		return waterRechargeDao.selectWaterRechargeList(null);
	}

	@Override
	public String updateWaterRecharge(WaterRecharge waterRecharge) {
		int result = waterRechargeDao.updateWaterRecharge(waterRecharge);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}

	@Override
	public String deleteWaterRecharge(String[] rechargeId) {
		int result = waterRechargeDao.deleteWaterRecharge(rechargeId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	
	}

}

