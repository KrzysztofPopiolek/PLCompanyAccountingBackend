package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
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
@Table(name = "ProfitCalculation")
public class ProfitCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C9 - Total revenue sume", nullable = false) //Przychód (pkt 1)
    private BigDecimal totalRevenueSume;

    @Column(name = "Inventory entries, start") //wartość spisu z natury na początek roku podatkowego
    private BigDecimal inventoryEntriesStart;

    @Column(name = "Inventory entries, year end") //minus wartość spisu z natury na koniec roku podatkowego
    private BigDecimal inventoryEntriesYearEnd;

    @Column(name = "C10 - Purchase goods and services sume") //plus wydatki na zakup towarów handlowych i materiałów
    private BigDecimal purchaseGoodsServicesSume;

    @Column(name = "C11 - Other purchase costs sume") //plus wydatki na koszty uboczne zakupu
    private BigDecimal otherPurchaseCostsSume;


    @Column(name = "C14 - Total expenses sume", nullable = false) //plus kwota pozostałych wydatków
    private BigDecimal totalExpensesSume;

    @Column(name = "C15 - Financial economic issues sume")
    //minus wartość wynagrodzeń w naturze ujętych w innych kolumnach księgi
    private BigDecimal financialEconomicIssuesSume;

    @Column(name = "Total deductible costs", nullable = false) //Razem koszty uzyskania przychodu (pkt 2)
    private BigDecimal totalDeductibleCosts;

    @Column(name = "Total profit", nullable = false) //a) przychód (pkt 1) |b) minus koszty uzyskania przychodów (pkt 2)
    private BigDecimal totalProfit;   //  Dochód (a–b)
}
