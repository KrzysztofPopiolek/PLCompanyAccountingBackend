package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.ProfitCalculationRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import com.PLCompanyAccountingBackend.services.ProfitCalculationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan

public class ProfitCalculationConfiguration {
    @Bean
    public ProfitCalculationService profitCalculationService(ProfitCalculationRepository profitCalculationRepository,
                                                             InventoryGeneralDetailsService inventoryGeneralDetailsService,
                                                             AnnualSummaryService annualSummaryService) {
        return new ProfitCalculationService(profitCalculationRepository, inventoryGeneralDetailsService, annualSummaryService);
    }
}
