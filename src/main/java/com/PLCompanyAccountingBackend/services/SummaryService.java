package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.*;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;

import java.time.LocalDate;
import java.util.List;

public class SummaryService {

    private final ExpenseEventService expenseEventService;
    private final IncomeEventService incomeEventService;
    private final OtherPurchaseCostsEventService otherPurchaseCostsEventService;
    private final PurchaseGoodsServicesEventService purchaseGoodsServicesEventService;
    private final ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService;
    private final AnnualSummaryRepository annualSummaryRepository;
    private final MonthlySummaryRepository monthlySummaryRepository;

    public SummaryService(ExpenseEventService expenseEventService,
                          IncomeEventService incomeEventService,
                          OtherPurchaseCostsEventService otherPurchaseCostsEventService,
                          PurchaseGoodsServicesEventService purchaseGoodsServicesEventService,
                          ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService,
                          AnnualSummaryRepository annualSummaryRepository,
                          MonthlySummaryRepository monthlySummaryRepository) {
        this.expenseEventService = expenseEventService;
        this.incomeEventService = incomeEventService;
        this.otherPurchaseCostsEventService = otherPurchaseCostsEventService;
        this.purchaseGoodsServicesEventService = purchaseGoodsServicesEventService;
        this.researchDevelopmentActivitiesCostsEventService = researchDevelopmentActivitiesCostsEventService;
        this.annualSummaryRepository = annualSummaryRepository;
        this.monthlySummaryRepository = monthlySummaryRepository;
    }

    public Summary createNewSummary(BusinessEvent businessEvent, Summary summary, Boolean deleteMode) {
        if (businessEvent instanceof ExpenseEvent) {
            return expenseEventService.createEntryForSummary((ExpenseEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof IncomeEvent) {
            return incomeEventService.createEntryForSummary((IncomeEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof OtherPurchaseCostsEvent) {
            return otherPurchaseCostsEventService.createEntryForSummary((OtherPurchaseCostsEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof PurchaseGoodsServicesEvent) {
            return purchaseGoodsServicesEventService.createEntryForSummary((PurchaseGoodsServicesEvent) businessEvent, summary, deleteMode);
        } else if (businessEvent instanceof ResearchDevelopmentActivitiesCostsEvent) {
            return researchDevelopmentActivitiesCostsEventService.createEntryForSummary((ResearchDevelopmentActivitiesCostsEvent) businessEvent, summary, deleteMode);
        }
        throw new RuntimeException("Unhandled object instance in SummaryService.");
    }

    public AnnualSummary addMonthsAndYearToSummaries(LocalDate date) {
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        for (AnnualSummary annualSummary : annualSummaries) {
            if (annualSummary.getDate().isEqual(date)) {
                // wpisać log z informacja że rok istnieje
                return annualSummary;
            }
        }
        addMonthsToSummary(date);
        AnnualSummary newAnnualSummary = AnnualSummary.builder().date(date).build();
        return annualSummaryRepository.save(newAnnualSummary);
    }

    private void addMonthsToSummary(LocalDate date) {
        for (int i = 0; i < 12; i++) {
            MonthlySummary newMonthlySummary = new MonthlySummary();
            newMonthlySummary.setDate(date);
            monthlySummaryRepository.save(newMonthlySummary);
            date = date.plusMonths(1);
        }
    }
}
