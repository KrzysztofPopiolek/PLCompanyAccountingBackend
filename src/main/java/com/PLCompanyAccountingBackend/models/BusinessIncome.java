package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BusinessIncome")
public class BusinessIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Sale value - C.7")
    private String saleValue;

    @Column(name = "Other income - C.8")
    private String otherIncome;

    @Column(name = "Total revenue - C.9")
    private String totalRevenue;

    public BusinessIncome() {
    }

    public BusinessIncome(String saleValue, String otherIncome, String totalRevenue) {
        this.saleValue = saleValue;
        this.otherIncome = otherIncome;
        this.totalRevenue = totalRevenue;
    }

    public Long getId() {
        return this.id;
    }

    public String getSaleValue() {
        return this.saleValue;
    }

    public void setSaleValue(String saleValue) {
        this.saleValue = saleValue;
    }

    public String getOtherIncome() {
        return this.otherIncome;
    }

    public void setOtherIncome(String otherIncome) {
        this.otherIncome = otherIncome;
    }

    public String getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
