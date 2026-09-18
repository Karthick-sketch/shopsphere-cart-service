package com.shopsphere.cartservice.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(CartItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleCartItemNotFoundException(
    CartItemNotFoundException ex
  ) {
    Map<String, String> error = new HashMap<>();
    error.put("message", ex.getMessage());
    return error;
  }
}
