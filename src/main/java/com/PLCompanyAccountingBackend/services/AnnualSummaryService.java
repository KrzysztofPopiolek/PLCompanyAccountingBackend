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

    public boolean updateAnnualSummary(Event event) {
        int eventYear = event.getDateEconomicEvent().getYear();
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();

        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (eventYear == annualSummariesYear) {
                AnnualSummary newAnnualSummary = new AnnualSummary();
                if(event instanceof ExpenseEvent) {
                    newAnnualSummary = (AnnualSummary) expenseEventService.addEntryToSummary((ExpenseEvent) event, annualSummary);
                } //etc.
                annualSummaryRepository.save(newAnnualSummary);
                return true;
            }
        }
        return false;
    }

}
