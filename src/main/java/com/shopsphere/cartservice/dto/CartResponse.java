package com.shopsphere.cartservice.dto;

import com.shopsphere.cartservice.dto.product.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

  private Long id;
  private Long userId;
  private Integer quantity;
  private ProductInfo productInfo;
}
