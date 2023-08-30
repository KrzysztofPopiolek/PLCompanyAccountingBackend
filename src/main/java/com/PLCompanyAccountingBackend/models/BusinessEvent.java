package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BusinessEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C2 - Date of economic event", nullable = false)
    private LocalDate dateEconomicEvent;

    @Column(name = "C3 - Accounting document number", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "C6 - Description of economic event", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "C17 - Events notes/comments")
    private String eventNotesComments;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private BusinessContractor businessContractor;
}
