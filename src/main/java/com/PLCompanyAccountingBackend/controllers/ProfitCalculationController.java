package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import com.PLCompanyAccountingBackend.services.ProfitCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class ProfitCalculationController {
    private final ProfitCalculationService profitCalculationService;

    public ProfitCalculationController(ProfitCalculationService profitCalculationService) {
        this.profitCalculationService = profitCalculationService;
    }

    @GetMapping("/getAllProfitCalculation")
    public List<ProfitCalculation> getAllProfitCalculation() {
        return profitCalculationService.getAllProfitCalculation_SortedById();
    }

    @GetMapping("/getProfitCalculation/{id}")
    public ResponseEntity<ProfitCalculation> getProfitCalculationById(@PathVariable Long id) {
        return ResponseEntity.ok(profitCalculationService.getProfitCalculation_ById(id));
    }

//    @GetMapping("/getProfitCalculation/{dateProfitCalculation}")
//    public ResponseEntity<ProfitCalculation> getProfitCalculation_ByInventoryId(@PathVariable Date dateProfitCalculation) {
//        return ResponseEntity.ok(profitCalculationService.getProfitCalculation_ByDateProfitCalculation(dateProfitCalculation));
//    }

}
