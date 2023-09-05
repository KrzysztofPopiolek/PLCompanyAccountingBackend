package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.SummaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AnnualSummaryConfiguration {

    @Bean
    public AnnualSummaryService annualSummaryService(AnnualSummaryRepository annualSummaryRepository,
                                                     SummaryService summaryService) {
        return new AnnualSummaryService(annualSummaryRepository, summaryService);
    }
}