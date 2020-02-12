package com.training.session11.cartfullreqresp.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String userId;
    private String cartId;
    private String cartStatus;
    private int totalPrice;
}
