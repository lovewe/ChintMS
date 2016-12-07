package com.mozi.chintms.accountManage.model;

import java.io.Serializable;

public class QRCodeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3712939582034921088L;

	/**
	 * 用户id
	 */
	private String personID;
	/**
	 * 校验码ID
	 */
	private String qrCodeID;
	/**
	 * 校验码
	 */
	private String qrCode;
	/**
	 * 来往方向
	 */
	private String payoutORIncome;
	/**
	 * 是否长期有效
	 */
	private String ignoreValidate;
	/**
	 * 生成时间
	 */
	private String generateTime;
	/**
	 * 安全码使用状态
	 */
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQrCodeID() {
		return qrCodeID;
	}
	public void setQrCodeID(String qrCodeID) {
		this.qrCodeID = qrCodeID;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public String getIgnoreValidate() {
		return ignoreValidate;
	}
	public void setIgnoreValidate(String ignoreValidate) {
		this.ignoreValidate = ignoreValidate;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getPayoutORIncome() {
		return payoutORIncome;
	}
	public void setPayoutORIncome(String payoutORincome) {
		this.payoutORIncome = payoutORincome;
	}

	
}
