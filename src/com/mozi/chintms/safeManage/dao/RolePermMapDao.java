package com.mozi.chintms.safeManage.dao;

import java.util.List;

import com.mozi.chintms.safeManage.model.RolePermMap;

public interface RolePermMapDao {

	
	public int deleteByPrimaryKey(String roleIds);
	public int insert(List<RolePermMap> list);
	public int deleteByUserIds(String roleId);
	
}
