package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "ExpenseEvents")
public class ExpenseEvent extends BusinessEvent {

    @Column(name = "C12 - Remuneration")
    private BigDecimal remuneration;

    @Column(name = "C13 - Other expenses")
    private BigDecimal otherExpenses;

    @Column(name = "C14 - Total expenses", nullable = false)
    private BigDecimal totalExpenses;

    @Column(name = "C15 - Financial economic issues")
    private BigDecimal financialEconomicIssues;
}
