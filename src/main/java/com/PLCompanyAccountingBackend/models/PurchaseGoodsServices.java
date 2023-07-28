package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseGoodsServices")
public class PurchaseGoodsServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Purchase goods and services - C.10")
    private String purchaseGoodsServices;

    public PurchaseGoodsServices(){}
    public PurchaseGoodsServices(String purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }

    public Long getId() {
        return id;
    }

    public String getPurchaseGoodsServices() {
        return purchaseGoodsServices;
    }

    public void setPurchaseGoodsServices(String purchaseGoodsServices) {
        this.purchaseGoodsServices = purchaseGoodsServices;
    }
}
