package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ProfitCalculation")
public class ProfitCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date of Profit Calculation", nullable = false)
    private LocalDate dateProfitCalculation;

    @Column(name = "Total deductible costs", nullable = false) //Razem koszty uzyskania przychodu (pkt 2)
    private BigDecimal totalDeductibleCosts;

    @Column(name = "Total profit", nullable = false) //a) przychód (pkt 1) |b) minus koszty uzyskania przychodów (pkt 2)
    private BigDecimal totalProfit;   //  Dochód (a–b)
}
