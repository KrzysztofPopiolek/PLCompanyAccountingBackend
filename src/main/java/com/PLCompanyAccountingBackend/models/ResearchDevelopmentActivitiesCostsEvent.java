package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ResearchDevelopmentActivitiesCostsEvents")
public class ResearchDevelopmentActivitiesCostsEvent extends BusinessEvent {

    @Column(name = "C16b - Research and development activities costs")
    private BigDecimal researchDevelopmentActivitiesCosts;

    @Column(name = "C16a - Research and development activities description")
    private String researchDevelopmentActivitiesDescription;

    @Builder
    public ResearchDevelopmentActivitiesCostsEvent(Long id, LocalDate dateEconomicEvent, String accountingDocumentNumber, String descriptionEconomicEvent,
                                                   String eventNotesComments, BusinessContractor businessContractor, BigDecimal researchDevelopmentActivitiesCosts,
                                                   String researchDevelopmentActivitiesDescription) {
        super(id, dateEconomicEvent, accountingDocumentNumber, descriptionEconomicEvent, eventNotesComments, businessContractor);
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
        this.researchDevelopmentActivitiesDescription = researchDevelopmentActivitiesDescription;
    }
}

