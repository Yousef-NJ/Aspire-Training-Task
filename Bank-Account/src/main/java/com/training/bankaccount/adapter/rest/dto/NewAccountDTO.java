package com.training.bankaccount.adapter.rest.dto;

import com.training.common.command.account.AccountType;
import com.training.common.command.account.Gender;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NewAccountDTO {

    @NotEmpty
    private String name;

    private Gender gender;
    private AccountType accountType;
}
