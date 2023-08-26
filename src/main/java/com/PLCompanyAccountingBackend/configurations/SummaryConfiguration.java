package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import com.PLCompanyAccountingBackend.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SummaryConfiguration {

    @Bean
    public SummaryService SummaryService(ExpenseEventService expenseEventService,
                                         IncomeEventService incomeEventService,
                                         OtherPurchaseCostsEventService otherPurchaseCostsEventService,
                                         PurchaseGoodsServicesEventService purchaseGoodsServicesEventService,
                                         ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService,
                                         AnnualSummaryRepository annualSummaryRepository,
                                         MonthlySummaryRepository monthlySummaryRepository) {
        return new SummaryService(expenseEventService, incomeEventService, otherPurchaseCostsEventService,
                purchaseGoodsServicesEventService, researchDevelopmentActivitiesCostsEventService,
                annualSummaryRepository, monthlySummaryRepository);
    }
}