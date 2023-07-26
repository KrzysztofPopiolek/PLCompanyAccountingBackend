package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "BusinessEventRepository")
public class BusinessEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Date of economic event - C.2", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "Accounting document number - C.3", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;

    public BusinessEvent() {
    }

    public BusinessEvent(Date dateEconomicEvent, String accountingDocumentNumber, String descriptionEconomicEvent) {
        this.dateEconomicEvent = dateEconomicEvent;
        this.accountingDocumentNumber = accountingDocumentNumber;
        this.descriptionEconomicEvent = descriptionEconomicEvent;
    }

    public int getId() {
        return this.id;
    }

    public Date getDateEconomicEvent() {
        return this.dateEconomicEvent;
    }

    public void setDateEconomicEvent(Date dateEconomicEvent) {
        this.dateEconomicEvent = dateEconomicEvent;
    }

    public String getAccountingDocumentNumber() {
        return this.accountingDocumentNumber;
    }

    public void setAccountingDocumentNumber(String accountingDocumentNumber) {
        this.accountingDocumentNumber = accountingDocumentNumber;
    }

    public String getDescriptionEconomicEvent() {
        return this.descriptionEconomicEvent;
    }

    public void setDescriptionEconomicEvent(String descriptionEconomicEvent) {
        this.descriptionEconomicEvent = descriptionEconomicEvent;
    }
}

