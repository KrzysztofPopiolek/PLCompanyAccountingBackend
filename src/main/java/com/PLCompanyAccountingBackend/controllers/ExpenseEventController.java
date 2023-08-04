package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/v_1/")
public class ExpenseEventController {
    @Autowired
    private ExpenseEventRepository expenseEventRepository;

    @Autowired
    private AnnualSummaryRepository annualSummaryRepository;

    @Autowired
    private MonthlySummaryRepository monthlySummaryRepository;

    @Autowired
    private BusinessContractorRepository businessContractorRepository;

    @GetMapping("/getAllExpense&Event")
    public List<ExpenseEvent> getAllExpenseEvent() {
        return expenseEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    @GetMapping("/getExpense&Event/{id}")
    public ResponseEntity<ExpenseEvent> getExpenseEventById(@PathVariable Long id) {
        ExpenseEvent expenseEvent = expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(expenseEvent);
    }

    @PostMapping("/addExpense&Event")
    public ExpenseEvent addBusinessExpense(@RequestBody ExpenseEvent expenseEvent) {
        boolean taxYearExists;

        if (!businessContractorRepository.existsById(expenseEvent.getBusinessContractor().getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }

        BigDecimal expenseRemuneration = expenseEvent.getRemuneration() == null ? new BigDecimal(0) : expenseEvent.getRemuneration();
        BigDecimal expenseOtherExpenses = expenseEvent.getOtherExpenses() == null ? new BigDecimal(0) : expenseEvent.getOtherExpenses();

        expenseEvent.setTotalExpenses(expenseRemuneration.add(expenseOtherExpenses));
        taxYearExists = updateAnnualSummary(expenseEvent);

        if (!taxYearExists) {
            throw new ResourceNotFoundException("Tax year does not exist");
        }

        updateMonthlySummary(expenseEvent);

        return expenseEventRepository.save(expenseEvent);
    }

    @DeleteMapping("/deleteExpense&Event/{id}")
    public void deleteExpenseEvent(@PathVariable Long id) {
        ExpenseEvent expenseEvent = expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with provided ID does not exist."));

        deleteEntryFromSummary(expenseEvent);
        expenseEventRepository.deleteById(id);
    }

    @PutMapping("/editExpense&Event/{id}")
    ExpenseEvent editExpenseEvent(@RequestBody ExpenseEvent newExpenseEvent, @PathVariable Long id) {

        return expenseEventRepository.findById(id).map(expenseEvent -> {
            if (!businessContractorRepository.existsById(newExpenseEvent.getBusinessContractor().getId())) {
                throw new ResourceNotFoundException("Contractor not found");
            }

            deleteEntryFromSummary(expenseEvent);

            newExpenseEvent.setTotalExpenses(newExpenseEvent.getRemuneration().add(newExpenseEvent.getOtherExpenses()));

            updateAnnualSummary(newExpenseEvent);
            updateMonthlySummary(newExpenseEvent);

            expenseEvent.setDateEconomicEvent(newExpenseEvent.getDateEconomicEvent());
            expenseEvent.setAccountingDocumentNumber(newExpenseEvent.getAccountingDocumentNumber());
            expenseEvent.setDescriptionEconomicEvent(newExpenseEvent.getDescriptionEconomicEvent());
            expenseEvent.setRemuneration(newExpenseEvent.getRemuneration());
            expenseEvent.setOtherExpenses(newExpenseEvent.getOtherExpenses());
            expenseEvent.setFinancialEconomicIssues(newExpenseEvent.getFinancialEconomicIssues());
            expenseEvent.setTotalExpenses(newExpenseEvent.getTotalExpenses());
            expenseEvent.setEventNotesComments(newExpenseEvent.getEventNotesComments());
            expenseEvent.setBusinessContractor((newExpenseEvent.getBusinessContractor()));
            return expenseEventRepository.save(expenseEvent);
        }).orElseThrow(() -> new ResourceNotFoundException("Expenses and event not found!"));
    }

    private void updateMonthlySummary(ExpenseEvent expenseEvent) {
        int expenseEventYear = expenseEvent.getDateEconomicEvent().getYear();
        int expenseEventMonth = expenseEvent.getDateEconomicEvent().getMonthValue();

        List<MonthlySummary> monthlySummaries = monthlySummaryRepository.findAll();

        for (MonthlySummary monthlySummary : monthlySummaries) {
            int monthlySummariesYear = monthlySummary.getDate().getYear();
            int monthlySummariesMonth = monthlySummary.getDate().getMonthValue();
            if (expenseEventYear == monthlySummariesYear && expenseEventMonth == monthlySummariesMonth) {
                MonthlySummary newMonthlySummary = (MonthlySummary) addEntryToSummary(expenseEvent, monthlySummary);
                monthlySummaryRepository.save(newMonthlySummary);
            }
        }
    }

    private boolean updateAnnualSummary(ExpenseEvent expenseEvent) {
        int expenseEventYear = expenseEvent.getDateEconomicEvent().getYear();
        List<AnnualSummary> annualSummaries = annualSummaryRepository.findAll();

        for (AnnualSummary annualSummary : annualSummaries) {
            int annualSummariesYear = annualSummary.getDate().getYear();
            if (expenseEventYear == annualSummariesYear) {
                AnnualSummary newAnnualSummary = (AnnualSummary) addEntryToSummary(expenseEvent, annualSummary);
                annualSummaryRepository.save(newAnnualSummary);
                return true;
            }
        }
        return false;
    }

    private Summary addEntryToSummary(ExpenseEvent expenseEvent, Summary summary) {
        BigDecimal remuneration = summary.getRemuneration() == null ? new BigDecimal(0) : summary.getRemuneration();
        BigDecimal otherExpenses = summary.getOtherExpenses() == null ? new BigDecimal(0) : summary.getOtherExpenses();
        BigDecimal totalExpenses = summary.getTotalExpenses() == null ? new BigDecimal(0) : summary.getTotalExpenses();
        BigDecimal financialEconomicIssues = summary.getFinancialEconomicIssues() == null ? new BigDecimal(0) : summary.getFinancialEconomicIssues();

        summary.setRemuneration(remuneration.add(expenseEvent.getRemuneration()));
        summary.setOtherExpenses(otherExpenses.add(expenseEvent.getOtherExpenses()));
        summary.setTotalExpenses(totalExpenses.add(expenseEvent.getTotalExpenses()));
        summary.setFinancialEconomicIssues(financialEconomicIssues.add(expenseEvent.getFinancialEconomicIssues()));
        return summary;
    }

    private void deleteEntryFromSummary(ExpenseEvent expenseEvent) {
        ExpenseEvent expenseEventForSummary = new ExpenseEvent(expenseEvent);
        BigDecimal expenseRemuneration = expenseEvent.getRemuneration() == null ? new BigDecimal(0) : expenseEvent.getRemuneration().negate();
        BigDecimal expenseOtherExpenses = expenseEvent.getOtherExpenses() == null ? new BigDecimal(0) : expenseEvent.getOtherExpenses().negate();
        BigDecimal expenseFinancialEconomicIssues = expenseEvent.getFinancialEconomicIssues() == null ? new BigDecimal(0) : expenseEvent.getFinancialEconomicIssues().negate();

        expenseEventForSummary.setRemuneration(expenseRemuneration);
        expenseEventForSummary.setOtherExpenses(expenseOtherExpenses);
        expenseEventForSummary.setFinancialEconomicIssues(expenseFinancialEconomicIssues);
        expenseEventForSummary.setTotalExpenses((expenseRemuneration.add(expenseOtherExpenses)));

        updateAnnualSummary(expenseEventForSummary);
        updateMonthlySummary(expenseEventForSummary);
    }
}
