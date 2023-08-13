package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;

import java.util.List;

public class MonthlySummaryService {

    private final MonthlySummaryRepository monthlySummaryRepository;

    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;

    public MonthlySummaryService(MonthlySummaryRepository monthlySummaryRepository,
                                 ExpenseEventService expenseEventService,
                                 IncomeEventService incomeEventService) {
        this.monthlySummaryRepository = monthlySummaryRepository;
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
    }

    /**
     * Updates the monthly summary table in the DB with the businessEvent info.
     *
     * @param businessEvent the businessEvent that was added to one of the other tables.
     * @param deleteMode    the action we are performing, if true we delete an entry from summary, otherwise we add the
     *                      entry.
     */
    public void updateMonthlySummary(BusinessEvent businessEvent, boolean deleteMode) {
        int expenseEventYear = businessEvent.getDateEconomicEvent().getYear();
        int expenseEventMonth = businessEvent.getDateEconomicEvent().getMonthValue();

        List<MonthlySummary> monthlySummaries = monthlySummaryRepository.findAll();

        for (MonthlySummary monthlySummary : monthlySummaries) {
            int monthlySummariesYear = monthlySummary.getDate().getYear();
            int monthlySummariesMonth = monthlySummary.getDate().getMonthValue();
            if (expenseEventYear == monthlySummariesYear && expenseEventMonth == monthlySummariesMonth) {
                MonthlySummary newMonthlySummary = new MonthlySummary();
                if (businessEvent instanceof ExpenseEvent) {
                    newMonthlySummary = (MonthlySummary) expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, monthlySummary, deleteMode);
                }
                if (businessEvent instanceof IncomeEvent) {
                    newMonthlySummary = (MonthlySummary) incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, monthlySummary, deleteMode);
                }
                monthlySummaryRepository.save(newMonthlySummary);
            }//etc.
        }
    }
}
