package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class IncomeEventController {

    @Autowired
    private IncomeEventRepository incomeEventRepository;

    private final BusinessContractorService businessContractorService;

    private final IncomeEventService incomeEventService;
    private final MonthlySummaryService monthlySummaryService;

    private final AnnualSummaryService annualSummaryService;

    public IncomeEventController(BusinessContractorService businessContractorService, IncomeEventService incomeEventService,
                                 MonthlySummaryService monthlySummaryService, AnnualSummaryService annualSummaryService) {
        this.businessContractorService = businessContractorService;
        this.incomeEventService = incomeEventService;
        this.monthlySummaryService = monthlySummaryService;
        this.annualSummaryService = annualSummaryService;
    }

    @GetMapping("/getAllIncome&Event")
    public List<IncomeEvent> getAllIncomeEvent() {
        return incomeEventService.getAllIncomeEvent_SortedByDate();
    }
//
//    @GetMapping("/getIncome&Event/{id}")
//    public ResponseEntity<IncomeEvent> getIncomeEventById(@PathVariable Long id) {
//        IncomeEvent incomeEvent = incomeEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
//        return ResponseEntity.ok(incomeEvent);
//    }
//
//    @PostMapping("/addIncome&Event")
//    public IncomeEvent addIncomeEvent(@RequestBody IncomeEvent incomeEvent) {
//        if (!businessContractorRepository.existsById(incomeEvent.getId())) {
//            throw new ResourceNotFoundException("Contractor not found");
//        }
//        return incomeEventRepository.save(incomeEvent);
//    }
//
//    @DeleteMapping("/deleteIncome&Event/{id}")
//    public void deleteIncomeEvent(@PathVariable Long id) {
//        if (incomeEventRepository.existsById(id)) {
//            incomeEventRepository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Item not found!");
//        }
//    }
//
//    @PutMapping("/editIncome&Event/{id}")
//    IncomeEvent editIncomeEvent(@RequestBody IncomeEvent newIncomeEvent, @PathVariable Long id) {
//        return incomeEventRepository.findById(id).map(
//                incomeEvent -> {
//                    if (!businessContractorRepository.existsById(newIncomeEvent.getId())) {
//                        throw new ResourceNotFoundException("Contractor not found");
//                    }
//                    incomeEvent.setDateEconomicEvent(newIncomeEvent.getDateEconomicEvent());
//                    incomeEvent.setAccountingDocumentNumber(newIncomeEvent.getAccountingDocumentNumber());
//                    incomeEvent.setDescriptionEconomicEvent(newIncomeEvent.getDescriptionEconomicEvent());
//                    incomeEvent.setSaleValue(newIncomeEvent.getSaleValue());
//                    incomeEvent.setOtherIncome(newIncomeEvent.getOtherIncome());
//                    incomeEvent.setTotalRevenue(newIncomeEvent.getTotalRevenue());
//                    incomeEvent.setEventNotesComments(newIncomeEvent.getEventNotesComments());
//                    incomeEvent.setBusinessContractor(newIncomeEvent.getBusinessContractor());
//                    return incomeEventRepository.save(incomeEvent);
//                }
//        ).orElseThrow(() -> new ResourceNotFoundException("Income and event not found!"));
//    }
}
