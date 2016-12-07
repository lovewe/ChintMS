package com.mozi.chintms.rechargeManage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.rechargeManage.dao.WideRechargeDao;
import com.mozi.chintms.rechargeManage.model.WideRecharge;
import com.mozi.chintms.rechargeManage.service.WideRechargeService;
import com.mozi.chintms.rechargeManage.service.impl.WideRechargeServiceImpl;



@Service("wideRechargeService")
public class WideRechargeServiceImpl implements WideRechargeService  {
	
	@Autowired
	private WideRechargeDao wideRechargeDao;

	@Override
	public String insertWideRecharge(WideRecharge wideRecharge) {
		
		wideRecharge.setRechargeId(RandomUtil.randomString());
		int result = wideRechargeDao.insertWideRecharge(wideRecharge);
		if(result > 0){
				return "true";
			}else{
				//如果添加失败，则删除报表信息
				//phoneRechargeDao.deletePhoneRecharge(PhoneRecharge.getPhoneRechargeId());
				return "false";
			}
	}

	@Override
	public List<WideRecharge> selectWideRechargeList() {
		return wideRechargeDao.selectWideRechargeList(null);
	}

	@Override
	public String updateWideRecharge(WideRecharge wideRecharge) {
		int result = wideRechargeDao.updateWideRecharge(wideRecharge);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}

	@Override
	public String deleteWideRecharge(String[] rechargeId) {
		int result = wideRechargeDao.deleteWideRecharge(rechargeId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	
	}

}

