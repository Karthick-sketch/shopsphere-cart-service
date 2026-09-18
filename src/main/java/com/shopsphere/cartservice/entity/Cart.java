package com.shopsphere.cartservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
  name = "cart",
  uniqueConstraints = {
    @UniqueConstraint(
      name = "uk_cart_user_product",
      columnNames = { "user_id", "product_id" }
    ),
  }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long productId;

  @Column(nullable = false)
  private Integer quantity;
}
