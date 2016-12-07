package com.mozi.chintms.safeManage.dao;

import java.util.List;

import com.mozi.chintms.safeManage.model.SafeUserInfo;

public interface SafeUserDao {
	/**
	 * 用户新增
	 * 
	 * @param info
	 */
	public int insertUser(SafeUserInfo info);
	/**
	 * 用户删除
	 * 
	 * @param info
	 */
	public int deleteUser(String[] userId);
	/**
	 * 查询用户列表
	 * @param info
	 * @return
	 */
	public List<SafeUserInfo> selectUserList(SafeUserInfo info);
	/**
	 * 修改密码
	 * @param info
	 * @return
	 */
	public int updatePwd(SafeUserInfo info);
	/**
	 * 更新用户信息
	 * @param info
	 * @return
	 */
	public int updateUser(SafeUserInfo info);
	
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
