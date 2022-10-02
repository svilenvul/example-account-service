package com.example.accountservice.controller.exception;

import com.example.accountservice.controller.models.error.ErrorResponse;
import com.example.accountservice.domain.exception.ElementNotFoundException;
import com.example.accountservice.domain.exception.PreconditionFailedException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ElementNotFoundException.class)
  public final ResponseEntity<ErrorResponse> handleElementNotFoundException
      (ElementNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    if (ex.getLocalizedMessage() != null) {
      details.add(ex.getLocalizedMessage());
    }
    ErrorResponse error = new ErrorResponse("Element not found", details);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<ErrorResponse> handleIllegalArgumentsException
      (IllegalArgumentException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    if (ex.getLocalizedMessage() != null) {
      details.add(ex.getLocalizedMessage());
    }
    ErrorResponse error = new ErrorResponse("Invalid input data", details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PreconditionFailedException.class)
  public final ResponseEntity<ErrorResponse> handlePreconditionFailed
      (PreconditionFailedException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    if (ex.getLocalizedMessage() != null) {
      details.add(ex.getLocalizedMessage());
    }
    ErrorResponse error = new ErrorResponse("Precondition failed", details);
    return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
  }
}
