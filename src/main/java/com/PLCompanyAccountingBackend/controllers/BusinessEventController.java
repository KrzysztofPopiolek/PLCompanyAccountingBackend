package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.repository.BusinessEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessEventController {
    @Autowired
    private BusinessEventRepository businessEventRepository;

    @GetMapping("/getAllBusinessEvents")
    public List<BusinessEvent> getAllBusinessEvents() {
        return businessEventRepository.findAll();
    }

    @GetMapping("/getBusinessEvent/{id}")
    public ResponseEntity<BusinessEvent> getBusinessEventById(@PathVariable Long id) {
        BusinessEvent businessEvent = businessEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(businessEvent);
    }

    @PostMapping("/addBusinessEvent")
    public BusinessEvent addBusinessEvent(@RequestBody BusinessEvent businessEvent) {
        return businessEventRepository.save(businessEvent);
    }

    @DeleteMapping("/deleteBusinessEvent/{id}")
    public void deleteBusinessEvent(@PathVariable Long id) {

        if (businessEventRepository.existsById(id)) {
            businessEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editBusinessEvent/{id}")
    BusinessEvent editBusinessEvent(@RequestBody BusinessEvent newBusinessEvent, @PathVariable Long id) {
        return businessEventRepository.findById(id).map(
                businessEvent -> {
                    businessEvent.setDateEconomicEvent(newBusinessEvent.getDateEconomicEvent());
                    businessEvent.setAccountingDocumentNumber(newBusinessEvent.getAccountingDocumentNumber());
                    businessEvent.setDescriptionEconomicEvent(newBusinessEvent.getDescriptionEconomicEvent());
                    businessEvent.setEventNotesComments(newBusinessEvent.getEventNotesComments());
                    return businessEventRepository.save(businessEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business event not found!"));
    }

}
