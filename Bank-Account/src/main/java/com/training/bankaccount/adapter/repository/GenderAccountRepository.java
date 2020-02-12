package com.training.bankaccount.adapter.repository;

import com.training.common.command.account.AccountType;
import com.training.common.command.account.Gender;
import com.training.common.model.GenderAccountReport;

public interface GenderAccountRepository {

    public GenderAccountReport loadGenderAccountReport(AccountType accountType);

    public void incrementAccount(AccountType accountType, Gender gender);
}
