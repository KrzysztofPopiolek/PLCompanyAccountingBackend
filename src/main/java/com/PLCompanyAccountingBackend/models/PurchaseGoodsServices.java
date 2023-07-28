package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseGoodsServices")
public class PurchaseGoodsServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Purchase goods and services - C.10")
    private Double purchaseGoodsServices;

    public PurchaseGoodsServices(){}
    public PurchaseGoodsServices(Double purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }

    public Long getId() {
        return id;
    }

    public Double getPurchaseGoodsServices() {
        return purchaseGoodsServices;
    }

    public void setPurchaseGoodsServices(Double purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }
}
