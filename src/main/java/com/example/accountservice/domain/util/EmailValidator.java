package com.example.accountservice.domain.util;

import java.util.regex.Pattern;

public class EmailValidator {

  private static final String RFC_5322_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
  private static final Pattern pattern = Pattern.compile(RFC_5322_REGEX);

  public static final boolean isEmailValid(String email) {
    return email != null && pattern.matcher(email).matches();
  }
}
