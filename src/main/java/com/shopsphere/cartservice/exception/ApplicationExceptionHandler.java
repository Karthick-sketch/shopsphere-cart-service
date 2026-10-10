package com.shopsphere.cartservice.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(CartItemNotFoundException.class)
  public ResponseEntity<?> handleCartItemNotFoundException(
    CartItemNotFoundException ex
  ) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      Map.of("message", ex.getMessage())
    );
  }

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<?> handleInsufficientStockException(
    InsufficientStockException ex
  ) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
      Map.of("message", ex.getMessage())
    );
  }
}
