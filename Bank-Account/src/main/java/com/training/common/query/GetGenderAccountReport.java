package com.training.common.query;

import com.training.common.command.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetGenderAccountReport {

    private AccountType accountType;
}
