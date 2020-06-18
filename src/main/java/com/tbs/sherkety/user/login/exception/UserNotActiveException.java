package com.tbs.sherkety.user.login.exception;

/**
 * 
 * User has no Active Status Exception
 *
 */
public class UserNotActiveException extends Exception {
  private static final long serialVersionUID = 1L;

  private static final String EXCEPTION_MESSAGE =
      "User has no ACTIVE status. He is PENDING or BLOCKED";

  public UserNotActiveException() {
    super(EXCEPTION_MESSAGE);
  }
}
