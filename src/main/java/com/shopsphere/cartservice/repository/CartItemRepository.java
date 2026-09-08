package com.shopsphere.cartservice.repository;

import com.shopsphere.cartservice.entity.CartItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  List<CartItem> findByCartId(Long cartId);
  Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
  void deleteByCartId(Long cartId);
}
