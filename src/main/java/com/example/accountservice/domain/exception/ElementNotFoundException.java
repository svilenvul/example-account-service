package com.example.accountservice.domain.exception;

public class ElementNotFoundException extends RuntimeException {

  public ElementNotFoundException() {
    super();
  }

  public ElementNotFoundException(String message) {
    super(message);
  }

}
