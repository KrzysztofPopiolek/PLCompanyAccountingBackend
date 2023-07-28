package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "OtherPurchaseCostsController")
public class OtherPurchaseCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Other purchase costs - C.11")
    private String otherPurchaseCosts;

    public OtherPurchaseCosts() {
    }

    public OtherPurchaseCosts(String otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }

    public Long getId() {
        return id;
    }

    public String getOtherPurchaseCosts() {
        return otherPurchaseCosts;
    }

    public void setOtherPurchaseCosts(String otherPurchaseCosts) {
        this.otherPurchaseCosts = otherPurchaseCosts;
    }
}
