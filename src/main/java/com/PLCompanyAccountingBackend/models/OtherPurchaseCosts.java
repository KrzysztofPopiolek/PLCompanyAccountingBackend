package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "OtherPurchaseCostsController")
public class OtherPurchaseCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Other purchase costs - C.11")
    private BigDecimal otherPurchaseCosts;

    public OtherPurchaseCosts() {
    }

    public OtherPurchaseCosts(BigDecimal otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getOtherPurchaseCosts() {
        return otherPurchaseCosts;
    }

    public void setOtherPurchaseCosts(BigDecimal otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }
}
