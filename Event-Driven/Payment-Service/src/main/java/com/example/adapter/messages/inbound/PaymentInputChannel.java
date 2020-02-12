package com.example.adapter.messages.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentInputChannel {

    String INPUT="payment-queue";

    @Input(INPUT)
    public SubscribableChannel input();
}
