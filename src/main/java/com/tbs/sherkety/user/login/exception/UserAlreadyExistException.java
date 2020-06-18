package com.tbs.sherkety.user.login.exception;

public class UserAlreadyExistException extends Exception {
  private static final long serialVersionUID = 1L;
  private static final String EXCEPTION_MESSAGE = "User Email already exist";

  public UserAlreadyExistException() {
    super(EXCEPTION_MESSAGE);
  }

}
