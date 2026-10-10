package com.shopsphere.cartservice.feign;

import com.shopsphere.cartservice.config.FeignAuthConfig;
import com.shopsphere.cartservice.dto.inventory.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
  name = "SHOPSPHERE-INVENTORY-SERVICE",
  configuration = FeignAuthConfig.class
)
public interface InventoryInterface {
  @PostMapping("/api/inventory/availability")
  ResponseEntity<AvailabilityResponse> getAvailability(
    @RequestBody AvailabilityRequest request
  );
}
