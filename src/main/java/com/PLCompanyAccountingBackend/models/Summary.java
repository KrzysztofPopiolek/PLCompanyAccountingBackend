package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "C7 - Sale value")
    private BigDecimal saleValue;

    @Column(name = "C8 - Other income")
    private BigDecimal otherIncome;

    @Column(name = "C9 - Total revenue")
    private BigDecimal totalRevenue;

    @Column(name = "C10 - Purchase goods and services")
    private BigDecimal purchaseGoodsServices;

    @Column(name = "C11 - Other purchase costs")
    private BigDecimal otherPurchaseCosts;

    @Column(name = "C12 - Remuneration")
    private BigDecimal remuneration;

    @Column(name = "C13 - Other expenses")
    private BigDecimal otherExpenses;

    @Column(name = "C14 - Total expenses")
    private BigDecimal totalExpenses;

    @Column(name = "C15 - Financial economic issues")
    private BigDecimal financialEconomicIssues;
}
