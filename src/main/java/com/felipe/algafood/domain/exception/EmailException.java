package com.felipe.algafood.domain.exception;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailException(String msg) {
		super(msg);
	}
	
	public EmailException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
