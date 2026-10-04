package com.shopsphere.cartservice.kafka;

public final class KafkaConstants {

  private KafkaConstants() {}

  // consumer configs
  public static final String AUTO_OFFSET_RESET_EARLIEST = "earliest";
  public static final String TRUST_ALL_PACKAGES = "*";

  // event types
  public static final String PAYMENT_STATUS_CHANGED_EVENT_TYPE =
    "PAYMENT_STATUS_CHANGED";
}
