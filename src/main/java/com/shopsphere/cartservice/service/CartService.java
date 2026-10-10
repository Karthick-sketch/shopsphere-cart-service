package com.shopsphere.cartservice.service;

import com.shopsphere.cartservice.dto.CartResponse;
import com.shopsphere.cartservice.dto.inventory.*;
import com.shopsphere.cartservice.dto.order.OrderPlacedData;
import com.shopsphere.cartservice.dto.product.*;
import com.shopsphere.cartservice.entity.Cart;
import com.shopsphere.cartservice.exception.CartItemNotFoundException;
import com.shopsphere.cartservice.exception.InsufficientStockException;
import com.shopsphere.cartservice.feign.*;
import com.shopsphere.cartservice.repository.CartRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;

  private final InventoryInterface inventoryInterface;
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

  public Cart findItem(Long id) {
    return cartRepository
      .findById(id)
      .orElseThrow(CartItemNotFoundException::new);
  }

  public CartResponse addItem(Cart cart) {
    checkAvailability(cart);
    cart = cartRepository.save(cart);
    return toCartResponse(cart, getProductInfoById(cart.getProductId()));
  }

  public CartResponse updateItem(Long itemId, Cart updatedCart) {
    checkAvailability(updatedCart);
    Cart existing = findItem(itemId);
    existing.setQuantity(updatedCart.getQuantity());
    existing = cartRepository.save(existing);
    return toCartResponse(
      existing,
      getProductInfoById(existing.getProductId())
    );
  }

  private void checkAvailability(Cart cart) {
    AvailabilityResponse response = inventoryInterface
      .getAvailability(toAvailablityRequest(cart))
      .getBody();
    if (response == null || !response.getIsAvailable()) {
      throw new InsufficientStockException(
        "Required stock is not available for product " + cart.getProductId()
      );
    }
  }

  public void removeItem(Long itemId) {
    cartRepository.delete(findItem(itemId));
  }

  @Transactional
  public void clearCart(Long userId) {
    cartRepository.deleteByUserId(userId);
  }

  @Transactional
  public void handleOrderPlaced(OrderPlacedData data) {
    clearCart(data.getUserId());
  }

  private ProductInfo getProductInfoById(Long id) {
    return productInterface.getInfo(id);
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
    return productInterface.getInfos(productIds).getBody();
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

  private AvailabilityRequest toAvailablityRequest(Cart cart) {
    return new AvailabilityRequest(cart.getProductId(), cart.getQuantity());
  }
}
