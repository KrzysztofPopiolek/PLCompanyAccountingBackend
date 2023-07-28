package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ResearchDevelopmentActivitiesCosts")
public class ResearchDevelopmentActivitiesCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Research and development activities costs - C.16")
    private Double researchDevelopmentActivitiesCosts;

    public ResearchDevelopmentActivitiesCosts(){}

    public ResearchDevelopmentActivitiesCosts(Double researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }

    public Long getId() {
        return id;
    }

    public Double getResearchDevelopmentActivitiesCosts() {
        return researchDevelopmentActivitiesCosts;
    }

    public void setResearchDevelopmentActivitiesCosts(Double researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }
}
