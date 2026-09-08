package com.shopsphere.cartservice.service;

import com.shopsphere.cartservice.entity.Cart;
import com.shopsphere.cartservice.entity.CartItem;
import com.shopsphere.cartservice.repository.CartItemRepository;
import com.shopsphere.cartservice.repository.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;

  public List<Cart> findAll() {
    return cartRepository.findAll();
  }

  public Cart findById(Long id) {
    return cartRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
  }

  public Cart findByUserId(Long userId) {
    return cartRepository
      .findByUserId(userId)
      .orElseThrow(() ->
        new RuntimeException("Cart not found for user id: " + userId)
      );
  }

  public Cart create(Cart cart) {
    return cartRepository.save(cart);
  }

  public CartItem addItem(Long cartId, CartItem item) {
    Cart cart = findById(cartId);
    item.setCart(cart);
    return cartItemRepository.save(item);
  }

  public CartItem updateItem(Long itemId, CartItem updated) {
    CartItem existing = cartItemRepository
      .findById(itemId)
      .orElseThrow(() ->
        new RuntimeException("Cart item not found with id: " + itemId)
      );
    existing.setQuantity(updated.getQuantity());
    return cartItemRepository.save(existing);
  }

  public void removeItem(Long itemId) {
    cartItemRepository.deleteById(itemId);
  }

  @Transactional
  public void clearCart(Long cartId) {
    cartItemRepository.deleteByCartId(cartId);
  }

  public void delete(Long id) {
    findById(id);
    cartRepository.deleteById(id);
  }

  public List<CartItem> findItemsByCartId(Long cartId) {
    return cartItemRepository.findByCartId(cartId);
  }
}
