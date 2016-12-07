package com.mozi.chintms.smsManage.service;

import java.util.List;

import com.mozi.chintms.smsManage.model.PhoneInfo;


/**
 * 菜单权限单
 * @author wx
 *
 */
public interface SmsService {

	/**
	 * 查询手机号是否存在
	 * @param userName
	 * @return
	 */
	public List<PhoneInfo> SelectUserByPhone(PhoneInfo info);

	public void SetPhoneCache(PhoneInfo info);
	
	public String GetPhoneCache(PhoneInfo info);
}
