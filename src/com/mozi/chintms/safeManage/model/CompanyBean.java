package com.mozi.chintms.safeManage.model;


/**
 * 公司Bean
 * @author zhulm
 *
 */
public class CompanyBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4681136070890918019L;
	
	/**
	 * 公司ID
	 */
	private int companyId;
	/**
	 * 公司名
	 */
	private String companyName;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
