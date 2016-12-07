package com.mozi.chintms.login.model;

import com.mozi.chintms.common.BaseDomain;

public class Module extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6105381474758085215L;
	private Long moduleID;
	private String moduleName;
	private Long parentID;
	private String linkURL;
	private Integer moduleLevel;
	private Integer moduleVisible;
	private String styple;
	private String id;
	private String name;
	private String pid;
	private String type;
	private String roleType;
	private String uid;
	private String userTypeId;

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}

	public Integer getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(Integer moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public Integer getModuleVisible() {
		return moduleVisible;
	}

	public void setModuleVisible(Integer moduleVisible) {
		this.moduleVisible = moduleVisible;
	}

	public String getStyple() {
		return styple;
	}

	public void setStyple(String styple) {
		this.styple = styple;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
}
