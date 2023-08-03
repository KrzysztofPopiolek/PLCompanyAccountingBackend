package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceAlreadyExistsException;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class MonthlyAnnualSummaryController {

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

    @PostMapping("/addMonthsAndYearToSummaries/{date}")
    public AnnualSummary addMonthsAndYearToSummaries(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        System.out.println("HERE2");
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();
        System.out.println("HERE4");
        for (int i = 0; i < annualSummaries.size(); i++) {
            if (annualSummaries.get(i).getDate().compareTo(date)==0) {
                throw new ResourceAlreadyExistsException("Date exist");
            }
        }
        System.out.println("HERE3");
        addMonthsToSummary(date);
        AnnualSummary newAnnualSummary = new AnnualSummary();
        newAnnualSummary.setDate(date);
        System.out.println("HERE");
        return annualSummaryRepository.save(newAnnualSummary);
    }

    private void addMonthsToSummary(Date date) {
        for (int i = 0; i < 12; i++) {
            MonthlySummary newMonthlySummary = new MonthlySummary();
            newMonthlySummary.setDate(date);
            System.out.println("HERE1");
            monthlySummaryRepository.save(newMonthlySummary);
            date = DateUtils.addMonths(date, 1);
        }
    }

}
