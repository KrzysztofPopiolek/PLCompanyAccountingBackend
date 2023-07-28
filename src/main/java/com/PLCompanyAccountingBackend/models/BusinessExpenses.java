package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BusinessExpenses")
public class BusinessExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Remuneration - C.12")
    private String remuneration;

    @Column(name = "Other expenses - C.13")
    private String otherExpenses;
    @Column(name = "Total expenses - C.14")
    private String totalExpenses;
    @Column(name = "Financial economic issues - C.15")
    private String financialEconomicIssues;

    public BusinessExpenses() {
    }

    public BusinessExpenses(String remuneration, String otherExpenses, String totalExpenses, String financialEconomicIssues) {
        this.remuneration = remuneration;
        this.otherExpenses = otherExpenses;
        this.totalExpenses = totalExpenses;
        this.financialEconomicIssues = financialEconomicIssues;
    }

    public Long getId() {
        return id;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public String getOtherExpenses() {
        return otherExpenses;
    }

    public String getTotalExpenses() {
        return totalExpenses;
    }

    public String getFinancialEconomicIssues() {
        return financialEconomicIssues;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public void setOtherExpenses(String otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public void setTotalExpenses(String totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void setFinancialEconomicIssues(String financialEconomicIssues) {
        this.financialEconomicIssues = financialEconomicIssues;
    }
}
