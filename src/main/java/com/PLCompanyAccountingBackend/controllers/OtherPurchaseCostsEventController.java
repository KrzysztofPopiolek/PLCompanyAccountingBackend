package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.BusinessContractor;
import com.PLCompanyAccountingBackend.models.OtherPurchaseCostsEvent;
import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import com.PLCompanyAccountingBackend.repository.OtherPurchaseCostsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class OtherPurchaseCostsEventController {
    @Autowired
    private OtherPurchaseCostsEventRepository otherPurchaseCostsEventRepository;

    @Autowired
    private BusinessContractorRepository businessContractorRepository;

    @GetMapping("/getAllOtherPurchaseCosts&Event")
    public List<OtherPurchaseCostsEvent> getAllOtherPurchaseCostsEvent() {
        return otherPurchaseCostsEventRepository.findAll();
    }

    @GetMapping("/getOtherPurchaseCosts&Event/{id}")
    public ResponseEntity<OtherPurchaseCostsEvent> getOtherPurchaseCostsEventById(@PathVariable Long id) {
        OtherPurchaseCostsEvent otherPurchaseCostsEvent = otherPurchaseCostsEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(otherPurchaseCostsEvent);
    }

    @PostMapping("/addOtherPurchaseCosts&Event")
    public OtherPurchaseCostsEvent addOtherPurchaseCostsEvent(@RequestBody OtherPurchaseCostsEvent otherPurchaseCostsEvent) {
        if (!businessContractorRepository.existsById(otherPurchaseCostsEvent.getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }
        return otherPurchaseCostsEventRepository.save(otherPurchaseCostsEvent);
    }

    @DeleteMapping("/deleteOtherPurchaseCosts&Event/{id}")
    public void deleteOtherPurchaseCostsEvent(@PathVariable Long id) {
        if (otherPurchaseCostsEventRepository.existsById(id)) {
            otherPurchaseCostsEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editOtherPurchaseCosts&Event/{id}")
    OtherPurchaseCostsEvent editOtherPurchaseCostsEvent(@RequestBody OtherPurchaseCostsEvent newOtherPurchaseCostsEvent, @PathVariable Long id) {
        return otherPurchaseCostsEventRepository.findById(id).map(
                otherPurchaseCostsEvent -> {
                    if (!businessContractorRepository.existsById(newOtherPurchaseCostsEvent.getId())) {
                        throw new ResourceNotFoundException("Contractor not found");
                    }
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
