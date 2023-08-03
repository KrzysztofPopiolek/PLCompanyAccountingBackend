package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class AnnualSummaryController {

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @GetMapping("/allAnnualSummary/{date}")
    public AnnualSummary getAnnualSummary(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        for (int i = 0; i < annualSummaries.size(); i++) {
            if (annualSummaries.get(i).getYear().compareTo(date) == 0) {
                return annualSummaries.get(i);
            }
        }
        throw new ResourceNotFoundException("Year not found");
    }
}
