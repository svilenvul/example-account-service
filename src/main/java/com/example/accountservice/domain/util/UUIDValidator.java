package com.example.accountservice.domain.util;

import java.util.regex.Pattern;

public class UUIDValidator {

  private static final String RFC_4122_REGEX = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$";
  private static final Pattern PATTERN = Pattern.compile(RFC_4122_REGEX);

  public static final boolean isValidUUID(String uuid) {
    return uuid != null && PATTERN.matcher(uuid).matches();
  }
}
