package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PurchaseGoodsServices")
public class PurchaseGoodsServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Purchase goods and services - C.10")
    private BigDecimal purchaseGoodsServices;

    public PurchaseGoodsServices(){}
    public PurchaseGoodsServices(BigDecimal purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPurchaseGoodsServices() {
        return purchaseGoodsServices;
    }

    public void setPurchaseGoodsServices(BigDecimal purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }
}
