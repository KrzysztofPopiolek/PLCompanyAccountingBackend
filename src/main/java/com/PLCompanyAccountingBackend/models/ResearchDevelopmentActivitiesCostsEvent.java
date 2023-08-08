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
@Table(name = "ResearchDevelopmentActivitiesCostsEvents")
public class ResearchDevelopmentActivitiesCostsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Date of economic event - C.2", nullable = false)
    private Date dateEconomicEvent;

    @Column(name = "Accounting document number - C.3", nullable = false)
    private String accountingDocumentNumber;

    @Column(name = "Description of economic event - C.6", nullable = false)
    private String descriptionEconomicEvent;

    @Column(name = "Research and development activities costs - C.16")
    private BigDecimal researchDevelopmentActivitiesCosts;

    @Column(name = "Events notes/comments - C.17")
    private String eventNotesComments;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private BusinessContractor businessContractor;
}
