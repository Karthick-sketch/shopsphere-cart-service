package com.shopsphere.cartservice.exception;

public class CartItemNotFoundException extends RuntimeException {

  public CartItemNotFoundException() {
    super("Item not found in cart");
  }
}
