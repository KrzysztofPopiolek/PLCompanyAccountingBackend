package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.OtherPurchaseCostsEventRepository;
import com.PLCompanyAccountingBackend.services.OtherPurchaseCostsEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class OtherPurchaseCostsEventConfiguration {
    @Bean
    public OtherPurchaseCostsEventService otherPurchaseCostsEventService(OtherPurchaseCostsEventRepository otherPurchaseCostsEventRepository) {
        return new OtherPurchaseCostsEventService(otherPurchaseCostsEventRepository);
    }
}
