package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.BusinessExpenses;
import com.PLCompanyAccountingBackend.repository.BusinessExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v_1/")
public class BusinessExpensesController {
    @Autowired
    private BusinessExpensesRepository businessExpensesRepository;

    @GetMapping("/getAllBusinessExpenses")
    public List<BusinessExpenses> getAllBusinessExpenses() {
        return businessExpensesRepository.findAll();
    }

    @GetMapping("/getBusinessExpense/{id}")
    public ResponseEntity<BusinessExpenses> getBusinessExpensesById(@PathVariable Long id) {
        BusinessExpenses businessExpenses = businessExpensesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(businessExpenses);
    }

    @PostMapping("/addBusinessExpenses")
    public BusinessExpenses addBusinessExpenses(@RequestBody BusinessExpenses businessExpenses) {
        return businessExpensesRepository.save(businessExpenses);
    }

    @DeleteMapping("/deleteBusinessExpenses/{id}")
    public void deleteBusinessExpenses(@PathVariable Long id) {
        businessExpensesRepository.deleteById(id);
    }
    @PutMapping("/editBusinessExpenses/{id}")
    BusinessExpenses editBusinessExpenses(@RequestBody BusinessExpenses newBusinessExpenses, @PathVariable Long id) {
        return businessExpensesRepository.findById(id).map(
                businessExpenses -> {
                    businessExpenses.setRemuneration(newBusinessExpenses.getRemuneration());
                    businessExpenses.setOtherExpenses(newBusinessExpenses.getOtherExpenses());
                    businessExpenses.setFinancialEconomicIssues(newBusinessExpenses.getFinancialEconomicIssues());
                    businessExpenses.setTotalExpenses(newBusinessExpenses.getTotalExpenses());
                    return businessExpensesRepository.save(businessExpenses);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business expenses not found!"));
    }
}
