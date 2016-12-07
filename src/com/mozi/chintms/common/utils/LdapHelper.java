package com.mozi.chintms.common.utils;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Ldap认证
 * @author zlm
 *
 */
public class LdapHelper {
	
	/**
	 * LDAP认证
	 * @param ldapUrl	ldapURL
	 * @param ldapPort	ldap端口
	 * @param account	ldap用户
	 * @param password	ldap密码
	 * @param root		ldap层级
	 * @return
	 * @throws AuthenticationException
	 * @throws Exception
	 */
	public static boolean getCtx(String ldapUrl,String ldapPort,String account,String password,String root) throws AuthenticationException,Exception{
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://"+ldapUrl+":"+ldapPort+"/" + root);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");	//用户密码认证模式
		env.put(Context.SECURITY_PRINCIPAL, account);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			// 链接ldap
			DirContext ctx = new InitialDirContext(env);
			return true;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new AuthenticationException("认证失败：" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("认证出错：" + e.getMessage());
		}
	}

	public static void main(String[] s) {
		try{
			LdapHelper.getCtx("Astronergy.com","389","mozi@Astronergy.com","mozilogic","dc=Astronergy,dc=local");
		}catch(AuthenticationException e){
			
		}catch(Exception e){
			
		}
	}
}
