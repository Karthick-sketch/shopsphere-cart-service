package com.shopsphere.cartservice.controller;

import com.shopsphere.cartservice.entity.Cart;
import com.shopsphere.cartservice.entity.CartItem;
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

  @GetMapping
  public ResponseEntity<List<Cart>> getAll() {
    return ResponseEntity.ok(cartService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cart> getById(@PathVariable Long id) {
    return ResponseEntity.ok(cartService.findById(id));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<Cart> getByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(cartService.findByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<Cart> create(@RequestBody Cart cart) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
      cartService.create(cart)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cartService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // --- Cart Item endpoints ---

  @GetMapping("/{cartId}/items")
  public ResponseEntity<List<CartItem>> getItems(@PathVariable Long cartId) {
    return ResponseEntity.ok(cartService.findItemsByCartId(cartId));
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<CartItem> addItem(
    @PathVariable Long cartId,
    @RequestBody CartItem item
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
      cartService.addItem(cartId, item)
    );
  }

  @PutMapping("/items/{itemId}")
  public ResponseEntity<CartItem> updateItem(
    @PathVariable Long itemId,
    @RequestBody CartItem item
  ) {
    return ResponseEntity.ok(cartService.updateItem(itemId, item));
  }

  @DeleteMapping("/items/{itemId}")
  public ResponseEntity<Void> removeItem(@PathVariable Long itemId) {
    cartService.removeItem(itemId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}/items")
  public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
    cartService.clearCart(cartId);
    return ResponseEntity.noContent().build();
  }
}
