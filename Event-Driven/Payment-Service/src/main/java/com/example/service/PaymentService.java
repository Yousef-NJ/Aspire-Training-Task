package com.example.service;

import com.example.adapter.messages.outbound.PaymentEventChannel;
import com.example.adapter.repo.PaymentRepo;
import com.example.events.CartChangeStateEvent;
import com.example.model.Payment;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(PaymentEventChannel.class)
public class PaymentService {
    private final PaymentRepo repo;
    private final PaymentEventChannel paymentEventChannel;

    public PaymentService(PaymentRepo repo,PaymentEventChannel paymentEventChannel) {
        this.repo = repo;
        this.paymentEventChannel = paymentEventChannel;
    }

    public boolean putPayment(Payment payment){
        repo.putPayment(payment);
        return true;
    }

    public Payment get(String cartNo){
        return repo.get(cartNo).orElseThrow(()->new RuntimeException("No Such Cart"));
    }

    public boolean pay(String cartNo){
        Payment payment = get(cartNo);
        if(payment.getState().equals("Pending")){
            repo.updateState(cartNo,"Payment-Succeed");
            sendCartChangeStateEvent(payment);
            return true;
        }
        return false;
    }


    private void  sendCartChangeStateEvent(Payment payment) {
        CartChangeStateEvent cartChangeStateEvent=CartChangeStateEvent
                .builder()
                .state(payment.getState())
                .cartNo(payment.getCartNo())
                .state(payment.getState())
                .totalPrice(payment.getTotalPrice())
                .build();
        Message<CartChangeStateEvent> msg=MessageBuilder.withPayload(cartChangeStateEvent).build();
        paymentEventChannel.outputEvents().send(msg);
    }

}
