package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BusinessContractorConfiguration {

    @Bean
    public BusinessContractorService businessContractorService(BusinessContractorRepository businessContractorRepository) {
        return new BusinessContractorService(businessContractorRepository);
    }

}