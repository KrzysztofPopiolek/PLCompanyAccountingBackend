package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import com.PLCompanyAccountingBackend.services.ExpenseEventService;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MonthlySummaryConfiguration {

    @Bean
    public MonthlySummaryService monthlySummaryService(MonthlySummaryRepository monthlySummaryRepository,
                                                       ExpenseEventService expenseEventService,
                                                       IncomeEventService incomeEventService) {
        return new MonthlySummaryService(monthlySummaryRepository, expenseEventService, incomeEventService);
    }

}