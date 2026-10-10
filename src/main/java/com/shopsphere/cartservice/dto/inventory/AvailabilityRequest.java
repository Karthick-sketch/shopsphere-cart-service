package com.shopsphere.cartservice.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityRequest {

  private Long productId;
  private Integer quantity;
}
