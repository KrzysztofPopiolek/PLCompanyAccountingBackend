package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class AnnualSummaryController {

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @GetMapping("/allAnnualSummary/{date}")
    public Summary getAnnualSummary(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<? extends Summary> annualSummaries = annualSummaryRepository.findAll();
        for (Summary annualSummary : annualSummaries) {
            if (annualSummary.getDate().isEqual(date)) {
                return annualSummary;
            }
        }
        throw new ResourceNotFoundException("Year not found");
    }
}
