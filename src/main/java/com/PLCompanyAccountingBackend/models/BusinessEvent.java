package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
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
