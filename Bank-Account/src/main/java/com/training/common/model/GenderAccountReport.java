package com.training.common.model;

import com.training.common.command.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class GenderAccountReport {

    private AccountType accountType;

    private int maleCount;

    private int femaleCount;
}
