package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import com.PLCompanyAccountingBackend.repository.ProfitCalculationRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class ProfitCalculationService {

    private final ProfitCalculationRepository profitCalculationRepository;
    private final InventoryGeneralDetailsService inventoryGeneralDetailsService;
    private final AnnualSummaryService annualSummaryService;


    public ProfitCalculationService(ProfitCalculationRepository profitCalculationRepository,
                                    InventoryGeneralDetailsService inventoryGeneralDetailsService,
                                    AnnualSummaryService annualSummaryService) {
        this.profitCalculationRepository = profitCalculationRepository;
        this.inventoryGeneralDetailsService = inventoryGeneralDetailsService;
        this.annualSummaryService = annualSummaryService;
    }

    public List<ProfitCalculation> getAllProfitCalculation_SortedById() {
        return profitCalculationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public ProfitCalculation getProfitCalculation_ById(Long id) {
        return profitCalculationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched calculation not found!"));
    }

    public ProfitCalculation calculateProfit(InventoryGeneralDetails inventoryGeneralDetails) {

        BigDecimal costOfGettingIncome = new BigDecimal(0);
        ProfitCalculation profitCalculation = new ProfitCalculation();

        costOfGettingIncome = costOfGettingIncome.add(inventoryGeneralDetailsService.getLastInventoryGeneralDetails().getTotalInventory());
        costOfGettingIncome = costOfGettingIncome.add(annualSummaryService.getLastAnnualSummary().getPurchaseGoodsMaterialsCost());
        costOfGettingIncome = costOfGettingIncome.add(annualSummaryService.getLastAnnualSummary().getOtherPurchaseCosts());
        costOfGettingIncome = costOfGettingIncome.subtract(inventoryGeneralDetails.getTotalInventory());
        costOfGettingIncome = costOfGettingIncome.add(annualSummaryService.getLastAnnualSummary().getTotalExpenses());
        costOfGettingIncome = costOfGettingIncome.subtract(annualSummaryService.getLastAnnualSummary().getFinancialEconomicIssues());

        profitCalculation.setDateProfitCalculation(inventoryGeneralDetails.getInventoryDate());
        profitCalculation.setTotalDeductibleCosts(costOfGettingIncome);
        profitCalculation.setTotalProfit(annualSummaryService.getLastAnnualSummary().getTotalRevenue().subtract(costOfGettingIncome));

        return profitCalculation;
    }

    public void saveProfitCalculation(ProfitCalculation profitCalculation) {
        profitCalculationRepository.save(profitCalculation);
    }
}
