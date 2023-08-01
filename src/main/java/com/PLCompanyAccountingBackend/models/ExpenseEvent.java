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
@Table(name = "ExpenseEvent")
public class ExpenseEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date of economic event - C.2", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "Accounting document number - C.3", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "Remuneration - C.12")
    private BigDecimal remuneration;

    @Column(name = "Other expenses - C.13")
    private BigDecimal otherExpenses;

    @Column(name = "Total expenses - C.14", nullable = false)
    private BigDecimal totalExpenses;

    @Column(name = "Financial economic issues - C.15")
    private BigDecimal financialEconomicIssues;

    @Column(name = "Events notes/comments - C.17")
    private String eventNotesComments;
}
