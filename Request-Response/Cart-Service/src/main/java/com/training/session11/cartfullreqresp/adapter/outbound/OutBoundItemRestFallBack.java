package com.training.session11.cartfullreqresp.adapter.outbound;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OutBoundItemRestFallBack implements OutBoundItemRest{

    @Override
    @Cacheable(cacheNames = "itemCache",key = "#itemNo")
    public Optional<ItemDTO> loadItemById(String itemNo) {
        return Optional.of(new ItemDTO(itemNo,400));
    }
}
