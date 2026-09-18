package com.shopsphere.cartservice.service;

import com.shopsphere.cartservice.dto.CartResponse;
import com.shopsphere.cartservice.dto.product.*;
import com.shopsphere.cartservice.entity.Cart;
import com.shopsphere.cartservice.feign.ProductInterface;
import com.shopsphere.cartservice.repository.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;

  private final ProductInterface productInterface;

  public List<CartResponse> fetchCart(Long userId) {
    List<Cart> cartItems = cartRepository.findByUserId(userId);
    List<ProductInfo> productInfos = getProductInfos(getProductIds(cartItems));

    return cartItems
      .stream()
      .map(cart ->
        toCartResponse(cart, findProductInfo(cart.getProductId(), productInfos))
      )
      .toList();
  }

  public Cart addItem(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart updateItem(Long itemId, Cart updated) {
    Cart existing = cartRepository
      .findById(itemId)
      .orElseThrow(() ->
        new RuntimeException("Cart item not found with id: " + itemId)
      );
    existing.setQuantity(updated.getQuantity());
    return cartRepository.save(existing);
  }

  public void removeItem(Long itemId) {
    cartRepository.deleteById(itemId);
  }

  public void clearCart(Long userId) {
    cartRepository.deleteByUserId(userId);
  }

  private ProductIdsRequest getProductIds(List<Cart> cartItems) {
    return new ProductIdsRequest(
      cartItems
        .stream()
        .map(cart -> cart.getProductId())
        .toList()
    );
  }

  private List<ProductInfo> getProductInfos(ProductIdsRequest productIds) {
    return productInterface.getProductInfos(productIds).getBody();
  }

  private ProductInfo findProductInfo(Long id, List<ProductInfo> productInfos) {
    return productInfos
      .stream()
      .filter(p -> p.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

  private CartResponse toCartResponse(Cart cart, ProductInfo productInfo) {
    return new CartResponse(
      cart.getId(),
      cart.getUserId(),
      cart.getQuantity(),
      productInfo
    );
  }
}
