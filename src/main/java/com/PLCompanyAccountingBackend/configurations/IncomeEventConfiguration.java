package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class IncomeEventConfiguration {
    @Bean
    public IncomeEventService incomeEventService(IncomeEventRepository incomeEventRepository){
        return new IncomeEventService(incomeEventRepository);
    }
}
