package com.sentryc.marketsystem;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ProducerSellerState {

    UUID producerId;
    String producerName;
    SellerState sellerState;
    UUID sellerId;
}
