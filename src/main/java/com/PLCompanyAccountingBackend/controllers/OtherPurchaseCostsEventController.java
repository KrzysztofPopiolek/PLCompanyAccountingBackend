package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.OtherPurchaseCostsEvent;
import com.PLCompanyAccountingBackend.repository.OtherPurchaseCostsEventRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import com.PLCompanyAccountingBackend.services.OtherPurchaseCostsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class OtherPurchaseCostsEventController {
    private final OtherPurchaseCostsEventService otherPurchaseCostsEventService;
    private final AnnualSummaryService annualSummaryService;
    private final MonthlySummaryService monthlySummaryService;
    private final BusinessContractorService businessContractorService;
    @Autowired
    private OtherPurchaseCostsEventRepository otherPurchaseCostsEventRepository;

    public OtherPurchaseCostsEventController(OtherPurchaseCostsEventService otherPurchaseCostsEventService,
                                             AnnualSummaryService annualSummaryService,
                                             MonthlySummaryService monthlySummaryService,
                                             BusinessContractorService businessContractorService) {
        this.otherPurchaseCostsEventService = otherPurchaseCostsEventService;
        this.annualSummaryService = annualSummaryService;
        this.monthlySummaryService = monthlySummaryService;
        this.businessContractorService = businessContractorService;
    }

    @GetMapping("/getAllOtherPurchaseCosts&Event")
    public List<OtherPurchaseCostsEvent> getAllOtherPurchaseCostsEvent() {
        return otherPurchaseCostsEventService.getAllOtherPurchaseCostsEvent_SortedByDate();
    }

    @GetMapping("/getOtherPurchaseCosts&Event/{id}")
    public ResponseEntity<OtherPurchaseCostsEvent> getOtherPurchaseCostsEventById(@PathVariable Long id) {
        return ResponseEntity.ok(otherPurchaseCostsEventService.getOtherPurchaseCostsEvent_ById(id));
    }

    @PostMapping("/addOtherPurchaseCosts&Event")
    public OtherPurchaseCostsEvent addOtherPurchaseCostsEvent(@RequestBody OtherPurchaseCostsEvent otherPurchaseCostsEvent) {
        otherPurchaseCostsEvent.setId(0L);

        if (!annualSummaryService.taxYearExists(otherPurchaseCostsEvent.getDateEconomicEvent().getYear())) {
            throw new ResourceNotFoundException("Tax year does not exist");
        } else if (businessContractorService.checkIfContractorExists(otherPurchaseCostsEvent.getBusinessContractor().getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }

        this.annualSummaryService.updateAnnualSummary(otherPurchaseCostsEvent, false);
        this.monthlySummaryService.updateMonthlySummary(otherPurchaseCostsEvent, false);
        return otherPurchaseCostsEventRepository.save(otherPurchaseCostsEvent);
    }

    @DeleteMapping("/deleteOtherPurchaseCosts&Event/{id}")
    public void deleteOtherPurchaseCostsEvent(@PathVariable Long id) {

        OtherPurchaseCostsEvent otherPurchaseCostsEvent = otherPurchaseCostsEventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event with provided ID does not exist."));

        this.annualSummaryService.updateAnnualSummary(otherPurchaseCostsEvent, true);
        this.monthlySummaryService.updateMonthlySummary(otherPurchaseCostsEvent, true);
        otherPurchaseCostsEventRepository.deleteById(id);
    }

    @PutMapping("/editOtherPurchaseCosts&Event/{id}")
    OtherPurchaseCostsEvent editOtherPurchaseCostsEvent(@RequestBody OtherPurchaseCostsEvent newOtherPurchaseCostsEvent, @PathVariable Long id) {
        return otherPurchaseCostsEventRepository.findById(id).map(
                otherPurchaseCostsEvent -> {

                    this.annualSummaryService.updateAnnualSummary(otherPurchaseCostsEvent, true);
                    this.monthlySummaryService.updateMonthlySummary(otherPurchaseCostsEvent, true);
                    if (!annualSummaryService.taxYearExists(newOtherPurchaseCostsEvent.getDateEconomicEvent().getYear())) {
                        throw new ResourceNotFoundException("Tax year does not exist");
                    } else if (businessContractorService.checkIfContractorExists(newOtherPurchaseCostsEvent.getBusinessContractor().getId())) {
                        throw new ResourceNotFoundException("Contractor not found");
                    }
                    this.annualSummaryService.updateAnnualSummary(newOtherPurchaseCostsEvent, false);
                    this.monthlySummaryService.updateMonthlySummary(newOtherPurchaseCostsEvent, false);

                    otherPurchaseCostsEvent.setDateEconomicEvent(newOtherPurchaseCostsEvent.getDateEconomicEvent());
                    otherPurchaseCostsEvent.setAccountingDocumentNumber(newOtherPurchaseCostsEvent.getAccountingDocumentNumber());
                    otherPurchaseCostsEvent.setDescriptionEconomicEvent(newOtherPurchaseCostsEvent.getDescriptionEconomicEvent());
                    otherPurchaseCostsEvent.setOtherPurchaseCosts(newOtherPurchaseCostsEvent.getOtherPurchaseCosts());
                    otherPurchaseCostsEvent.setEventNotesComments(newOtherPurchaseCostsEvent.getEventNotesComments());
                    otherPurchaseCostsEvent.setBusinessContractor(newOtherPurchaseCostsEvent.getBusinessContractor());
                    return otherPurchaseCostsEventRepository.save(otherPurchaseCostsEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Other purchase costs and event not found!"));
    }
}
