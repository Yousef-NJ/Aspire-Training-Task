package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {
    @EqualsAndHashCode.Include
    private String cartNo;
    private String state;
    private double totalPrice;
}
