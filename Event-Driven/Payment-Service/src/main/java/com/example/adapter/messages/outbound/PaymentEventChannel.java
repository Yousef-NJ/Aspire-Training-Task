package com.example.adapter.messages.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PaymentEventChannel {
    String OUTPUT= "payment-events";

    @Output(OUTPUT)
    public MessageChannel outputEvents();

}
