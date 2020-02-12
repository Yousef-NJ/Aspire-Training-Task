package com.example.adapter.rest;

import com.example.model.Payment;
import com.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{cartNo}")
    public Payment get(@PathVariable("cartNo") String cartNo){
        return paymentService.get(cartNo);
    }

    @PutMapping("/{cartNo}/succeed")
    public boolean pay(@PathVariable("cartNo") String cartNo){
        return paymentService.paymentSucceed(cartNo);
    }


}
