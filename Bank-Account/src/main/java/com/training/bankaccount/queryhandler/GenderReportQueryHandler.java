package com.training.bankaccount.queryhandler;

import com.training.bankaccount.adapter.repository.GenderAccountRepository;
import com.training.common.model.GenderAccountReport;
import com.training.common.query.GetGenderAccountReport;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class GenderReportQueryHandler {

    private final GenderAccountRepository genderAccountRepository;

    public GenderReportQueryHandler(GenderAccountRepository genderAccountRepository) {
        this.genderAccountRepository = genderAccountRepository;
    }

    @QueryHandler
    public GenderAccountReport genderAccountReport(
            GetGenderAccountReport getGenderAccountReport){


        return genderAccountRepository.loadGenderAccountReport(
                getGenderAccountReport.getAccountType()

        );

    }
}
