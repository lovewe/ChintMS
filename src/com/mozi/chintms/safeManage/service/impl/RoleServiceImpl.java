package com.mozi.chintms.safeManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mozi.chintms.common.BaseService;
import com.mozi.chintms.common.utils.RandomUtil;
import com.mozi.chintms.login.model.RoleInfo;
import com.mozi.chintms.safeManage.dao.RoleDao;
import com.mozi.chintms.safeManage.dao.RolePermMapDao;
import com.mozi.chintms.safeManage.model.RolePermMap;
import com.mozi.chintms.safeManage.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl extends BaseService implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePermMapDao rolePermMapDao;
	
	public List<RoleInfo> getRoles() {
		return roleDao.selectAll(new RoleInfo());
	}

	public int insertRole(RoleInfo role,String menuIds) {
		
		role.setRoleId(RandomUtil.randomString());
		
		roleDao.insertSelective(role);
		
		rolePermMapDao.insert(getRolePerm(role.getRoleId(),menuIds));
		
		return 1;
	}

	public int updateRole(RoleInfo role , String menuIds) {
		roleDao.updateByPrimaryKeySelective(role);
		if(menuIds != ""){
			rolePermMapDao.deleteByPrimaryKey(role.getRoleId()); //先删除再添加
			rolePermMapDao.insert(getRolePerm(role.getRoleId(),menuIds));
		}
		return 1;
	}

	public int deleteRole(String roleIds) {
		rolePermMapDao.deleteByPrimaryKey(roleIds);//删除角色权限对应表
		roleDao.deleteByPrimaryKey(roleIds); //删除角色表
		return 1;
	}
	
	public List<RoleInfo> getRolesByUserId(String userId){
		return roleDao.getRolesByUserId(userId);
	}
	
	public List<RoleInfo> getRolesByUserName(String userName){
		return roleDao.getRolesByUserName(userName);
	}
	
	public List<RolePermMap>  getRolePerm(String roleId,String menuIds){
		List<RolePermMap> list = new ArrayList<RolePermMap>();
		String[] ids =menuIds.split(",");
		for(String menuId : ids){
			RolePermMap domain = new RolePermMap();
			domain.setRoleId(roleId);
			domain.setPermId(menuId);
			list.add(domain);
		}
		return list;
	}
	
}
