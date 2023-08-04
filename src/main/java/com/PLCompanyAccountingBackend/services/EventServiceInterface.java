package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.Summary;

public interface EventServiceInterface {
    public Summary addEntryToSummary(ExpenseEvent event, Summary summary);

    public void deleteEntryFromSummary(ExpenseEvent expenseEvent);

    }
