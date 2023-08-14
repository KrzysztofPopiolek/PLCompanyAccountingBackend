package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PurchaseGoodsServicesEvents")
public class PurchaseGoodsServicesEvent {


    @Column(name = "C10 - Purchase goods and services")
    private BigDecimal purchaseGoodsServices;

    public PurchaseGoodsServicesEvent(PurchaseGoodsServicesEvent otherPurchaseGoodsServicesEvent){
        super(otherPurchaseGoodsServicesEvent);
        this.purchaseGoodsServices = otherPurchaseGoodsServicesEvent.purchaseGoodsServices;
    }
}
