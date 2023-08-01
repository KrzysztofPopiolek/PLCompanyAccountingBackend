package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCostsEvent;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
//działalność badawczo-rozwojową /B+R/
public class ResearchDevelopmentActivitiesCostsEventController {
    @Autowired
    private ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository;

    @GetMapping("/getAllResearchDevelopmentActivities&EventCosts")
    public List<ResearchDevelopmentActivitiesCostsEvent> getResearchDevelopmentActivitiesCostsEvent() {
        return researchDevelopmentActivitiesCostsEventRepository.findAll();
    }

    @GetMapping("/getResearchDevelopmentActivitiesCosts&Event/{id}")
    public ResponseEntity<ResearchDevelopmentActivitiesCostsEvent> getResearchDevelopmentActivitiesCostsEventById(@PathVariable Long id) {
        ResearchDevelopmentActivitiesCostsEvent researchDevelopmentActivitiesCostsEvent = researchDevelopmentActivitiesCostsEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(researchDevelopmentActivitiesCostsEvent);
    }

    @PostMapping("/addResearchDevelopmentActivitiesCosts&Event")
    public ResearchDevelopmentActivitiesCostsEvent addResearchDevelopmentActivitiesCostsEvent(@RequestBody ResearchDevelopmentActivitiesCostsEvent researchDevelopmentActivitiesCostsEvent) {
        return researchDevelopmentActivitiesCostsEventRepository.save(researchDevelopmentActivitiesCostsEvent);
    }

    @DeleteMapping("/deleteResearchDevelopmentActivitiesCosts&Event/{id}")
    public void deleteResearchDevelopmentActivitiesCostsEvent(@PathVariable Long id) {
        if (researchDevelopmentActivitiesCostsEventRepository.existsById(id)) {
            researchDevelopmentActivitiesCostsEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editResearchDevelopmentActivitiesCosts&Event/{id}")
    ResearchDevelopmentActivitiesCostsEvent editResearchDevelopmentActivitiesCostsEvent(@RequestBody ResearchDevelopmentActivitiesCostsEvent newResearchDevelopmentActivitiesCostsEvent, @PathVariable Long id) {
        return researchDevelopmentActivitiesCostsEventRepository.findById(id).map(
                researchDevelopmentActivitiesCostsEvent -> {
                    researchDevelopmentActivitiesCostsEvent.setDateEconomicEvent(newResearchDevelopmentActivitiesCostsEvent.getDateEconomicEvent());
                    researchDevelopmentActivitiesCostsEvent.setAccountingDocumentNumber(newResearchDevelopmentActivitiesCostsEvent.getAccountingDocumentNumber());
                    researchDevelopmentActivitiesCostsEvent.setDescriptionEconomicEvent(newResearchDevelopmentActivitiesCostsEvent.getDescriptionEconomicEvent());
                    researchDevelopmentActivitiesCostsEvent.setResearchDevelopmentActivitiesCosts(newResearchDevelopmentActivitiesCostsEvent.getResearchDevelopmentActivitiesCosts());
                    researchDevelopmentActivitiesCostsEvent.setEventNotesComments(newResearchDevelopmentActivitiesCostsEvent.getEventNotesComments());
                    return researchDevelopmentActivitiesCostsEventRepository.save(researchDevelopmentActivitiesCostsEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Research development activities costs and event not found!"));
    }
}
