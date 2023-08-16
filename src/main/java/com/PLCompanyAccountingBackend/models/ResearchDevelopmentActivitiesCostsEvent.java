package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ResearchDevelopmentActivitiesCostsEvents")
public class ResearchDevelopmentActivitiesCostsEvent extends BusinessEvent {

    @Column(name = "C16b - Research and development activities costs")
    private BigDecimal researchDevelopmentActivitiesCosts;

    @Column(name = "C16a - Research and development activities description")
    private String researchDevelopmentActivitiesDescription;
}

