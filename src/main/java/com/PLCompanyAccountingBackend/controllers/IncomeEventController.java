package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class IncomeEventController {

    private final IncomeEventService incomeEventService;
    private final MonthlySummaryService monthlySummaryService;
    private final AnnualSummaryService annualSummaryService;
    @Autowired
    private IncomeEventRepository incomeEventRepository;

    public IncomeEventController(IncomeEventService incomeEventService,
                                 MonthlySummaryService monthlySummaryService,
                                 AnnualSummaryService annualSummaryService) {
        this.incomeEventService = incomeEventService;
        this.monthlySummaryService = monthlySummaryService;
        this.annualSummaryService = annualSummaryService;
    }

    @GetMapping("/getAllIncome&Event")
    public List<IncomeEvent> getAllIncomeEvent() {
        return incomeEventService.getAllIncomeEvent_SortedByDate();
    }

    @GetMapping("/getIncome&Event/{id}")
    public ResponseEntity<IncomeEvent> getIncomeEventById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeEventService.getIncomeEvent_ById(id));
    }

    @PostMapping("/addIncome&Event")
    public IncomeEvent addIncomeEvent(@RequestBody IncomeEvent incomeEvent) {

        incomeEvent.setId(0L);
        annualSummaryService.checkContractorTaxYearExists(incomeEvent);

        BigDecimal saleValue = incomeEvent.getSaleValue() == null ? new BigDecimal(0) : incomeEvent.getSaleValue();
        BigDecimal otherIncome = incomeEvent.getOtherIncome() == null ? new BigDecimal(0) : incomeEvent.getOtherIncome();
        incomeEvent.setTotalRevenue(saleValue.add(otherIncome));
        this.annualSummaryService.updateAnnualSummary(incomeEvent, false);
        this.monthlySummaryService.updateMonthlySummary(incomeEvent, false);
        return incomeEventRepository.save(incomeEvent);
    }

    @DeleteMapping("/deleteIncome&BusinessEvent/{id}")
    public void deleteIncomeEvent(@PathVariable Long id) {
        IncomeEvent incomeEvent = incomeEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with provided ID does not exist."));

        this.annualSummaryService.updateAnnualSummary(incomeEvent, true);
        this.monthlySummaryService.updateMonthlySummary(incomeEvent, true);
        incomeEventRepository.deleteById(id);
    }

    @PutMapping("/editIncome&BusinessEvent/{id}")
    IncomeEvent editIncomeEvent(@RequestBody IncomeEvent newIncomeEvent, @PathVariable Long id) {
        return incomeEventRepository.findById(id).map(
                incomeEvent -> {
                    annualSummaryService.checkContractorTaxYearExists(newIncomeEvent);

                    this.annualSummaryService.updateAnnualSummary(incomeEvent, true);
                    this.monthlySummaryService.updateMonthlySummary(incomeEvent, true);
                    newIncomeEvent.setTotalRevenue(newIncomeEvent.getSaleValue().add(newIncomeEvent.getOtherIncome()));
                    this.annualSummaryService.updateAnnualSummary(newIncomeEvent, false);
                    this.monthlySummaryService.updateMonthlySummary(newIncomeEvent, false);

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
