package com.mozi.chintms.basicDataManage.model;

import java.io.Serializable;

public class UserUniqueKeyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8341554432748121219L;

	private String name;
	private String billCode;
	private String add;
	private String trans;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
}
