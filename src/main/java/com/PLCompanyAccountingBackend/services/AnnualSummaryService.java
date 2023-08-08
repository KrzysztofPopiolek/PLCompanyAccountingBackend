package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.Event;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;

import java.util.List;

public class AnnualSummaryService {

    private final AnnualSummaryRepository annualSummaryRepository;

    private final ExpenseEventService expenseEventService;

    public AnnualSummaryService(AnnualSummaryRepository annualSummaryRepository,
                                ExpenseEventService expenseEventService) {
        this.annualSummaryRepository = annualSummaryRepository;
        this.expenseEventService = expenseEventService;
    }

    public void updateAnnualSummary(Event event, boolean isDeleteMode) {
        int eventYear = event.getDateEconomicEvent().getYear();
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();

        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (eventYear == annualSummariesYear) {
                AnnualSummary newAnnualSummary = new AnnualSummary();
                if (event instanceof ExpenseEvent) {
                    if (isDeleteMode) {
                        newAnnualSummary = (AnnualSummary) expenseEventService.createDeleteEntryForSummary((ExpenseEvent) event, annualSummary);
                    } else {
                        newAnnualSummary = (AnnualSummary) expenseEventService.createAddEntryForSummary((ExpenseEvent) event, annualSummary);
                    }
                } //etc.
                annualSummaryRepository.save(newAnnualSummary);
            }
        }
    }

    public boolean taxYearExists(int year) {
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (year == annualSummariesYear) {
                return true;
            }
        }
        return false;
    }
}
