package com.example.demo.adapter.outbound;

import com.example.demo.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@FeignClient(value = "cart-service")
public interface OutBoundCartRest {

    @GetMapping("/carts/{cartNo}")
    public Optional<Payment> loadCart(@PathVariable("cartNo") String cartNo);

    @PutMapping("/carts/{cartNo}/paid")
    public void paid(@PathVariable("cartNo") String cartNo);

}
