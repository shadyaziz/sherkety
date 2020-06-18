package com.tbs.sherkety.user.model.view;

import com.tbs.sherkety.user.model.constant.UserStatus;

public interface UserSignIn {
  public Integer getIdUser();

  public String getEmail();

  public String getEncodedPassword();

  public Integer getErrorCounter();

  public UserStatus getUserStatus();

}
