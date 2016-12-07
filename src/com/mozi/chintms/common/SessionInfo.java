package com.mozi.chintms.common;

import java.util.List;

import com.mozi.chintms.login.model.Module;
import com.mozi.chintms.login.model.LoginUserInfo;

/**
 * 登录成功后，就需要设置到session里面，便于系统使用
 * 
 * @author wuhanhua
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -1112969954531570554L;
	private LoginUserInfo userDomain;
	private List<Module> moduleList;
	private int accountId;
	private int uid;
	private String accountName;
	private String accountType;
	private Boolean boo;

	public Boolean getBoo() {
		return boo;
	}

	public void setBoo(Boolean boo) {
		this.boo = boo;
	}

	public LoginUserInfo getLoginUser() {
		return userDomain;
	}

	public void setLoginUser(LoginUserInfo userDomain) {
		this.userDomain = userDomain;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
