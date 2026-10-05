package com.shopsphere.cartservice.dto.order;

import lombok.Data;

@Data
public class OrderItemData {

  private Long productId;
  private Integer quantity;
}
