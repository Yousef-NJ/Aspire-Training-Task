package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {
    @EqualsAndHashCode.Include
    private String cartId;
    private String cartStatus;
    private double totalPrice;
}
