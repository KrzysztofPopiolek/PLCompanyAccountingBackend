package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IncomeEvents")
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

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private BusinessContractor businessContractor;
}
