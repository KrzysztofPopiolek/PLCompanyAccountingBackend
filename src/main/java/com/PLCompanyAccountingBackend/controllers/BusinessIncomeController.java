package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.BusinessIncome;
import com.PLCompanyAccountingBackend.repository.BusinessIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessIncomeController {

    @Autowired
    private BusinessIncomeRepository businessIncomeRepository;

    @GetMapping("/getAllBusinessIncome")
    public List<BusinessIncome> getAllBusinessIncome() {
        return businessIncomeRepository.findAll();
    }

    @GetMapping("/getBusinessIncome/{id}")
    public ResponseEntity<BusinessIncome> getBusinessIncomeById(@PathVariable Long id) {
        BusinessIncome businessIncome = businessIncomeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(businessIncome);
    }

    @PostMapping("/addBusinessIncome")
    public BusinessIncome addBusinessIncome(@RequestBody BusinessIncome businessIncome) {
        return businessIncomeRepository.save(businessIncome);
    }

    @DeleteMapping("/deleteBusinessIncome/{id}")
    public void deleteBusinessIncome(@PathVariable Long id) {
        businessIncomeRepository.deleteById(id);
    }

    @PutMapping("/editBusinessIncome/{id}")
    BusinessIncome editBusinessIncome(@RequestBody BusinessIncome newBusinessIncome, @PathVariable Long id) {
        return businessIncomeRepository.findById(id).map(
                businessIncome -> {
                    businessIncome.setSaleValue(newBusinessIncome.getSaleValue());
                    businessIncome.setOtherIncome(newBusinessIncome.getOtherIncome());
                    businessIncome.setTotalRevenue(newBusinessIncome.getTotalRevenue());
                    return businessIncomeRepository.save(businessIncome);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business income not found!"));
    }
}
