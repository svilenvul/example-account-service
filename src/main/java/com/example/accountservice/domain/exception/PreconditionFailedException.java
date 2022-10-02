package com.example.accountservice.domain.exception;

public class PreconditionFailedException extends RuntimeException {

  public PreconditionFailedException() {
    super();
  }

  public PreconditionFailedException(String message) {
    super(message);
  }
}
