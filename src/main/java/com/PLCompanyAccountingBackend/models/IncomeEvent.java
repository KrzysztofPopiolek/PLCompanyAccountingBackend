package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "IncomeEvents")
public class IncomeEvent extends BusinessEvent {

    @Column(name = "C7 - Sale value")
    private BigDecimal saleValue;

    @Column(name = "C8 - Other income")
    private BigDecimal otherIncome;

    @Column(name = "C9 - Total revenue", nullable = false)
    private BigDecimal totalRevenue;

    @Builder
    public IncomeEvent(Long id, LocalDate dateEconomicEvent, String accountingDocumentNumber, String descriptionEconomicEvent,
                       String eventNotesComments, BusinessContractor businessContractor, BigDecimal saleValue, BigDecimal otherIncome,
                       BigDecimal totalRevenue){
        super(id, dateEconomicEvent, accountingDocumentNumber, descriptionEconomicEvent, eventNotesComments, businessContractor);
        this.saleValue = saleValue;
        this.otherIncome = otherIncome;
        this.totalRevenue = totalRevenue;
    }

}
