package com.example.adapter.messages.inbound;

import com.example.model.Payment;
import com.example.service.PaymentService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(PaymentInputChannel.class)
public class PaymentListener {
    private PaymentService paymentService;

    public PaymentListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @StreamListener(PaymentInputChannel.INPUT)
    public void handleMessage(Payment payment){
        paymentService.putPayment(payment);
    }
}
