package com.mozi.chintms.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.mozi.chintms.common.utils.Constant;
import com.mozi.chintms.login.model.LoginUserInfo;

/**
 * PermitTag.java是数字正泰系统的菜单、功能的访问权限控制标签类。
 * 
 * @version 1.0
 * @author wuhanhua
 * 
 */
public class PermitTag extends BodyTagSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 971701478913080447L;

	/**
	 * 菜单ID或功能ID
	 */
	private String functionId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		//TODO UserEntity currentUser = (UserEntity) ActionContext.getContext().getSession().get(Constant.CURRENT_SESSION_USER_KEY);
		LoginUserInfo currentUser = (LoginUserInfo)pageContext.getSession().getAttribute(Constant.CURRENT_SESSION_USER_KEY);
		if (currentUser == null) {
			return SKIP_BODY;
		} else {
			// 如果用户类型是管理员的话，拥有所有权限。
//			if (currentUser.getUserType() == Constant.USER_TYPE_ADMIN) {
//				return EVAL_BODY_INCLUDE;
//			}
//			if (currentUser.getFunctionIdSet() == null) {
//				return SKIP_BODY;
//			}
//			if (currentUser.getFunctionIdSet().contains(functionId)) {
//				return EVAL_BODY_INCLUDE;
//			}
		}
		return SKIP_BODY;
	}

	/**
	 * @return the functionId
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
