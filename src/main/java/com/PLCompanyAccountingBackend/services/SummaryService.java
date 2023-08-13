package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.Summary;

public class SummaryService {

    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;

    public SummaryService(ExpenseEventService expenseEventService,
                          IncomeEventService incomeEventService) {
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
    }

    public Summary createNewSummary(BusinessEvent businessEvent, Summary summary, Boolean deleteMode) {
        if (businessEvent instanceof ExpenseEvent) {
            return expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof IncomeEvent) {
            return incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, summary, deleteMode);
        }
        throw new RuntimeException("Invalid object instance.");
    }
}
