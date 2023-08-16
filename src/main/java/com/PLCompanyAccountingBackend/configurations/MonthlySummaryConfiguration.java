package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import com.PLCompanyAccountingBackend.services.SummaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MonthlySummaryConfiguration {

    @Bean
    public MonthlySummaryService monthlySummaryService(MonthlySummaryRepository monthlySummaryRepository,
                                                       SummaryService summaryService) {
        return new MonthlySummaryService(monthlySummaryRepository, summaryService);
    }
}