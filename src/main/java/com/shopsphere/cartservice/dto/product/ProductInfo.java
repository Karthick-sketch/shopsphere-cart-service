package com.shopsphere.cartservice.dto.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {

  private Long id;
  private String name;
  private BigDecimal price;
  private Integer stock;
  private String image;
}
