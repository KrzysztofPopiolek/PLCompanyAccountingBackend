package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.*;
import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseEventService implements EventServiceInterface {

    private final ExpenseEventRepository expenseEventRepository;

    private final AnnualSummaryService annualSummaryService;

    private final MonthlySummaryService monthlySummaryService;

    public ExpenseEventService(ExpenseEventRepository expenseEventRepository,
                               AnnualSummaryService annualSummaryService,
                               MonthlySummaryService monthlySummaryService){
        this.expenseEventRepository = expenseEventRepository;
        this.annualSummaryService = annualSummaryService;
        this.monthlySummaryService = monthlySummaryService;
    }

    public List<ExpenseEvent> getAllExpensesEvents_SortedByDate(){
        return expenseEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public ExpenseEvent getExpenseEvent_ById(Long id){
        return expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    public Summary addEntryToSummary(ExpenseEvent expenseEvent, Summary summary) {
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

    public void deleteEntryFromSummary(ExpenseEvent expenseEvent) {
        ExpenseEvent expenseEventForSummary = new ExpenseEvent(expenseEvent);
        BigDecimal expenseRemuneration = expenseEvent.getRemuneration() == null ? new BigDecimal(0) : expenseEvent.getRemuneration().negate();
        BigDecimal expenseOtherExpenses = expenseEvent.getOtherExpenses() == null ? new BigDecimal(0) : expenseEvent.getOtherExpenses().negate();
        BigDecimal expenseFinancialEconomicIssues = expenseEvent.getFinancialEconomicIssues() == null ? new BigDecimal(0) : expenseEvent.getFinancialEconomicIssues().negate();

        expenseEventForSummary.setRemuneration(expenseRemuneration);
        expenseEventForSummary.setOtherExpenses(expenseOtherExpenses);
        expenseEventForSummary.setFinancialEconomicIssues(expenseFinancialEconomicIssues);
        expenseEventForSummary.setTotalExpenses((expenseRemuneration.add(expenseOtherExpenses)));

        this.annualSummaryService.updateAnnualSummary(expenseEventForSummary);
        this.monthlySummaryService.updateMonthlySummary(expenseEventForSummary);
    }

}
