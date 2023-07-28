package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BusinessContractors")
public class BusinessContractors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Business/Contractors name - C.4", nullable = false)
    private String businessContractorsName;

    @Column(name = "Business/Contractors address - C.5", nullable = false)
    private String businessContractorsAddress;

    public BusinessContractors() {
    }

    public BusinessContractors(String businessContractorsName, String businessContractorsAddress) {
        this.businessContractorsName = businessContractorsName;
        this.businessContractorsAddress = businessContractorsAddress;
    }

    public Long getId() {
        return id;
    }

    public String getBusinessContractorsName() {
        return businessContractorsName;
    }

    public void setBusinessContractorsName(String businessContractorsName) {
        this.businessContractorsName = businessContractorsName;
    }

    public String getBusinessContractorsAddress() {
        return businessContractorsAddress;
    }

    public void setBusinessContractorsAddress(String businessContractorsAddress) {
        this.businessContractorsAddress = businessContractorsAddress;
    }
}
