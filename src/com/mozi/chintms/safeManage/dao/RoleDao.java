package com.mozi.chintms.safeManage.dao;

import java.util.List;
import java.util.Map;

import com.mozi.chintms.login.model.RoleInfo;

public interface RoleDao {
	
	
	public List<Map<?,?>> selectByPrimaryKey();
	
	public List<RoleInfo> selectAll(RoleInfo info);
	
	public List<RoleInfo> getRolesByUserId(String userId);
	
	public List<RoleInfo> getRolesByUserName(String userName);
	
	public int deleteByPrimaryKey(String roleIds);
	
	public int insert();
	
	public int insertSelective(RoleInfo info);
	
	public int updateByPrimaryKeySelective(RoleInfo role);
	
	public int updateByPrimaryKey();
	
}
