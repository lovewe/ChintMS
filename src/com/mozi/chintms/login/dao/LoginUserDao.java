package com.mozi.chintms.login.dao;

import java.util.List;
import java.util.Map;

import com.mozi.chintms.login.model.LoginUserInfo;
import com.mozi.chintms.safeManage.model.SafeUserInfo;

public interface LoginUserDao {

	/**
	 * 用户登录模块 
	 * @param info
	 * @return
	 */
	public LoginUserInfo userLogin(LoginUserInfo info);
	
	/**
	 * 用户新增
	 * @param info
	 */
//	public int  insertUser(SafeUserInfo info);
	
	/**
	 * 用户删除
	 * @param info
	 */
	public int deleteUser(String[] userId);
	
	/**
	 * 密码重置 验证通过重新输入密码
	 * @param info
	 */
	public int updatePwd(LoginUserInfo info);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public List<LoginUserInfo> selectUserList();
	
	public int updateUser(LoginUserInfo user);
	
	
}
