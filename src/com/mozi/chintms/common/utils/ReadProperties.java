package com.mozi.chintms.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取init配置文件
 * @author zlm
 *
 */
public class ReadProperties {
	
	/**
	 * ldap服务器IP
	 */
	private static String ldapIp;
	/**
	 * ldap服务器端口
	 */
	private static String ldapPort;
	/**
	 * ldap 用户域，如@mozilogic.com
	 */
	private static String ldapUserDomain;
	/**
	 * ldap	目录，如dc=mozilogic,dc=local
	 */
	private static String ldapRoot;
	/**
	 * ldap falg
	 */
	private static String ldapFlag;
	/**
	 * tableau IP
	 */
	private static String tableauIP;
	/**
	 * tableau port
	 */
	private static String tableauPort;
	/**
	 * tableau user
	 */
	private static String tableauUser;
	/**
	 * 域帐号验证WebService
	 */
	private static String domainService;
	

	/**
	 * ssas服务链接
	 */
	private static String ssasService;
	/**
	 * ssas驱动
	 */
	private static String ssasDriver;
	
	/**
	 * ssas数据库
	 */
	private static String ssasCatalog;
	/**
	 * ssas用户
	 */
	private static String ssasUser;
	/**
	 * ssas密码
	 */
	private static String ssasPwd;

	
	
	static {
        Properties prop = new Properties();
        InputStream in = ReadProperties.class.getResourceAsStream("/init.properties");
        try {
            prop.load(in);
            ldapIp = prop.getProperty("ldapIp").trim();
            ldapPort = prop.getProperty("ldapPort").trim();
            ldapUserDomain = prop.getProperty("ldapUserDomain").trim();
            ldapRoot = prop.getProperty("ldapRoot").trim();
            ldapFlag = prop.getProperty("ldapFlag").trim();
            tableauIP = prop.getProperty("tableauIP").trim();
            tableauPort = prop.getProperty("tableauPort").trim();
            tableauUser = prop.getProperty("tableauUser").trim();
            domainService = prop.getProperty("domainService").trim();
            
        	ssasService = prop.getProperty("ssasService").trim();
			ssasDriver = prop.getProperty("ssasDriver").trim();
			ssasCatalog = prop.getProperty("ssasCatalog").trim();
			ssasUser = prop.getProperty("ssasUser").trim();
			ssasPwd = prop.getProperty("ssasPwd").trim();

        } catch (IOException e) {   
            e.printStackTrace();   
        }
    }

	public static String getLdapIp() {
		return ldapIp;
	}

	public static String getLdapPort() {
		return ldapPort;
	}

	public static String getLdapUserDomain() {
		return ldapUserDomain;
	}

	public static String getLdapRoot() {
		return ldapRoot;
	}

	public static String getLdapFlag() {
		return ldapFlag;
	}

	public static String getTableauIP() {
		return tableauIP;
	}

	public static String getTableauPort() {
		return tableauPort;
	}

	public static String getTableauUser() {
		return tableauUser;
	}

	public static String getDomainService() {
		return domainService;
	}
	
	public static String getSsasService() {
		return ssasService;
	}


	public static String getSsasDriver() {
		return ssasDriver;
	}


	public static String getSsasCatalog() {
		return ssasCatalog;
	}


	public static String getSsasUser() {
		return ssasUser;
	}

	public static String getSsasPwd() {
		return ssasPwd;
	}
}
