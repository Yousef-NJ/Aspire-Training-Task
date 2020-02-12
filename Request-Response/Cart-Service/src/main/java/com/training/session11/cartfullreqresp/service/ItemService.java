package com.training.session11.cartfullreqresp.service;

import com.training.session11.cartfullreqresp.adapter.outbound.ItemDTO;
import com.training.session11.cartfullreqresp.adapter.outbound.OutBoundItemRest;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final OutBoundItemRest outBoundItemRest;

    public ItemService(OutBoundItemRest outBoundItemRest) {
        this.outBoundItemRest = outBoundItemRest;
    }

    @CachePut(cacheNames = "itemCache",key = "#itemNo")
    public Optional<ItemDTO> getItemByItemNo(String itemNo){
        return outBoundItemRest.loadItemById(itemNo);
    }


}
