package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ResearchDevelopmentActivitiesCosts")
public class ResearchDevelopmentActivitiesCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Research and development activities costs - C.16")
    private BigDecimal researchDevelopmentActivitiesCosts;

    public ResearchDevelopmentActivitiesCosts() {
    }

    public ResearchDevelopmentActivitiesCosts(BigDecimal researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getResearchDevelopmentActivitiesCosts() {
        return researchDevelopmentActivitiesCosts;
    }

    public void setResearchDevelopmentActivitiesCosts(BigDecimal researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }
}
