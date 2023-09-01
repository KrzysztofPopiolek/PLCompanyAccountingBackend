package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class Summary {

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

    @Column(name = "C10 - Purchase goods and materials cost")
    private BigDecimal purchaseGoodsMaterialsCost;

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

    @Column(name = "C16 - Research and development activities costs")
    private BigDecimal researchDevelopmentActivitiesCosts;
}
