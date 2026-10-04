package com.shopsphere.cartservice.dto.payment;

import com.shopsphere.cartservice.enums.PaymentMethod;
import com.shopsphere.cartservice.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentStatusChangedData {

  private Long userId;
  private Long orderId;
  private Long paymentId;
  private PaymentStatus status;
  private PaymentMethod paymentMethod;
}
