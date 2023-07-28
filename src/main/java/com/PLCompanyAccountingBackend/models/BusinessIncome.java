package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BusinessIncome")
public class BusinessIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Sale value - C.7")
    private Double saleValue;

    @Column(name = "Other income - C.8")
    private Double otherIncome;

    @Column(name = "Total revenue - C.9", nullable = false)
    private Double totalRevenue;

    public BusinessIncome() {
    }

    public BusinessIncome(Double saleValue, Double otherIncome, Double totalRevenue) {
        this.saleValue = saleValue;
        this.otherIncome = otherIncome;
        this.totalRevenue = totalRevenue;
    }

    public Long getId() {
        return this.id;
    }

    public Double getSaleValue() {
        return this.saleValue;
    }

    public void setSaleValue(Double saleValue) {
        this.saleValue = saleValue;
    }

    public Double getOtherIncome() {
        return this.otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
