package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "IncomeEvent")
public class IncomeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Date of economic event - C.2", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "Accounting document number - C.3", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "Sale value - C.7")
    private BigDecimal saleValue;

    @Column(name = "Other income - C.8")
    private BigDecimal otherIncome;

    @Column(name = "Total revenue - C.9", nullable = false)
    private BigDecimal totalRevenue;
    @Column(name = "Events notes/comments - C.17")
    private String eventNotesComments;

    public IncomeEvent() {
    }

    public IncomeEvent(Date dateEconomicEvent, String accountingDocumentNumber, String descriptionEconomicEvent, BigDecimal saleValue, BigDecimal otherIncome, BigDecimal totalRevenue, String eventNotesComments) {
        this.dateEconomicEvent = dateEconomicEvent;
        this.accountingDocumentNumber = accountingDocumentNumber;
        this.descriptionEconomicEvent = descriptionEconomicEvent;
        this.saleValue = saleValue;
        this.otherIncome = otherIncome;
        this.totalRevenue = totalRevenue;
        this.eventNotesComments = eventNotesComments;
    }

    public Long getId() {
        return id;
    }

    public Date getDateEconomicEvent() {
        return dateEconomicEvent;
    }

    public String getAccountingDocumentNumber() {
        return accountingDocumentNumber;
    }

    public String getDescriptionEconomicEvent() {
        return descriptionEconomicEvent;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public BigDecimal getOtherIncome() {
        return otherIncome;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public String getEventNotesComments() {
        return eventNotesComments;
    }

    public void setDateEconomicEvent(Date dateEconomicEvent) {
        this.dateEconomicEvent = dateEconomicEvent;
    }

    public void setAccountingDocumentNumber(String accountingDocumentNumber) {
        this.accountingDocumentNumber = accountingDocumentNumber;
    }

    public void setDescriptionEconomicEvent(String descriptionEconomicEvent) {
        this.descriptionEconomicEvent = descriptionEconomicEvent;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public void setOtherIncome(BigDecimal otherIncome) {
        this.otherIncome = otherIncome;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setEventNotesComments(String eventNotesComments) {
        this.eventNotesComments = eventNotesComments;
    }
}
