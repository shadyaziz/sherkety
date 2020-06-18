package com.tbs.sherkety.token.exception;

public class TokenInvalidException extends Exception {

  private static final long serialVersionUID = 1L;
  private static final String EXCEPTION_MESSAGE = "Token is invalid or expired";

  public TokenInvalidException() {
    super(EXCEPTION_MESSAGE);
  }


}
