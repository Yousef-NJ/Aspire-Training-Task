package com.training.bankaccount.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AmountDOT {

    @Min(0)
    private int amount;
}
