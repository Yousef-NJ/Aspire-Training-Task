package com.example.demo.adapter.rest;

import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
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
    public void succeed(@PathVariable("cartNo") String cartNo){
        paymentService.paymentSucceed(cartNo);
    }

    @PutMapping("/{cartNo}/failed")
    public void failed(@PathVariable("cartNo") String cartNo){
        paymentService.paymentFailed(cartNo);
    }

}
