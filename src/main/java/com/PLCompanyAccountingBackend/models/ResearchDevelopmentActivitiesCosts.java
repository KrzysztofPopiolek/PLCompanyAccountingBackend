package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ResearchDevelopmentActivitiesCosts")
public class ResearchDevelopmentActivitiesCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Research and development activities costs - C.16")
    private String researchDevelopmentActivitiesCosts;

    public ResearchDevelopmentActivitiesCosts(){}

    public ResearchDevelopmentActivitiesCosts(String researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }

    public Long getId() {
        return id;
    }

    public String getResearchDevelopmentActivitiesCosts() {
        return researchDevelopmentActivitiesCosts;
    }

    public void setResearchDevelopmentActivitiesCosts(String researchDevelopmentActivitiesCosts) {
        this.researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCosts;
    }
}
