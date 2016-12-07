package com.mozi.chintms.common.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 8362826695538257671L;

	private int id = 0;
	private String[] args = null;
	protected String message = null;

	public BaseException() {
		super();
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message) {
		super();
		this.message = message;
	}

	public BaseException(int id) {
		super();
		this.id = id;
	}

	public BaseException(int id, String[] args) {
		super();
		this.id = id;
		this.args = args;
	}

	@Override
	public String getMessage() {
		if (this.message != null) {
			return this.message;
		}
		String mess = Exceptions.getInstance().getExpInfo(String.valueOf(id));
		for (int i = 0; args != null && i < args.length; i++) {
			mess = mess.replaceFirst("\\{\\d{1}\\}", args[i]);
		}
		return mess;
	}
}
