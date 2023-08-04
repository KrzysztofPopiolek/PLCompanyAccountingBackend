package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.*;
import com.PLCompanyAccountingBackend.repository.*;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.ExpenseEventService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/v_1/")
public class ExpenseEventController {
    @Autowired
    private ExpenseEventRepository expenseEventRepository;

    private final BusinessContractorService businessContractorService;

    private final ExpenseEventService expenseEventService;

    private final MonthlySummaryService monthlySummaryService;

    private final AnnualSummaryService annualSummaryService;

    public ExpenseEventController(BusinessContractorService businessContractorService,
                                  ExpenseEventService expenseEventService,
                                  MonthlySummaryService monthlySummaryService,
                                  AnnualSummaryService annualSummaryService){
        this.businessContractorService = businessContractorService;
        this.expenseEventService = expenseEventService;
        this.monthlySummaryService = monthlySummaryService;
        this.annualSummaryService = annualSummaryService;
    }

    @GetMapping("/getAllExpense&Event")
    public List<ExpenseEvent> getAllExpenseEvent() {
        return expenseEventService.getAllExpensesEvents_SortedByDate();
    }

    @GetMapping("/getExpense&Event/{id}")
    public ResponseEntity<ExpenseEvent> getExpenseEventById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseEventService.getExpenseEvent_ById(id));
    }

    @PostMapping("/addExpense&Event")
    public ExpenseEvent addBusinessExpense(@RequestBody ExpenseEvent expenseEvent) {
        boolean taxYearExists;

        boolean contractorExists = businessContractorService.checkIfContractorExists(expenseEvent.getBusinessContractor().getId());
        if(!contractorExists){
            throw new ResourceNotFoundException("Contractor not found");
        }

        BigDecimal expenseRemuneration = expenseEvent.getRemuneration() == null ? new BigDecimal(0) : expenseEvent.getRemuneration();
        BigDecimal expenseOtherExpenses = expenseEvent.getOtherExpenses() == null ? new BigDecimal(0) : expenseEvent.getOtherExpenses();

        expenseEvent.setTotalExpenses(expenseRemuneration.add(expenseOtherExpenses));
        taxYearExists = this.annualSummaryService.updateAnnualSummary(expenseEvent);

        if (!taxYearExists) {
            throw new ResourceNotFoundException("Tax year does not exist");
        }

        this.monthlySummaryService.updateMonthlySummary(expenseEvent);

        return expenseEventRepository.save(expenseEvent);
    }

    @DeleteMapping("/deleteExpense&Event/{id}")
    public void deleteExpenseEvent(@PathVariable Long id) {
        ExpenseEvent expenseEvent = expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with provided ID does not exist."));

        this.expenseEventService.deleteEntryFromSummary(expenseEvent);
        expenseEventRepository.deleteById(id);
    }

    @PutMapping("/editExpense&Event/{id}")
    ExpenseEvent editExpenseEvent(@RequestBody ExpenseEvent newExpenseEvent, @PathVariable Long id) {

        return expenseEventRepository.findById(id).map(expenseEvent -> {
            boolean contractorExists = businessContractorService.checkIfContractorExists(expenseEvent.getBusinessContractor().getId());
            if(!contractorExists){
                throw new ResourceNotFoundException("Contractor not found");
            }

            this.expenseEventService.deleteEntryFromSummary(expenseEvent);

            newExpenseEvent.setTotalExpenses(newExpenseEvent.getRemuneration().add(newExpenseEvent.getOtherExpenses()));

            this.annualSummaryService.updateAnnualSummary(newExpenseEvent);
            this.monthlySummaryService.updateMonthlySummary(newExpenseEvent);

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
}
