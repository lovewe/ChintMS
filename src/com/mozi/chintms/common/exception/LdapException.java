package com.mozi.chintms.common.exception;

/**
 * Ldap错误转换类
 * @author zhulm
 *
 */
public class LdapException {
	
	/**
	 * 根据错误信息，返回中文错误提示
	 * @param errorMsg
	 * @return
	 */
	public static String getLdapErrorMsg(String errorMsg){
		String msg=null;
		if(errorMsg!= null && errorMsg.indexOf("LDAP: error code 49")>0){
			msg="用户名或密码错误。";
		}else if(errorMsg!= null && errorMsg.indexOf("LDAP: error code 50")>0){
			msg="密码出错,您可能输入旧密码,请输入最新的密码。";
		}else if(errorMsg!= null && errorMsg.indexOf("LDAP: error code 19")>0){
			msg="您的密码不满足域安全策略：如密码复杂性、密码最短使用期限、强制密码历史,请联系管理员。";
		}else{
			msg="连接ldap时异常,请联系系统管理员。";
		}
		return msg;
	}

}
