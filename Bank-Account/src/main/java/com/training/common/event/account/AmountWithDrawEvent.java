package com.training.common.event.account;

import lombok.Value;

@Value
public class AmountWithDrawEvent extends AbstractAccountEvent {

    private String accountNo;
    private int amount;
}
