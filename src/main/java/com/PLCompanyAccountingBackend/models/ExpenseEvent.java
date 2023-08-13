package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ExpenseEvents")
public class ExpenseEvent extends BusinessEvent {

    @Column(name = "Remuneration - C.12")
    private BigDecimal remuneration;

    @Column(name = "Other expenses - C.13")
    private BigDecimal otherExpenses;

    @Column(name = "Total expenses - C.14", nullable = false)
    private BigDecimal totalExpenses;

    @Column(name = "Financial economic issues - C.15")
    private BigDecimal financialEconomicIssues;

    public ExpenseEvent(ExpenseEvent otherExpenseEvent) {
        super(otherExpenseEvent);
        this.remuneration = otherExpenseEvent.remuneration;
        this.otherExpenses = otherExpenseEvent.otherExpenses;
        this.totalExpenses = otherExpenseEvent.totalExpenses;
        this.financialEconomicIssues = otherExpenseEvent.financialEconomicIssues;
    }
}
