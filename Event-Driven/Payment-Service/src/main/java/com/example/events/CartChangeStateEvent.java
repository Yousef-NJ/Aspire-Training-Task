package com.example.events;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CartChangeStateEvent {
    private String cartNo;
    private String state;
    private double totalPrice;
    @Builder.Default
    private String type = "CartChangeStateEvent";
}
