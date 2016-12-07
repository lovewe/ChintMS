package com.mozi.chintms.login.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.mozi.chintms.login.model.LoginUserInfo;

public interface UserLoginService {

	/**
	 * 用户登录。
	 */
	public LoginUserInfo userLogin(LoginUserInfo userDomain);
	
}
