package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class MonthlySummaryController {

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

    @GetMapping("/allMonthlySummary")
    public List<MonthlySummary> getAllMonthlySummary() {
        return monthlySummaryRepository.findAll();
    }


}
