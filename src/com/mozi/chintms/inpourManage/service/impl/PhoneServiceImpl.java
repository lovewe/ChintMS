package com.mozi.chintms.inpourManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.inpourManage.dao.PhoneDao;
import com.mozi.chintms.inpourManage.model.PhoneInfo;
import com.mozi.chintms.inpourManage.service.PhoneService;
import com.mozi.chintms.inpourManage.service.impl.PhoneServiceImpl;


@Service("phoneService")
public class PhoneServiceImpl implements PhoneService  {
	
	@Autowired
	private PhoneDao phoneDao;


	@Override
	
	public String insertPhone(PhoneInfo phone) {
		
		//交易订单 ID
		String  strRechargeId =null;
		
		strRechargeId = RandomUtil.randomString();
		
		phone.setRechargeId(strRechargeId);
		
		int result = phoneDao.insertPhone(phone);
		if(result > 0){
				return strRechargeId;
			}else{
				
				return null;
			}
	}

	/*@Override
	public String updatePhone(PhoneInfo phoneInfo) {
		int result = phoneDao.updatePhone(phoneInfo);
		if(result > 0){
			return "true";
		}else{
			return "false";
		
		}*/
	}
	




