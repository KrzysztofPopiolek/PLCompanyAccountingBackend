package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;

import java.util.List;

public class MonthlySummaryService {

    private final MonthlySummaryRepository monthlySummaryRepository;
    private final SummaryService summaryService;

    public MonthlySummaryService(MonthlySummaryRepository monthlySummaryRepository,
                                 SummaryService summaryService) {
        this.monthlySummaryRepository = monthlySummaryRepository;
        this.summaryService = summaryService;
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

        List<MonthlySummary> summaries = monthlySummaryRepository.findAll();

        for (Summary summary : summaries) {
            int monthlySummariesYear = summary.getDate().getYear();
            int monthlySummariesMonth = summary.getDate().getMonthValue();
            if (expenseEventYear == monthlySummariesYear && expenseEventMonth == monthlySummariesMonth) {
                MonthlySummary newSummary = (MonthlySummary) summaryService.createNewSummary(businessEvent, summary, deleteMode);
                monthlySummaryRepository.save(newSummary);
            }
        }
    }
}
