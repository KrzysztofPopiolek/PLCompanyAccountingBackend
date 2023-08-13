package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseEventService {

    private final ExpenseEventRepository expenseEventRepository;

    public ExpenseEventService(ExpenseEventRepository expenseEventRepository) {
        this.expenseEventRepository = expenseEventRepository;
    }

    public List<ExpenseEvent> getAllExpensesEvents_SortedByDate() {
        return expenseEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public ExpenseEvent getExpenseEvent_ById(Long id) {
        return expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    /**
     * Creates an entry which will be added to the summary tables from the provided event.
     *
     * @param expenseEvent The event we added to the expenseEvent table.
     * @param summary      The object which represents the current state of the summary table.
     * @return A summary object which represents the new state of the summary table.
     */
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

    /**
     * Creates an entry which will be added to the summary tables from the provided event. Value are negated so the
     * entry is essentially deleted from the table.
     *
     * @param expenseEvent The event we deleted from the expenseEvent table.
     * @param summary      The object which represents the current state of the summary table.
     * @return A summary object which represents the new state of the summary table.
     */
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
