package com.mozi.chintms.safeManage.service;

import java.util.List;

import com.mozi.chintms.login.model.RoleInfo;
import com.mozi.chintms.safeManage.model.Permission;


/**
 * 角色
 * @author wcm
 *
 */
public interface RoleService {
	
	public List<RoleInfo> getRoles();
	
	public List<RoleInfo> getRolesByUserId(String UserId);
	
	public int insertRole(RoleInfo role,String menuIds);
	
	public int updateRole(RoleInfo role , String menuIds);
	
	public int deleteRole(String roleIds);
	
	public List<RoleInfo> getRolesByUserName(String userName);

}
