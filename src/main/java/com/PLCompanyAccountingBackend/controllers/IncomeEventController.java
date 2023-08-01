package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/v_1/")
public class IncomeEventController {

    @Autowired
    private IncomeEventRepository incomeEventRepository;

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
                    incomeEvent.setDateEconomicEvent(newIncomeEvent.getDateEconomicEvent());
                    incomeEvent.setAccountingDocumentNumber(newIncomeEvent.getAccountingDocumentNumber());
                    incomeEvent.setDescriptionEconomicEvent(newIncomeEvent.getDescriptionEconomicEvent());
                    incomeEvent.setSaleValue(newIncomeEvent.getSaleValue());
                    incomeEvent.setOtherIncome(newIncomeEvent.getOtherIncome());
                    incomeEvent.setTotalRevenue(newIncomeEvent.getTotalRevenue());
                    incomeEvent.setEventNotesComments(newIncomeEvent.getEventNotesComments());
                    return incomeEventRepository.save(incomeEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Income and event not found!"));
    }
}
