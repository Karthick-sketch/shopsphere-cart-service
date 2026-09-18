package com.shopsphere.cartservice.controller;

import com.shopsphere.cartservice.dto.CartResponse;
import com.shopsphere.cartservice.entity.Cart;
import com.shopsphere.cartservice.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId) {
    return ResponseEntity.ok(cartService.fetchCart(userId));
  }

  @PostMapping
  public ResponseEntity<CartResponse> addItem(@RequestBody Cart cart) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
      cartService.addItem(cart)
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<CartResponse> updateItem(
    @PathVariable Long id,
    @RequestBody Cart cart
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(
      cartService.updateItem(id, cart)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeItem(@PathVariable Long id) {
    cartService.removeItem(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/user/{userId}")
  public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
    cartService.clearCart(userId);
    return ResponseEntity.noContent().build();
  }
}
