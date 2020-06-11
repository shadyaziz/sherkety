package com.tbs.sherkety.company.exception;

public class CompanyNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  private static final String EXCEPTION_MESSAGE = "Company not found Exception";

  public CompanyNotFoundException() {
    super(EXCEPTION_MESSAGE);
  }
}
