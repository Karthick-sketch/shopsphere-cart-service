package com.shopsphere.cartservice.kafka;

import com.shopsphere.cartservice.dto.order.OrderPlacedData;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderPlacedEvent {

  private UUID eventId;
  private String eventType;
  private Instant initiatedAt;
  private OrderPlacedData data;
}
