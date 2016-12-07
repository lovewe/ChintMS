package com.mozi.chintms.safeManage.model;

import com.mozi.chintms.common.BaseDomain;

/**
 * 域信息
 * @author zlm
 *
 */
public class LdapBean  extends BaseDomain{
	
	private int ldapId;
	private String ldapIp;
	private String ldapPort;
	private String ldapDomain;
	private String ldapRoot;
	public int getLdapId() {
		return ldapId;
	}
	public void setLdapId(int ldapId) {
		this.ldapId = ldapId;
	}
	public String getLdapIp() {
		return ldapIp;
	}
	public void setLdapIp(String ldapIp) {
		this.ldapIp = ldapIp;
	}
	public String getLdapPort() {
		return ldapPort;
	}
	public void setLdapPort(String ldapPort) {
		this.ldapPort = ldapPort;
	}
	public String getLdapDomain() {
		return ldapDomain;
	}
	public void setLdapDomain(String ldapDomain) {
		this.ldapDomain = ldapDomain;
	}
	public String getLdapRoot() {
		return ldapRoot;
	}
	public void setLdapRoot(String ldapRoot) {
		this.ldapRoot = ldapRoot;
	}
	
	

}
