package com.mozi.chintms.inpourManage.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.inpourManage.dao.WideDao;
import com.mozi.chintms.inpourManage.model.WideInfo;
import com.mozi.chintms.inpourManage.service.WideService;
import com.mozi.chintms.inpourManage.service.impl.WideServiceImpl;
import com.mozi.chintms.rechargeManage.model.WideRecharge;



@Service("wideService")
public class WideServiceImpl implements WideService  {
	
	@Autowired
	private WideDao wideDao;

	@Override
	public String insertWide(WideInfo wide) {
		//交易订单 ID
				String  strRechargeId =null;
				
				strRechargeId = RandomUtil.randomString();
				
				wide.setRechargeId(strRechargeId);
		
		int result = wideDao.insertWide(wide);
		if(result > 0){
				return strRechargeId;
			}else{
				//如果添加失败，则删除报表信息
				//phoneRechargeDao.deletePhoneRecharge(PhoneRecharge.getPhoneRechargeId());
				return "false";
			}
	}
	/*@Override
	public List<WideInfo> selectWideList() {
		return wideDao.selectWideList(null);
	}

	@Override
	public String updateWide(WideInfo wideInfo) {
		int result = wideDao.updateWide(wideInfo);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}

	@Override
	public String deleteWide(String[] rechargeId) {
		int result = wideDao.deleteWide(rechargeId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	
	}*/
}


