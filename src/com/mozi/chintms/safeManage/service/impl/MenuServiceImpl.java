package com.mozi.chintms.safeManage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.safeManage.dao.MenuDao;
import com.mozi.chintms.safeManage.model.CompanyBean;
import com.mozi.chintms.safeManage.model.Permission;
import com.mozi.chintms.safeManage.model.TreeBean;
import com.mozi.chintms.safeManage.model.TreeDto;
import com.mozi.chintms.safeManage.service.MenuService;

@Service("MenuService")
public class MenuServiceImpl extends BaseService implements MenuService{

	@Autowired
	private MenuDao menuDao;

	public String addMenu(Permission perm) {
		// 检查是否已经有相同的ID
		Permission per = menuDao.selectMenuById(perm);
		if (per != null) {
			return "duplicate";
		}
		int result = menuDao.insertMenu(perm);
		// 如果菜单增加成功
		if (result > 0) {

			return "false";
		} else {
			return "false";
		}
	}
	
	/**
	 * 返回菜单与公司的关系
	 * @param perm
	 * @return
	 */
	private List<Permission> getMenuCompany(Permission perm){
		List<Permission> list = new ArrayList<Permission>();
		if(perm.getCompany() != null && !"".equals(perm.getCompany())){
			String[] companys = perm.getCompany().split(",");
			for(int i=0;i<companys.length;i++){
				Permission bean = new Permission();
				bean.setPermId(perm.getPermId());
				bean.setCompanyId(Integer.parseInt(companys[i]));
				list.add(bean);
			}
		}
		return list;
	}

	public List<Permission> getMenuList() {
		return menuDao.selectMenuList();
	}

	public String updateMenu(Permission perm) {

			int result = menuDao.updateMenu(perm);
			List<Permission> list = getMenuCompany(perm);
			if(list.size() > 0){
				result = menuDao.insertMenuCompany(list);
			}
			if(result > 0){
				return "true";
			}else{
				return "false";
			}

	}
	
	public String deleteMenu(String[] permId) {
		int result = menuDao.deleteMenu(permId);
		if(result > 0){
			return "true";
		}else{
			return "false";
		}
		
	}


	public List<Permission> getMenulistByUser(String userName,String company) {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("userName", userName);
		map.put("company", company);
		
		return menuDao.getMenuListByUser(map);
	}
	
	public StringBuffer getMenuHtmlStr(List<Permission> permList){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<ul class=\"sidebar-menu\">");
		for(Permission perm : permList){
			if("0".equals(perm.getpPermId())){
				htmlStr.append("<li class=\"has-sub\"><a href=\"javascript:;\" class=\"\">");
				htmlStr.append("<span class=\"icon-box\"> <i class=\"").append(perm.getMenuClass()).append("\"></i></span>");
				htmlStr.append(perm.getPermName()).append("<span class=\"arrow\"></span></a>");
				htmlStr = this.getSunMenuHtmlStr(htmlStr, perm.getPermId(), permList);
				htmlStr.append("</li>");
			}else{
				
			}
		}
		htmlStr.append("</ul>");
		return htmlStr;
	}
	
	/**
	 * 获取子菜单
	 * @param htmlStr
	 * @param p_perm_id
	 * @param permList
	 * @return
	 */
	public StringBuffer getSunMenuHtmlStr(StringBuffer htmlStr,String p_perm_id,List<Permission> permList){
		boolean flag = false;
		for(Permission perm : permList){
			if(p_perm_id.equals(perm.getpPermId())){
				if(!flag){
					flag = true;
					htmlStr.append("<ul class=\"sub\">");
				}
				htmlStr.append("<li class=\"\"><a class=\"\" href=\"javascript:;\" onclick=\"doSubmit('");
				htmlStr.append(perm.getUrl()).append("')\">").append(perm.getPermName()).append("</a></li>");
			}else{
				
			}
		}
		if(flag){
			htmlStr.append("</ul>");
		}
		return htmlStr;
	}
	
	public List<Permission> getMenuListByParentId(String parentId){
		return menuDao.selectMenuList();
	}
	
	public List<TreeDto> getChildren(String id,int i) {
		List<TreeDto> list= new ArrayList<TreeDto>();
		List<Permission> menuList=menuDao.selectMenuList();
		for(Permission permission:menuList){
			TreeDto node = new TreeDto();
			if(permission.getUrl()==null){
				node.setId(i++);
				node.setName(permission.getPermName());
				node.setTitle(permission.getPermId());
				node.setChildren(getChildren(permission.getPermId(),i));
			}
			else{
				node.setId(i++);
				node.setName(permission.getPermName());
				node.setTitle(permission.getPermId());
				node.setChildren(getChildren(permission.getPermId(),i));
			}
			list.add(node);
		}
		return list;
	}

	public List<TreeDto> getMenu() {
		
		List<Permission> list = menuDao.selectMenuList();
		List<TreeDto> nodeList = new ArrayList<TreeDto>();
		int i=0;
		for(Permission permission:list){
			TreeDto node = new TreeDto();
			node.setId(i++);
			node.setName(permission.getPermName());
			node.setTitle(permission.getPermId());
			node.setChildren(getChildren(permission.getPermId(),i));
			nodeList.add(node);
		}
		return nodeList;
	}
	
	public List<TreeBean> getMenu2() {
		
		List<Permission> list = menuDao.selectMenuList();
		List<TreeBean> nodeList = new ArrayList<TreeBean>();
		for(Permission perm:list){
			TreeBean node = new TreeBean();
			node.setId(perm.getPermId());
			node.setName(perm.getPermName());
			node.setPid(perm.getpPermId());
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public Permission getMenuByPermId(Permission perm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> getMenusByRoleId(String roleId) {
		// TODO Auto-generated method stub
		List<Permission> list = null;
		list = menuDao.getMenusByRoleId(roleId);
		return list;
	}

	@Override
	public List<TreeBean> getCompanyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyBean> getCompanyByMenuId(String menuId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public List<Permission> getMenusByRoleId(String roleId){
//		return menuDao.getMenusByRoleId(roleId);
//	}
//	
//	public Permission getMenuByPermId(Permission perm){
//		return menuDao.selectMenuById(perm);
//	}
//	
//	public List<TreeBean> getCompanyList(){
//		List<CompanyBean> list = menuDao.getCompanyList();
//		List<TreeBean> treeList = new ArrayList<TreeBean>();
//		for(CompanyBean company:list){
//			TreeBean tree = new TreeBean();
//			tree.setId(String.valueOf(company.getCompanyId()));
//			tree.setName(company.getCompanyName());
//			tree.setPid("0");
//			treeList.add(tree);
//		}
//		return treeList;
//	}
//	
//	public List<CompanyBean> getCompanyByMenuId(String menuId){
//		return menuDao.getCompanyByMenuId(menuId);
//	}
}
