package com.training.session11.cartfullreqresp.adapter.outbound;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "item-service",fallback = OutBoundItemRestFallBack.class)
public interface OutBoundItemRest {

    @GetMapping("/items/{itemNo}")
    public Optional<ItemDTO> loadItemById(@PathVariable("itemNo") String itemNo);
}
