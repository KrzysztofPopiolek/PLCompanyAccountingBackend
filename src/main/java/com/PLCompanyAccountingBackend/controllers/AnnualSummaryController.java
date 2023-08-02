package com.PLCompanyAccountingBackend.controllers;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class AnnualSummaryController {

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @GetMapping("/allAnnualSummary")
    public List<AnnualSummary> getAnnualSummary() {
        return annualSummaryRepository.findAll();
    }
}
