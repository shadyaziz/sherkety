package com.tbs.sherkety.user.model.constant;

/**
 * Enum to hold the different user status values
 * 
 */
public enum UserStatus {
  PENDING("PENDING"), ACTIVE("ACTIVE"), BLOCKED("BLOCKED");

  private String value;

  private UserStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
