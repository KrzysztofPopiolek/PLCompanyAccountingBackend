package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PurchaseGoodsServicesEvents")
public class PurchaseGoodsServicesEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C2 - Date of economic event", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "C3 - Accounting document number", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "C6 - Description of economic event", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "C10 - Purchase goods and services")
    private BigDecimal purchaseGoodsServices;

    @Column(name = "C17 - Events notes/comments")
    private String eventNotesComments;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private BusinessContractor businessContractor;
}
