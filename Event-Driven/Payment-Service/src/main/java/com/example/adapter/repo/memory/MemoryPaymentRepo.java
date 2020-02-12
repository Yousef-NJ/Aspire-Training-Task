package com.example.adapter.repo.memory;

import com.example.adapter.repo.PaymentRepo;
import com.example.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryPaymentRepo implements PaymentRepo {
    private final Map<String,Payment> repo=new ConcurrentHashMap<>();

    @Override
    public boolean putPayment(Payment payment) {
        repo.put(payment.getCartNo(),payment);
        return true;
    }

    @Override
    public Optional<Payment> get(String cartNo) {
        if(repo.containsKey(cartNo)){
            return Optional.of(repo.get(cartNo));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateState(String cartNo, String state) {
        if(repo.containsKey(cartNo)){
            repo.get(cartNo).setState(state);
            return true;
        }
        return false;
    }
}
