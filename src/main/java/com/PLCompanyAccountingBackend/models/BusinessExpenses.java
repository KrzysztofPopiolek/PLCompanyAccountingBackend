package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BusinessExpenses")
public class BusinessExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Remuneration - C.12")
    private BigDecimal remuneration;

    @Column(name = "Other expenses - C.13")
    private BigDecimal otherExpenses;
    @Column(name = "Total expenses - C.14", nullable = false)
    private BigDecimal totalExpenses;
    @Column(name = "Financial economic issues - C.15")
    private BigDecimal financialEconomicIssues;

    public BusinessExpenses() {
    }

    public BusinessExpenses(BigDecimal remuneration, BigDecimal otherExpenses, BigDecimal totalExpenses, BigDecimal financialEconomicIssues) {
        this.remuneration = remuneration;
        this.otherExpenses = otherExpenses;
        this.totalExpenses = totalExpenses;
        this.financialEconomicIssues = financialEconomicIssues;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(BigDecimal remuneration) {
        this.remuneration = remuneration;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getFinancialEconomicIssues() {
        return financialEconomicIssues;
    }

    public void setFinancialEconomicIssues(BigDecimal financialEconomicIssues) {
        this.financialEconomicIssues = financialEconomicIssues;
    }
}
