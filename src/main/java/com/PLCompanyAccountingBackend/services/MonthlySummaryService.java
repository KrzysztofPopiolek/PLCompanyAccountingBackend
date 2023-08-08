package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.Event;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;

import java.util.List;

public class MonthlySummaryService {

    private final MonthlySummaryRepository monthlySummaryRepository;

    private final ExpenseEventService expenseEventService;

    public MonthlySummaryService(MonthlySummaryRepository monthlySummaryRepository,
                                ExpenseEventService expenseEventService) {
        this.monthlySummaryRepository = monthlySummaryRepository;
        this.expenseEventService = expenseEventService;
    }

    public void updateMonthlySummary(Event event, boolean isDeleteMode) {
        int expenseEventYear = event.getDateEconomicEvent().getYear();
        int expenseEventMonth = event.getDateEconomicEvent().getMonthValue();

        List<MonthlySummary> monthlySummaries = monthlySummaryRepository.findAll();

        for (MonthlySummary monthlySummary : monthlySummaries) {
            int monthlySummariesYear = monthlySummary.getDate().getYear();
            int monthlySummariesMonth = monthlySummary.getDate().getMonthValue();
            if (expenseEventYear == monthlySummariesYear && expenseEventMonth == monthlySummariesMonth) {
                MonthlySummary newMonthlySummary = new MonthlySummary();
                if(event instanceof ExpenseEvent){
                    if(isDeleteMode){
                        newMonthlySummary = (MonthlySummary) this.expenseEventService.createDeleteEntryForSummary((ExpenseEvent) event, monthlySummary);
                    }else{
                        newMonthlySummary = (MonthlySummary) this.expenseEventService.createAddEntryForSummary((ExpenseEvent) event, monthlySummary);
                    }
                } //etc.
                monthlySummaryRepository.save(newMonthlySummary);
            }
        }
    }
}
