package com.shopsphere.cartservice.repository;

import com.shopsphere.cartservice.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByUserId(Long userId);

  void deleteByUserId(Long userId);
}
