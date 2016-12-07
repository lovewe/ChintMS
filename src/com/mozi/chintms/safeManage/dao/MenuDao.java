package com.mozi.chintms.safeManage.dao;

import java.util.List;
import java.util.Map;

import com.mozi.chintms.safeManage.model.Permission;

public interface MenuDao {

	/**
	 * 查询是否存在相同ID
	 * 
	 * @param info
	 * @return
	 */
	public Permission selectMenuById(Permission info);
	
	public List<Permission> selectMenuList();
	/**
	 * 新增菜单
	 * @param perm
	 * @return
	 */
	public int insertMenu(Permission perm);
	/**
	 * 菜单保存成功
	 * @param list
	 * @return
	 */
	public int insertMenuCompany(List<Permission> list);
	
	/**
	 * 删除菜单与公司关系
	 * @param permId
	 * @return
	 */
	public int deleteMenuCompany(String[] permId);
	
	/**
	 * 更新菜单 
	 * @param domain
	 * @return
	 */
	public int updateMenu(Permission domain);
	
	public List<Permission> getMenuListByUser(Map<String, String> map);
	
	public int deleteMenu(String[] permId);
	/**
	 * 根据角色id获取菜单
	 * @param roleId
	 * @return
	 */
	public List<Permission> getMenusByRoleId(String roleId);
}
