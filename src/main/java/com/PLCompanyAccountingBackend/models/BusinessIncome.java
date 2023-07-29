package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BusinessIncome")
public class BusinessIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Sale value - C.7")
    private BigDecimal saleValue;

    @Column(name = "Other income - C.8")
    private BigDecimal otherIncome;

    @Column(name = "Total revenue - C.9", nullable = false)
    private BigDecimal totalRevenue;

    public BusinessIncome() {
    }

    public BusinessIncome(BigDecimal saleValue, BigDecimal otherIncome, BigDecimal totalRevenue) {
        this.saleValue = saleValue;
        this.otherIncome = otherIncome;
        this.totalRevenue = totalRevenue;
    }

    public Long getId() {
        return this.id;
    }

    public BigDecimal getSaleValue() {
        return this.saleValue;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public BigDecimal getOtherIncome() {
        return this.otherIncome;
    }

    public void setOtherIncome(BigDecimal otherIncome) {
        this.otherIncome = otherIncome;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
