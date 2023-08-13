package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.ExpenseEventService;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AnnualSummaryConfiguration {

    @Bean
    public AnnualSummaryService annualSummaryService(AnnualSummaryRepository annualSummaryRepository,
                                                     ExpenseEventService expenseEventService,
                                                     IncomeEventService incomeEventService,
                                                     BusinessContractorService businessContractorService) {
        return new AnnualSummaryService(annualSummaryRepository, expenseEventService, incomeEventService, businessContractorService);
    }

}