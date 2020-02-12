package com.training.session11.cartfullreqresp.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemQtyDTO {
    private String itemNo;
    private int qty;
}
