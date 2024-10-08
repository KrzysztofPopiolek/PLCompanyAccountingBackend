package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCostsEvent;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import com.PLCompanyAccountingBackend.services.ResearchDevelopmentActivitiesCostsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
//działalność badawczo-rozwojową /B+R/
public class ResearchDevelopmentActivitiesCostsEventController {

    private final ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService;
    private final AnnualSummaryService annualSummaryService;
    private final MonthlySummaryService monthlySummaryService;
    private final BusinessContractorService businessContractorService;

    @Autowired
    private ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository;

    public ResearchDevelopmentActivitiesCostsEventController(ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService,
                                                             AnnualSummaryService annualSummaryService,
                                                             MonthlySummaryService monthlySummaryService,
                                                             BusinessContractorService businessContractorService) {
        this.researchDevelopmentActivitiesCostsEventService = researchDevelopmentActivitiesCostsEventService;
        this.annualSummaryService = annualSummaryService;
        this.monthlySummaryService = monthlySummaryService;
        this.businessContractorService = businessContractorService;
    }

    @GetMapping("/getAllResearchDevelopmentActivities&EventCosts")
    public List<ResearchDevelopmentActivitiesCostsEvent> getResearchDevelopmentActivitiesCostsEvent() {
        return researchDevelopmentActivitiesCostsEventService.getAllResearchDevelopmentActivitiesCostsEvent_SortedByDate();
    }

    @GetMapping("/getResearchDevelopmentActivitiesCosts&Event/{id}")
    public ResponseEntity<ResearchDevelopmentActivitiesCostsEvent> getResearchDevelopmentActivitiesCostsEventById(@PathVariable Long id) {
        return ResponseEntity.ok(researchDevelopmentActivitiesCostsEventService.getResearchDevelopmentActivitiesCostsEvent_ById(id));
    }

    @PostMapping("/addResearchDevelopmentActivitiesCosts&Event")
    public ResearchDevelopmentActivitiesCostsEvent addResearchDevelopmentActivitiesCostsEvent(
            @RequestBody ResearchDevelopmentActivitiesCostsEvent researchDevelopmentActivitiesCostsEvent) {
        researchDevelopmentActivitiesCostsEvent.setId(0L);
        if (!annualSummaryService.taxYearExists(researchDevelopmentActivitiesCostsEvent.getDateEconomicEvent().getYear())) {
            throw new ResourceNotFoundException("Tax year does not exist");
        } else if (businessContractorService.checkIfContractorExists(researchDevelopmentActivitiesCostsEvent.getBusinessContractor().getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }

        this.annualSummaryService.updateAnnualSummary(researchDevelopmentActivitiesCostsEvent, false);
        this.monthlySummaryService.updateMonthlySummary(researchDevelopmentActivitiesCostsEvent, false);

        return researchDevelopmentActivitiesCostsEventRepository.save(researchDevelopmentActivitiesCostsEvent);
    }

    @DeleteMapping("/deleteResearchDevelopmentActivitiesCosts&Event/{id}")
    public void deleteResearchDevelopmentActivitiesCostsEvent(@PathVariable Long id) {

        ResearchDevelopmentActivitiesCostsEvent researchDevelopmentActivitiesCostsEvent = researchDevelopmentActivitiesCostsEventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event with provided ID does not exist."));
        this.annualSummaryService.updateAnnualSummary(researchDevelopmentActivitiesCostsEvent, true);
        this.monthlySummaryService.updateMonthlySummary(researchDevelopmentActivitiesCostsEvent, true);
        researchDevelopmentActivitiesCostsEventRepository.deleteById(id);
    }

    @PutMapping("/editResearchDevelopmentActivitiesCosts&Event/{id}")
    ResearchDevelopmentActivitiesCostsEvent editResearchDevelopmentActivitiesCostsEvent(
            @RequestBody ResearchDevelopmentActivitiesCostsEvent newResearchDevelopmentActivitiesCostsEvent, @PathVariable Long id) {
        return researchDevelopmentActivitiesCostsEventRepository.findById(id).map(
                researchDevelopmentActivitiesCostsEvent -> {

                    this.annualSummaryService.updateAnnualSummary(researchDevelopmentActivitiesCostsEvent, true);
                    this.monthlySummaryService.updateMonthlySummary(researchDevelopmentActivitiesCostsEvent, true);

                    if (!annualSummaryService.taxYearExists(newResearchDevelopmentActivitiesCostsEvent.getDateEconomicEvent().getYear())) {
                        throw new ResourceNotFoundException("Tax year does not exist");
                    } else if (businessContractorService.checkIfContractorExists(newResearchDevelopmentActivitiesCostsEvent.getBusinessContractor().getId())) {
                        throw new ResourceNotFoundException("Contractor not found");
                    }

                    this.annualSummaryService.updateAnnualSummary(newResearchDevelopmentActivitiesCostsEvent, false);
                    this.monthlySummaryService.updateMonthlySummary(newResearchDevelopmentActivitiesCostsEvent, false);

                    researchDevelopmentActivitiesCostsEvent.setDateEconomicEvent(newResearchDevelopmentActivitiesCostsEvent.getDateEconomicEvent());
                    researchDevelopmentActivitiesCostsEvent.setAccountingDocumentNumber(newResearchDevelopmentActivitiesCostsEvent.getAccountingDocumentNumber());
                    researchDevelopmentActivitiesCostsEvent.setDescriptionEconomicEvent(newResearchDevelopmentActivitiesCostsEvent.getDescriptionEconomicEvent());
                    researchDevelopmentActivitiesCostsEvent.setResearchDevelopmentActivitiesCosts(newResearchDevelopmentActivitiesCostsEvent.getResearchDevelopmentActivitiesCosts());
                    researchDevelopmentActivitiesCostsEvent.setResearchDevelopmentActivitiesDescription(newResearchDevelopmentActivitiesCostsEvent.getResearchDevelopmentActivitiesDescription());
                    researchDevelopmentActivitiesCostsEvent.setEventNotesComments(newResearchDevelopmentActivitiesCostsEvent.getEventNotesComments());
                    researchDevelopmentActivitiesCostsEvent.setBusinessContractor(newResearchDevelopmentActivitiesCostsEvent.getBusinessContractor());
                    return researchDevelopmentActivitiesCostsEventRepository.save(researchDevelopmentActivitiesCostsEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Research development activities costs and event not found!"));
    }
}
