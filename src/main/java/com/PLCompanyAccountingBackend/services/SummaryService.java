package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.*;

public class SummaryService {

    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;
    private final OtherPurchaseCostsEventService otherPurchaseCostsEventService;

    public SummaryService(ExpenseEventService expenseEventService,
                          IncomeEventService incomeEventService,
                          OtherPurchaseCostsEventService otherPurchaseCostsEventService) {
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
        this.otherPurchaseCostsEventService = otherPurchaseCostsEventService;
    }

    public Summary createNewSummary(BusinessEvent businessEvent, Summary summary, Boolean deleteMode) {
        if (businessEvent instanceof ExpenseEvent) {
            return expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof IncomeEvent) {
            return incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof OtherPurchaseCostsEvent) {
            return otherPurchaseCostsEventService.createEntryForSummary((OtherPurchaseCostsEvent) businessEvent, summary, deleteMode);
        } // etc:
        throw new RuntimeException("Unhandled object instance in SummaryService.");
    }
}
