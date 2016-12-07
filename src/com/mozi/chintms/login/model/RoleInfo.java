package com.mozi.chintms.login.model;

import com.mozi.chintms.common.BaseDomain;

public class RoleInfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5181069023016863317L;
	private String roleId;
	private String roleName;
	private String tableauIp;
	private String tableauPort;
	private String tableauUser;
	private String description;
	private Integer status;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTableauIp() {
		return tableauIp;
	}
	public void setTableauIp(String tableauIp) {
		this.tableauIp = tableauIp;
	}
	public String getTableauPort() {
		return tableauPort;
	}
	public void setTableauPort(String tableauPort) {
		this.tableauPort = tableauPort;
	}
	public String getTableauUser() {
		return tableauUser;
	}
	public void setTableauUser(String tableauUser) {
		this.tableauUser = tableauUser;
	}

}
