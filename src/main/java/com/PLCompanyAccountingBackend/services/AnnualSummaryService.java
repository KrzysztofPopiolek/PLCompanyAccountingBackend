package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;

import java.util.List;

public class AnnualSummaryService {

    private final AnnualSummaryRepository annualSummaryRepository;
    private final SummaryService summaryService;

    public AnnualSummaryService(AnnualSummaryRepository annualSummaryRepository,
                                SummaryService summaryService) {
        this.annualSummaryRepository = annualSummaryRepository;
        this.summaryService = summaryService;
    }

    /**
     * Updates the annual summary table in the DB with the businessEvent info.
     *
     * @param businessEvent the businessEvent that was added to one of the other tables.
     * @param deleteMode    the action we are performing, if true we delete an entry from summary, otherwise we add the
     *                      entry.
     */
    public void updateAnnualSummary(BusinessEvent businessEvent, boolean deleteMode) {
        int eventYear = businessEvent.getDateEconomicEvent().getYear();
        List<AnnualSummary> summaries = annualSummaryRepository.findAll();
        for (Summary summary : summaries) {
            int annualSummariesYear = summary.getDate().getYear();
            if (eventYear == annualSummariesYear) {
                AnnualSummary newSummary = (AnnualSummary) summaryService.createNewSummary(businessEvent, summary, deleteMode);
                annualSummaryRepository.save(newSummary);
            }
        }
    }

    /**
     * Checks if a tax year exists in the database.
     *
     * @param year the year to check.
     * @return a boolean value, true if the tax year exists false otherwise.
     */
    public boolean taxYearExists(int year) {
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        for (Summary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (year == annualSummariesYear) {
                return true;
            }
        }
        return false;
    }

    public AnnualSummary getLastAnnualSummary() {
        List<AnnualSummary> allAnnualSummaries = annualSummaryRepository.findAll();
        return allAnnualSummaries.get(allAnnualSummaries.size() - 1);
    }
}
