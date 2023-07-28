package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "BusinessEvents")
public class BusinessEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date of economic event - C.2", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "Accounting document number - C.3")
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;
    
    @Column(name = "Events notes/comments - C.17")
    private String eventNotesComments;

    public BusinessEvent() {
    }

    public BusinessEvent(Date dateEconomicEvent, String accountingDocumentNumber, String descriptionEconomicEvent, String eventNotesComments) {
        this.dateEconomicEvent = dateEconomicEvent;
        this.accountingDocumentNumber = accountingDocumentNumber;
        this.descriptionEconomicEvent = descriptionEconomicEvent;
        this.eventNotesComments = eventNotesComments;
    }

    public Long getId() {
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

    public String getEventNotesComments() {
        return this.eventNotesComments;
    }

    public void setEventNotesComments(String eventNotesComments) {
        this.eventNotesComments = eventNotesComments;
    }
}

