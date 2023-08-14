package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCostsEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class ResearchDevelopmentActivitiesCostsEventService {

    private final ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository;

    public ResearchDevelopmentActivitiesCostsEventService
            (ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository) {
        this.researchDevelopmentActivitiesCostsEventRepository = researchDevelopmentActivitiesCostsEventRepository;
    }

    public List<ResearchDevelopmentActivitiesCostsEvent> getAllResearchDevelopmentActivitiesCostsEvent_SortedByDate() {
        return researchDevelopmentActivitiesCostsEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public ResearchDevelopmentActivitiesCostsEvent getResearchDevelopmentActivitiesCostsEvent_ById(Long id) {
        return researchDevelopmentActivitiesCostsEventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Searched item not found!"));
    }

    /**
     * Creates an entry which will be added to the summary tables from the provided event.
     *
     * @param researchDevelopmentActivitiesCostsEvent The event we added to the researchDevelopmentActivitiesCostsEvent table.
     * @param summary                                 The object which represents the current state of the summary table.
     * @return A summary object which represents the new state of the summary table.
     */
    public Summary createEntryForSummary(ResearchDevelopmentActivitiesCostsEvent researchDevelopmentActivitiesCostsEvent, Summary summary, Boolean deleteMode) {
        BigDecimal researchDevelopmentActivitiesCosts = summary.getResearchDevelopmentActivitiesCosts() == null ?
                new BigDecimal(0) : summary.getResearchDevelopmentActivitiesCosts();

        if (deleteMode) {
            summary.setResearchDevelopmentActivitiesCosts(researchDevelopmentActivitiesCosts.add
                    (researchDevelopmentActivitiesCostsEvent.getResearchDevelopmentActivitiesCosts().negate()));
        } else {
            summary.setResearchDevelopmentActivitiesCosts(researchDevelopmentActivitiesCosts.add
                    (researchDevelopmentActivitiesCostsEvent.getResearchDevelopmentActivitiesCosts()));
        }
        return summary;
    }
}
