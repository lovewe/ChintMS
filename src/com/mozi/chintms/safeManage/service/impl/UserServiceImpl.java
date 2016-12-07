package com.mozi.chintms.safeManage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.MD5Util;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.safeManage.dao.SafeUserDao;
import com.mozi.chintms.safeManage.model.SafeUserInfo;
import com.mozi.chintms.safeManage.service.UserService;
import com.mozi.chintms.smsManage.controller.SmsController;

@Service("UserService")
public class UserServiceImpl extends BaseService implements UserService{

	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SafeUserDao userDao;
	
	public String addUser(SafeUserInfo user) {
		//设置用户ID
		user.setPersonID(RandomUtil.randomString());
		//设置密码
		if(user.getPassword()==null||user.getPassword().length()<=0)
		{
			user.setPassword("123456");
		}
		
		user.setPassword(MD5Util.md5Encode(user.getPassword()));
		//设置用户状态
		user.setStatus(1);
		int result =0;
		try {
			result = userDao.insertUser(user);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	}

	public String deleteUser(String[] userId) {
		int result = userDao.deleteUser(userId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	}
	

	public List<SafeUserInfo> getUserList() {
		return userDao.selectUserList(null);
	}

	public String updateUser(SafeUserInfo user) {
		int result = userDao.updateUser(user);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	}
	
	public String updatePwd(SafeUserInfo user) {
		//对密码进行MD5码加密
		user.setOldPwd(MD5Util.md5Encode(user.getOldPwd()));
		user.setPassword(MD5Util.md5Encode(user.getPassword()));
		int result = userDao.updatePwd(user);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
	}

	@Override
	public SafeUserInfo selectUserByID(SafeUserInfo info) {
		// TODO Auto-generated method stub
		return userDao.selectUserByID(info);
	}

	@Override
	public SafeUserInfo selectPhoneUserByID(SafeUserInfo info) {
		// TODO Auto-generated method stub
		return userDao.selectPhoneUserByID(info);
	}

	
}
