package com.tbs.sherkety.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class LoginUtils {

  public static String getHashedString(String value) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(value);
  }
}
