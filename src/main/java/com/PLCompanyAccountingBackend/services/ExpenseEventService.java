package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.*;
import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseEventService implements EventServiceInterface {

    private final ExpenseEventRepository expenseEventRepository;

    public ExpenseEventService(ExpenseEventRepository expenseEventRepository){
        this.expenseEventRepository = expenseEventRepository;
    }

    public List<ExpenseEvent> getAllExpensesEvents_SortedByDate(){
        return expenseEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public ExpenseEvent getExpenseEvent_ById(Long id){
        return expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    public Summary createAddEntryForSummary(ExpenseEvent expenseEvent, Summary summary) {
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

    public Summary createDeleteEntryForSummary(ExpenseEvent expenseEvent, Summary summary) {
        BigDecimal remuneration = summary.getRemuneration() == null ? new BigDecimal(0) : summary.getRemuneration();
        BigDecimal otherExpenses = summary.getOtherExpenses() == null ? new BigDecimal(0) : summary.getOtherExpenses();
        BigDecimal totalExpenses = summary.getTotalExpenses() == null ? new BigDecimal(0) : summary.getTotalExpenses();
        BigDecimal financialEconomicIssues = summary.getFinancialEconomicIssues() == null ? new BigDecimal(0) : summary.getFinancialEconomicIssues();

        summary.setRemuneration(remuneration.add(expenseEvent.getRemuneration().negate()));
        summary.setOtherExpenses(otherExpenses.add(expenseEvent.getOtherExpenses().negate()));
        summary.setTotalExpenses(totalExpenses.add(expenseEvent.getTotalExpenses().negate()));
        summary.setFinancialEconomicIssues(financialEconomicIssues.add(expenseEvent.getFinancialEconomicIssues().negate()));

        return summary;
    }

}
