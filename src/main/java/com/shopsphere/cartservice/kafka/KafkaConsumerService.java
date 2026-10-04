package com.shopsphere.cartservice.kafka;

import com.shopsphere.cartservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {

  private final CartService cartService;

  @KafkaListener(
    topics = "${kafka.topic.payment-status-changed}",
    groupId = "${kafka.consumer.group-id}"
  )
  public void handlePaymentStatusChangedEvent(PaymentStatusChangedEvent event) {
    cartService.handlePaymentStatusChanged(event.getData());
  }
}
