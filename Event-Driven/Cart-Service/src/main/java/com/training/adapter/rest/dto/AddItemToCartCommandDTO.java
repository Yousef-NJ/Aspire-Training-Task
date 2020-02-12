package com.training.adapter.rest.dto;

import lombok.Data;

@Data
public class AddItemToCartCommandDTO {
    private final String itemNo;
    private final int quantity;
}
