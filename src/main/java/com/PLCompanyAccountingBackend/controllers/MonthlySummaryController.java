package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class MonthlySummaryController {

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

    @GetMapping("/allMonthlySummary/{date}")
    public List<MonthlySummary> getAllMonthlySummary(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<MonthlySummary> monthlySummaries = monthlySummaryRepository.findAll();
        List<MonthlySummary> foundMonthlySummaries = new ArrayList<>();
        for (int i = 0; i < monthlySummaries.size(); i++) {
            if (monthlySummaries.get(i).getMonthYear().compareTo(date) == 0) {
                for (int j = i; j < i + 12; j++) {
                    foundMonthlySummaries.add(monthlySummaries.get(j));
                }
                return foundMonthlySummaries;
            }
        }
        throw new ResourceNotFoundException("Date not found");
    }


}
