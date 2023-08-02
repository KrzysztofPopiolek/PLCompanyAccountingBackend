package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AnnualSummary")
public class AnnualSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Year", nullable = false)
    private Date year;

    @Column(name = "Sale value - C.7")
    private BigDecimal saleValue;

    @Column(name = "Other income - C.8")
    private BigDecimal otherIncome;

    @Column(name = "Total revenue - C.9")
    private BigDecimal totalRevenue;

    @Column(name = "Purchase goods and services - C.10")
    private BigDecimal purchaseGoodsServices;

    @Column(name = "Other purchase costs - C.11")
    private BigDecimal otherPurchaseCosts;

    @Column(name = "Remuneration - C.12")
    private BigDecimal remuneration;

    @Column(name = "Other expenses - C.13")
    private BigDecimal otherExpenses;

    @Column(name = "Total expenses - C.14")
    private BigDecimal totalExpenses;

    @Column(name = "Financial economic issues - C.15")
    private BigDecimal financialEconomicIssues;
}
