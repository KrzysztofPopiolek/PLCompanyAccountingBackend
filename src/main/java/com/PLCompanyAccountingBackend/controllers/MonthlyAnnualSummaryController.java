package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v_1/")
public class MonthlyAnnualSummaryController {

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

//    public Summary addMonthsAndYearToSummaries(LocalDate date) {
//        List<? extends Summary> annualSummaries = annualSummaryRepository.findAll();
//        for (Summary annualSummary : annualSummaries) {
//            if (annualSummary.getDate().isEqual(date)) {
//                throw new ResourceAlreadyExistsException("Date exist");
//            }
//        }
//        addMonthsToSummary(date);
//        AnnualSummary newAnnualSummary = new AnnualSummary();
//        newAnnualSummary.setDate(date);
//        return annualSummaryRepository.save(newAnnualSummary);
//    }
//
//    private void addMonthsToSummary(LocalDate date) {
//        for (int i = 0; i < 12; i++) {
//            MonthlySummary newMonthlySummary = new MonthlySummary();
//            newMonthlySummary.setDate(date);
//            monthlySummaryRepository.save(newMonthlySummary);
//            date = date.plusMonths(1);
//        }
//    }
}
