package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "PurchaseGoodsServicesEvents")
public class PurchaseGoodsServicesEvent extends BusinessEvent {

    @Column(name = "C10 - Purchase goods and services")
    private BigDecimal purchaseGoodsServices;

    public PurchaseGoodsServicesEvent(PurchaseGoodsServicesEvent otherPurchaseGoodsServicesEvent) {
        super(otherPurchaseGoodsServicesEvent);
        this.purchaseGoodsServices = otherPurchaseGoodsServicesEvent.purchaseGoodsServices;
    }
}
