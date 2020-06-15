package com.tbs.sherkety.common.exception;

public class GeneralInputException extends Exception {
  private static final long serialVersionUID = 1L;
  private static final String EXCEPTION_MESSAGE = "General Invalid input exception ";

  public GeneralInputException() {
    super(EXCEPTION_MESSAGE);
  }
}
