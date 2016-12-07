package com.mozi.chintms.safeManage.service;

import java.util.List;

import com.mozi.chintms.safeManage.model.CompanyBean;
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.model.TreeBean;
import com.mozi.chintms.safeManage.model.TreeDto;


/**
 * 菜单权限单
 * @author zlm
 *
 */
public interface MenuService {

	/**
	 * 根据用户与公司，取得该用户拥有的菜单
	 * @param userName
	 * @return
	 */
	public List<Permission> getMenulistByUser(String userName,String company);
	/**
	 * 根据菜单列表，生成HTML菜单树
	 * @param permList
	 * @return
	 */
	public StringBuffer getMenuHtmlStr(List<Permission> permList);
	
	/**
	 * 添加菜单
	 * @param perm
	 * @return
	 */
	public String addMenu(Permission perm);
	/**
	 * 取得菜单列表
	 * @return
	 */
	public List<Permission> getMenuList();
	/**
	 * 更新菜单
	 * @param perm
	 * @return
	 */
	public String updateMenu(Permission perm);

	
	/**
	 * 根据菜单ID获取菜单对象
	 * @param perm
	 * @return
	 */
	public Permission getMenuByPermId(Permission perm);
	
	/**
	 * 根据父类ID取得菜单列表
	 * @return
	 */
	public List<Permission> getMenuListByParentId(String parentId);
	
	public List<TreeDto> getMenu();
	
	public List<TreeBean> getMenu2();
	
	public List<Permission> getMenusByRoleId(String roleId);
	/**
	 * 获取公司列表
	 * @return
	 */
	public List<TreeBean> getCompanyList();
	/**
	 * 根据菜单ID获取公司列表
	 * @return
	 */
	public List<CompanyBean> getCompanyByMenuId(String menuId);
	
	/**
	 * 删除菜单
	 * @param permId
	 * @return
	 */
	public String deleteMenu(String[] permId);

}
