package com.mozi.chintms.common.exception;

/**
 * 
 * 项目名称：yicada<br>
 * 类名称：BusiException<br>
 * 类描述：自定义业务层异常<br>
 * 创建时间：2015-3-16<br>
 * 
 * @author wuhanhua
 * @version 1.0.0
 *
 */
public class BusiException extends BaseException {

	private static final long serialVersionUID = -7297752186777167205L;

	public BusiException() {
		super();
	}

	public BusiException(int id, String[] args) {
		super(id, args);
	}

	public BusiException(int id) {
		super(id);
	}

	public BusiException(String message) {
		super(message);
	}

	public BusiException(Throwable cause) {
		super(cause);
	}
}
