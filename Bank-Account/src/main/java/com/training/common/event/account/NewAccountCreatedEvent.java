package com.training.common.event.account;

import com.training.common.command.account.AccountType;
import com.training.common.command.account.Gender;
import lombok.Value;

@Value
public class NewAccountCreatedEvent extends AbstractAccountEvent {

    private String accountNo;

    private String name;

    private Gender gender;

    private AccountType accountType;
}
