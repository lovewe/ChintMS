package com.mozi.chintms.login.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.login.dao.LoginUserDao;
import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.login.service.UserLoginService;


@Service("UserLoginService")
public class UserLoginServiceImpl extends BaseService implements
		UserLoginService {

	/**
	 * 日志器
	 */
	private static final Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	private LoginUserDao userDao;

	/**
	 * 用户登录
	 * 
	 * @param UserLogin
	 * @return
	 */
	public LoginUserInfo userLogin(LoginUserInfo userDomain) {
		LoginUserInfo userInfo = userDao.userLogin(userDomain);
		return userInfo;
	}
	
}