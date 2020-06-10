package com.tbs.sherkety.login.exception;

public class PasswordIncorrectException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "Password is incorrect";
	
	public PasswordIncorrectException() {
		super(EXCEPTION_MESSAGE);
	}
}
