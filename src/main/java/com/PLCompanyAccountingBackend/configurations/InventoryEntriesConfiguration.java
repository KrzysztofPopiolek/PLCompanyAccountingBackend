package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import com.PLCompanyAccountingBackend.services.InventoryEntriesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class InventoryEntriesConfiguration {
    @Bean
    public InventoryEntriesService inventoryEntriesService(InventoryEntriesRepository inventoryEntriesRepository){
        return new InventoryEntriesService(inventoryEntriesRepository);
    }
}
