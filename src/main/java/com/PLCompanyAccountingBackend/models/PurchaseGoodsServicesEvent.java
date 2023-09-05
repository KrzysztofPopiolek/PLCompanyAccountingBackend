package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "PurchaseGoodsServicesEvents")
public class PurchaseGoodsServicesEvent extends BusinessEvent {

    @Column(name = "C10 - Purchase goods and services")
    private BigDecimal purchaseGoodsServices;

    public PurchaseGoodsServicesEvent(PurchaseGoodsServicesEvent otherPurchaseGoodsServicesEvent) {

        this.purchaseGoodsServices = otherPurchaseGoodsServicesEvent.purchaseGoodsServices;
    }
}
