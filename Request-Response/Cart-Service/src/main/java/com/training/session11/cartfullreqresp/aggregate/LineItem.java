package com.training.session11.cartfullreqresp.aggregate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class LineItem {

    private String itemNo;
    private int price;
    private int qty;
}
