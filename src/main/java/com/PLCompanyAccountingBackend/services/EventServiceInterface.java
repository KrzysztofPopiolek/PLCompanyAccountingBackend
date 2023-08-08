package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.Summary;

public interface EventServiceInterface {
    public Summary createAddEntryForSummary(ExpenseEvent event, Summary summary);

    public Summary createDeleteEntryForSummary(ExpenseEvent expenseEvent, Summary summary);

}
