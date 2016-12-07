package com.mozi.chintms.rechargeManage.service.impl;
import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.rechargeManage.dao.PhoneRechargeDao;
import com.mozi.chintms.rechargeManage.model.PhoneRecharge;
import com.mozi.chintms.rechargeManage.service.PhoneRechargeService;
import com.mozi.chintms.rechargeManage.service.impl.PhoneRechargeServiceImpl;



@Service("phoneRechargeService")
public class PhoneRechargeServiceImpl implements PhoneRechargeService  {
	
	@Autowired
	private PhoneRechargeDao phoneRechargeDao;

	@Override
	public String insertPhoneRecharge(PhoneRecharge phoneRecharge) {
		
		phoneRecharge.setRechargeId(RandomUtil.randomString());
		int result = phoneRechargeDao.insertPhoneRecharge(phoneRecharge);
		if(result > 0){
				return "true";
			}else{
				//如果添加失败，则删除报表信息
				//phoneRechargeDao.deletePhoneRecharge(PhoneRecharge.getPhoneRechargeId());
				return "false";
			}
	}

	@Override
	public List<PhoneRecharge> selectPhoneRechargeList() {
		return phoneRechargeDao.selectPhoneRechargeList(null);
	}

	@Override
	public String updatePhoneRecharge(PhoneRecharge phoneRecharge) {
		int result = phoneRechargeDao.updatePhoneRecharge(phoneRecharge);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}

	@Override
	public String deletePhoneRecharge(String[] rechargeId) {
		int result = phoneRechargeDao.deletePhoneRecharge(rechargeId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	
	}

}
