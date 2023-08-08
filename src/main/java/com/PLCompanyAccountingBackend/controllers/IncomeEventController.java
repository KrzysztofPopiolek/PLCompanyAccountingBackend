package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class IncomeEventController {

    @Autowired
    private IncomeEventRepository incomeEventRepository;

    @Autowired
    private BusinessContractorRepository businessContractorRepository;

    @GetMapping("/getAllIncome&Event")
    public List<IncomeEvent> getAllIncomeEvent() {
        return incomeEventRepository.findAll();
    }

    @GetMapping("/getIncome&Event/{id}")
    public ResponseEntity<IncomeEvent> getIncomeEventById(@PathVariable Long id) {
        IncomeEvent incomeEvent = incomeEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(incomeEvent);
    }

    @PostMapping("/addIncome&Event")
    public IncomeEvent addIncomeEvent(@RequestBody IncomeEvent incomeEvent) {
        if (!businessContractorRepository.existsById(incomeEvent.getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }
        return incomeEventRepository.save(incomeEvent);
    }

    @DeleteMapping("/deleteIncome&Event/{id}")
    public void deleteIncomeEvent(@PathVariable Long id) {
        if (incomeEventRepository.existsById(id)) {
            incomeEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editIncome&Event/{id}")
    IncomeEvent editIncomeEvent(@RequestBody IncomeEvent newIncomeEvent, @PathVariable Long id) {
        return incomeEventRepository.findById(id).map(
                incomeEvent -> {
                    if (!businessContractorRepository.existsById(newIncomeEvent.getId())) {
                        throw new ResourceNotFoundException("Contractor not found");
                    }
                    incomeEvent.setDateEconomicEvent(newIncomeEvent.getDateEconomicEvent());
                    incomeEvent.setAccountingDocumentNumber(newIncomeEvent.getAccountingDocumentNumber());
                    incomeEvent.setDescriptionEconomicEvent(newIncomeEvent.getDescriptionEconomicEvent());
                    incomeEvent.setSaleValue(newIncomeEvent.getSaleValue());
                    incomeEvent.setOtherIncome(newIncomeEvent.getOtherIncome());
                    incomeEvent.setTotalRevenue(newIncomeEvent.getTotalRevenue());
                    incomeEvent.setEventNotesComments(newIncomeEvent.getEventNotesComments());
                    incomeEvent.setBusinessContractor(newIncomeEvent.getBusinessContractor());
                    return incomeEventRepository.save(incomeEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Income and event not found!"));
    }
}
