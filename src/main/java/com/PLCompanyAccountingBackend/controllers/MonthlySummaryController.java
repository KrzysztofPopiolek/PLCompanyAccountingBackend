package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class MonthlySummaryController {

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

    @GetMapping("/allMonthlySummary/{date}")
    public List<Summary> getAllMonthlySummary(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<? extends Summary> monthlySummaries = monthlySummaryRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
        List<Summary> foundMonthlySummaries = new ArrayList<>();
        for (int i = 0; i < monthlySummaries.size(); i++) {
            if (monthlySummaries.get(i).getDate().isEqual(date)) {
                for (int j = i; j < i + 12; j++) {
                    foundMonthlySummaries.add(monthlySummaries.get(j));
                }
                return foundMonthlySummaries;
            }
        }
        throw new ResourceNotFoundException("Date not found");
    }

}
