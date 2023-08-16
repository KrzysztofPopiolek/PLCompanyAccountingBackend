package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.*;

public class SummaryService {

    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;
    private final OtherPurchaseCostsEventService otherPurchaseCostsEventService;
    private final PurchaseGoodsServicesEventService purchaseGoodsServicesEventService;
    private final ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService;

    public SummaryService(ExpenseEventService expenseEventService,
                          IncomeEventService incomeEventService,
                          OtherPurchaseCostsEventService otherPurchaseCostsEventService,
                          PurchaseGoodsServicesEventService purchaseGoodsServicesEventService,
                          ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService) {
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
        this.otherPurchaseCostsEventService = otherPurchaseCostsEventService;
        this.purchaseGoodsServicesEventService = purchaseGoodsServicesEventService;
        this.researchDevelopmentActivitiesCostsEventService = researchDevelopmentActivitiesCostsEventService;
    }

    public Summary createNewSummary(BusinessEvent businessEvent, Summary summary, Boolean deleteMode) {
        if (businessEvent instanceof ExpenseEvent) {
            return expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof IncomeEvent) {
            return incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof OtherPurchaseCostsEvent) {
            return otherPurchaseCostsEventService.createEntryForSummary((OtherPurchaseCostsEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof PurchaseGoodsServicesEvent) {
            return purchaseGoodsServicesEventService.createEntryForSummary((PurchaseGoodsServicesEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof ResearchDevelopmentActivitiesCostsEvent) {
            return researchDevelopmentActivitiesCostsEventService.createEntryForSummary((ResearchDevelopmentActivitiesCostsEvent) businessEvent, summary, deleteMode);
        }
        throw new RuntimeException("Unhandled object instance in SummaryService.");
    }
}
