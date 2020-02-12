package com.training.session11.cartfullreqresp.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartPaidCommand extends AbstractCartCommand{
    private String cartId;
}
