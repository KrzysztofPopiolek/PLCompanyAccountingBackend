package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import com.PLCompanyAccountingBackend.repository.ProfitCalculationRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ProfitCalculationService {

    private final ProfitCalculationRepository profitCalculationRepository;


    public ProfitCalculationService(ProfitCalculationRepository profitCalculationRepository) {
        this.profitCalculationRepository = profitCalculationRepository;
    }

    public List<ProfitCalculation> getAllProfitCalculation_SortedById() {
        return profitCalculationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public ProfitCalculation getProfitCalculation_ById(Long id) {
        return profitCalculationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched calculation not found!"));
    }

//    public ProfitCalculation getProfitCalculation_ByDateProfitCalculation(Date dateProfitCalculation) {
//        return profitCalculationRepository.findAll(dateProfitCalculation.getTime()).orElseThrow(() -> new ResourceNotFoundException("Searched calculation date not found!"));
//    }

    // ==========================================================================

//    private final InventoryGeneralDetails inventoryGeneralDetails;


    public void profitCalculationSummaryService() {


    }

}
