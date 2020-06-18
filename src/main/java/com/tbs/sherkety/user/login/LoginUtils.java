package com.tbs.sherkety.user.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class LoginUtils {
  private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  private static String URL_FORMAT = "%s://%s:%s/register/%s";

  public static String getEncodedString(String value) {
    return encoder.encode(value);
  }

  public static boolean comparePasswords(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }

  public static String buildUrlRegisterToken(String scheme, String serverName, int port,
      String token) {
    return String.format(URL_FORMAT, scheme, serverName, port, token);
  }
}
