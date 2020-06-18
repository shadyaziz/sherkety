package com.tbs.sherkety.token.exception;

public class TokenNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  private static final String EXCEPTION_MESSAGE = "Token is not found";

  public TokenNotFoundException() {
    super(EXCEPTION_MESSAGE);
  }
}
