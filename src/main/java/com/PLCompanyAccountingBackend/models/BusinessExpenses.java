package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.util.Currency;

@Entity
@Table(name = "BusinessExpenses")
public class BusinessExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Remuneration - C.12")
    private Double remuneration;

    @Column(name = "Other expenses - C.13")
    private Double otherExpenses;
    @Column(name = "Total expenses - C.14")
    private Double totalExpenses;
    @Column(name = "Financial economic issues - C.15")
    private Double financialEconomicIssues;

    public BusinessExpenses() {
    }



    public Long getId() {
        return id;
    }

    public Double getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Double remuneration) {
        this.remuneration = remuneration;
    }

    public Double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(Double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Double getFinancialEconomicIssues() {
        return financialEconomicIssues;
    }

    public void setFinancialEconomicIssues(Double financialEconomicIssues) {
        this.financialEconomicIssues = financialEconomicIssues;
    }
}
