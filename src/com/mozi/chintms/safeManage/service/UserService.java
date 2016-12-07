package com.mozi.chintms.safeManage.service;

import java.util.List;

import com.mozi.chintms.safeManage.model.SafeUserInfo;


/**
 * 用户管理接口类
 * @author zlm
 *
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public String addUser(SafeUserInfo user);
	/**
	 * 取得用户列表
	 * @return
	 */
	public List<SafeUserInfo> getUserList();
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public String updateUser(SafeUserInfo user);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public String deleteUser(String[] userId);
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public String updatePwd(SafeUserInfo user);

	/**
	 * 根据用户ID查询用户信息
	 * @param info
	 * @return
	 */
	public SafeUserInfo selectUserByID(SafeUserInfo info);
	
	/**
	 * 根据用户ID查询用户信息
	 * 手机端使用
	 * @param info
	 * @return
	 */
	public SafeUserInfo selectPhoneUserByID(SafeUserInfo info);
}
