package com.training.session11.cartfullreqresp.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemToCartCommand extends AbstractCartCommand{

    private String cartId;

    private String itemNo;

    private int qty;

}
