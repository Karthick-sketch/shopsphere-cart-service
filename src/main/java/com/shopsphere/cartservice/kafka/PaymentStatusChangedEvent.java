package com.shopsphere.cartservice.kafka;

import com.shopsphere.cartservice.dto.payment.PaymentStatusChangedData;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class PaymentStatusChangedEvent {

  private UUID eventId;
  private String eventType;
  private Instant initiatedAt;
  private PaymentStatusChangedData data;
}
