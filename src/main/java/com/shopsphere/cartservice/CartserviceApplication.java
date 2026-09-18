package com.shopsphere.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CartserviceApplication.class, args);
  }
}
