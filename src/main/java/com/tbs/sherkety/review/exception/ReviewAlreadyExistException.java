package com.tbs.sherkety.review.exception;

public class ReviewAlreadyExistException extends Exception {
  private static final long serialVersionUID = 1L;
  private static final String EXCEPTION_MESSAGE =
      "The following user has already reviewed the company. Review is not unique";

  public ReviewAlreadyExistException() {
    super(EXCEPTION_MESSAGE);
  }



}
