package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "OtherPurchaseCostsController")
public class OtherPurchaseCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Other purchase costs - C.11")
    private Double otherPurchaseCosts;

    public OtherPurchaseCosts() {
    }

    public OtherPurchaseCosts(Double otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }

    public Long getId() {
        return id;
    }

    public Double getOtherPurchaseCosts() {
        return otherPurchaseCosts;
    }

    public void setOtherPurchaseCosts(Double otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }
}
