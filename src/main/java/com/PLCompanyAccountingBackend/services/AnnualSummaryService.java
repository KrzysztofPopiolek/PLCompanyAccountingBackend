package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;

import java.util.List;

public class AnnualSummaryService {

    private final AnnualSummaryRepository annualSummaryRepository;
    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;
    private final BusinessContractorService businessContractorService;

    public AnnualSummaryService(AnnualSummaryRepository annualSummaryRepository,
                                ExpenseEventService expenseEventService,
                                IncomeEventService incomeEventService,
                                BusinessContractorService businessContractorService) {
        this.annualSummaryRepository = annualSummaryRepository;
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
        this.businessContractorService = businessContractorService;
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
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();

        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (eventYear == annualSummariesYear) {
                AnnualSummary newAnnualSummary = new AnnualSummary();
                if (businessEvent instanceof ExpenseEvent) {
                    newAnnualSummary = (AnnualSummary) expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, annualSummary, deleteMode);
                }
                if (businessEvent instanceof IncomeEvent) {
                    newAnnualSummary = (AnnualSummary) incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, annualSummary, deleteMode);
                }
                annualSummaryRepository.save(newAnnualSummary);
            }
        }
    }

    /**
     * Checks if a tax year exists in the database.
     *
     * @param year the year to check.
     * @return a boolean value, true if the tax year exists false otherwise.
     */
    private boolean taxYearExists(int year) {
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (year == annualSummariesYear) {
                return true;
            }
        }
        return false;
    }

    public void checkContractorTaxYearExists(BusinessEvent businessEvent) {
        boolean taxYearExist = taxYearExists(businessEvent.getDateEconomicEvent().getYear());
        boolean contractorExists = businessContractorService.checkIfContractorExists(businessEvent.getBusinessContractor().getId());

        if (!taxYearExist) {
            throw new ResourceNotFoundException("Tax year does not exist");
        } else if (!contractorExists) {
            throw new ResourceNotFoundException("Contractor not found");
        }
    }
}
