package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesEventRepository;
import com.PLCompanyAccountingBackend.services.PurchaseGoodsServicesEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class PurchaseGoodsServicesEventConfiguration {
    @Bean
    public PurchaseGoodsServicesEventService purchaseGoodsServicesEventService(PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository) {
        return new PurchaseGoodsServicesEventService(purchaseGoodsServicesEventRepository);
    }
}