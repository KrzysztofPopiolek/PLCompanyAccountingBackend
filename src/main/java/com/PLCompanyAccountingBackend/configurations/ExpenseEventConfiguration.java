package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
import com.PLCompanyAccountingBackend.services.ExpenseEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ExpenseEventConfiguration {

    @Bean
    public ExpenseEventService expenseEventService(ExpenseEventRepository expenseEventRepository) {
        return new ExpenseEventService(expenseEventRepository);
    }
}