package com.tbs.sherkety.user.login.exception;

public class UserNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "User not found";
	
	public UserNotFoundException() {
		super(EXCEPTION_MESSAGE);
	}

}
