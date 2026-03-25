package com.jordi.pojos;

public class MsgError {
	private int code;
	private String message;
	
	public MsgError(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public MsgError() {
		super();
		this.code = 0;
		this.message = "";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MsgError [code=" + code + ", message=" + message + "]";
	}
}
