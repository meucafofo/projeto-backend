package com.tcc.moradiaestudantil.exception;

public class RegisterException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public RegisterException (String msg) {
		super(msg);
	}


	public RegisterException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public RegisterException (Throwable cause) {
		super(cause);
	}
}

