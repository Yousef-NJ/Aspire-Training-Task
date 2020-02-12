package com.training.bankaccount.eventHandler;

import com.training.bankaccount.adapter.repository.GenderAccountRepository;
import com.training.common.event.account.NewAccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandlers {

    private final GenderAccountRepository genderAccountRepository;

    public AccountEventHandlers(GenderAccountRepository genderAccountRepository) {
        this.genderAccountRepository = genderAccountRepository;
    }

    @EventHandler
    public void accountCreated(NewAccountCreatedEvent newAccountCreated){
        genderAccountRepository
                .incrementAccount(newAccountCreated.getAccountType(),newAccountCreated.getGender());
    }
}
