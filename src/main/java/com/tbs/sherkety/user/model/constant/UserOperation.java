package com.tbs.sherkety.user.model.constant;

public enum UserOperation {
  REGISTRATION("REGISTRATION"), FORGET_PASSWORD("FORGET_PASSWORD");

  private String value;

  private UserOperation(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
