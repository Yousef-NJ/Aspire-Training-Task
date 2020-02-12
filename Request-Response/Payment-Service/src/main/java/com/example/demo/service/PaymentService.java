package com.example.demo.service;

import com.example.demo.adapter.outbound.OutBoundCartRest;
import com.example.demo.adapter.repo.PaymentRepo;
import com.example.demo.model.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepo repo;
    private final OutBoundCartRest outBoundCartRest;

    public PaymentService(PaymentRepo repo,OutBoundCartRest outBoundCartRest) {
        this.repo = repo;
        this.outBoundCartRest = outBoundCartRest;
    }

    public Payment get(String cartNo){
        return repo.get(cartNo).orElseThrow(()->new RuntimeException("No Such Payment For Cart"));
    }

    public void paymentSucceed(String cartNo){
        if(repo.get(cartNo).isPresent()){
            throw new RuntimeException("Payment Already Succeed");
        }
        Optional<Payment> optionalPayment = outBoundCartRest.loadCart(cartNo);
        Payment payment = optionalPayment.orElseThrow(()->new RuntimeException("No Such Cart"));
        if(!payment.getCartStatus().equals("BINDING")){
            throw new RuntimeException("Cart must be in Binding status");
        }
        repo.putPayment(payment);
        outBoundCartRest.paid(cartNo);
    }

    public void paymentFailed(String cartNo){
        if(repo.get(cartNo).isPresent()){
            throw new RuntimeException("Payment Already Succeed");
        }
        Optional<Payment> optionalPayment = outBoundCartRest.loadCart(cartNo);
        Payment payment = optionalPayment.orElseThrow(()->new RuntimeException("No Such Cart"));
        if(!payment.getCartStatus().equals("BINDING")){
            throw new RuntimeException("Cart must be in Binding status");
        }
    }


}
