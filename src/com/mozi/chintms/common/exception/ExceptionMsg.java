package com.mozi.chintms.common.exception;

import com.mozi.chintms.common.utils.BeanUtil;

/**
 * 异常信息
 * 
 * @author wuhanhua
 * 
 * */
public class ExceptionMsg {

	private int errCode;

	private String errMsg;

	public ExceptionMsg(BusiException ex) {
		this.errCode = (Integer) BeanUtil.getProperty(ex, "id");
		this.errMsg = ex.getMessage();
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
