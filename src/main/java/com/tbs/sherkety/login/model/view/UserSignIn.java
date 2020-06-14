package com.tbs.sherkety.login.model.view;

public interface UserSignIn {
  public Integer getIdUser();

  public String getEmail();

  public String getEncodedPassword();

  public Integer getErrorCounter();

}
