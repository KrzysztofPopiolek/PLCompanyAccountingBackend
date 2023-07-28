package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.BusinessIncome;
import com.PLCompanyAccountingBackend.repository.BusinesIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessIncomeController {

    @Autowired
    private BusinesIncomeRepository businesIncomeRepository;

    @GetMapping("/getAllBusinessIncome")
    public List<BusinessIncome> getAllBusinessIncome() {
        return businesIncomeRepository.findAll();
    }

    @GetMapping("/getBusinessIncome/{id}")
    public ResponseEntity<BusinessIncome> getBusinessIncomeById(@PathVariable Long id) {
        BusinessIncome businessIncome = businesIncomeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        return ResponseEntity.ok(businessIncome);
    }

    @PostMapping("/addBusinessIncome")
    public BusinessIncome addBusinessIncome(@RequestBody BusinessIncome businessIncome) {
        return businesIncomeRepository.save(businessIncome);
    }

    @DeleteMapping("/deleteBusinessIncome/{id}")
    public void deleteBusinessIncome(@PathVariable Long id) {
        businesIncomeRepository.deleteById(id);
    }

    @PutMapping("/editBusinessIncome/{id}")
    BusinessIncome editBusinessIncome(@RequestBody BusinessIncome newBusinessIncome, @PathVariable Long id) {
        return businesIncomeRepository.findById(id).map(
                businessIncome -> {
                    businessIncome.setSaleValue(newBusinessIncome.getSaleValue());
                    businessIncome.setOtherIncome(newBusinessIncome.getOtherIncome());
                    businessIncome.setTotalRevenue(newBusinessIncome.getTotalRevenue());
                    return businesIncomeRepository.save(businessIncome);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business income not found!"));
    }
}
