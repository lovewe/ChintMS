package com.mozi.chintms.safeManage.model;

/**
 * 前端树节点Bean
 * @author zhulm
 *
 */
public class TreeBean {
	
	/**
	 * 树节点ID
	 */
	private String id;
	/**
	 * 树节点名
	 */
	private String name;
	/**
	 * 父节点ID
	 */
	private String pid;
	
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

}
