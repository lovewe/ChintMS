package com.mozi.chintms.inpourManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.inpourManage.dao.WaterDao;
import com.mozi.chintms.inpourManage.model.WaterInfo;
import com.mozi.chintms.inpourManage.service.WaterService;




@Service("waterService")
public class WaterServiceImpl implements WaterService  {
	
	@Autowired
	private WaterDao waterDao;

	@Override
	public String insertWater(WaterInfo water) {
		//交易订单 ID
				String  strRechargeId =null;
				
				strRechargeId = RandomUtil.randomString();
				
				water.setRechargeId(strRechargeId);
		
		int result = waterDao.insertWater(water);
		if(result > 0){
				return strRechargeId;
			}else{
				//如果添加失败，则删除报表信息
				//phoneRechargeDao.deletePhoneRecharge(PhoneRecharge.getPhoneRechargeId());
				return "false";
			}
	}
	/*@Override
	public List<WaterInfo> selectWaterList() {
		return waterDao.selectWaterList(null);
	}

	@Override
	public String updateWater(WaterInfo waterInfo) {
		int result = waterDao.updateWater(waterInfo);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}

	@Override
	public String deleteWater(String[] rechargeId) {
		int result = waterDao.deleteWater(rechargeId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	
	}*/
	
}


