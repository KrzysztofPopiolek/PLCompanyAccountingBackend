package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.services.ExpenseEventService;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import com.PLCompanyAccountingBackend.services.SummaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SummaryConfiguration {

    @Bean
    public SummaryService SummaryService(ExpenseEventService expenseEventService,
                                         IncomeEventService incomeEventService) {
        return new SummaryService(expenseEventService, incomeEventService);
    }

}