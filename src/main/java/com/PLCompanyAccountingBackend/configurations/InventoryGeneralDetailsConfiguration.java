package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class InventoryGeneralDetailsConfiguration {
    @Bean
    public InventoryGeneralDetailsService inventoryGeneralDetailsService(InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository) {
        return new InventoryGeneralDetailsService(inventoryGeneralDetailsRepository);
    }
}
