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
@Table(name = "OtherPurchaseCostsEvents")
public class OtherPurchaseCostsEvent extends BusinessEvent {

    @Column(name = "C11 - Other purchase costs")
    private BigDecimal otherPurchaseCosts;

    public OtherPurchaseCostsEvent(OtherPurchaseCostsEvent otherOtherPurchaseCostsEvent) {
        this.otherPurchaseCosts = otherOtherPurchaseCostsEvent.otherPurchaseCosts;
    }
}
