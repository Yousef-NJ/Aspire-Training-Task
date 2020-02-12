package com.example.demo.adapter.repo;

import com.example.demo.model.Payment;

import java.util.Optional;

public interface PaymentRepo {

    public boolean putPayment(Payment payment);

    public Optional<Payment> get(String cartNo);

    public boolean updateState(String cartNo,String state);

}
