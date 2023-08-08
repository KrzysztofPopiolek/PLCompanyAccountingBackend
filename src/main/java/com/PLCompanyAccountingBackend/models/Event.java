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
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date of economic event - C.2", nullable = false)
    private LocalDate dateEconomicEvent;

    @Column(name = "Accounting document number - C.3", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "Events notes/comments - C.17")
    private String eventNotesComments;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private BusinessContractor businessContractor;

    public Event(Event otherEvent) {
        this.id = otherEvent.id;
        this.dateEconomicEvent = otherEvent.dateEconomicEvent;
        this.accountingDocumentNumber = otherEvent.accountingDocumentNumber;
        this.descriptionEconomicEvent = otherEvent.descriptionEconomicEvent;
        this.eventNotesComments = otherEvent.eventNotesComments;
        this.businessContractor = otherEvent.businessContractor;
    }
}
