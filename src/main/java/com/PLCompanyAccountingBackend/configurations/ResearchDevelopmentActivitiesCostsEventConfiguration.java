package com.PLCompanyAccountingBackend.configurations;

import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import com.PLCompanyAccountingBackend.services.ResearchDevelopmentActivitiesCostsEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ResearchDevelopmentActivitiesCostsEventConfiguration {
    @Bean
    public ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService
            (ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository) {
        return new ResearchDevelopmentActivitiesCostsEventService(researchDevelopmentActivitiesCostsEventRepository);
    }
}
