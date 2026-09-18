package com.shopsphere.cartservice.feign;

import com.shopsphere.cartservice.dto.product.*;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("SHOPSPHERE-PRODUCT-SERVICE")
public interface ProductInterface {
  @GetMapping("/api/products/info/{id}")
  ProductInfo getInfo(@PathVariable Long id);

  @PostMapping("/api/products/info/bash")
  ResponseEntity<List<ProductInfo>> getInfos(
    @RequestBody ProductIdsRequest ids
  );
}
