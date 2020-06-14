package com.tbs.sherkety.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class LoginUtils {
  private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String getEncodedString(String value) {
    return encoder.encode(value);
  }

  public static boolean comparePasswords(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
