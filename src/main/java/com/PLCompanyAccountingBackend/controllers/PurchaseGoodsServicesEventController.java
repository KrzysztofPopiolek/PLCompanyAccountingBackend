package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.PurchaseGoodsServicesEvent;
import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesEventRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.MonthlySummaryService;
import com.PLCompanyAccountingBackend.services.PurchaseGoodsServicesEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class PurchaseGoodsServicesEventController {

    private final AnnualSummaryService annualSummaryService;
    private final MonthlySummaryService monthlySummaryService;
    private final PurchaseGoodsServicesEventService purchaseGoodsServicesEventService;

    @Autowired
    private PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository;

    public PurchaseGoodsServicesEventController(AnnualSummaryService annualSummaryService,
                                                MonthlySummaryService monthlySummaryService,
                                                PurchaseGoodsServicesEventService purchaseGoodsServicesEventService) {
        this.annualSummaryService = annualSummaryService;
        this.monthlySummaryService = monthlySummaryService;
        this.purchaseGoodsServicesEventService = purchaseGoodsServicesEventService;
    }

    @GetMapping("/getAllPurchaseGoodsServices")
    public List<PurchaseGoodsServicesEvent> getAllPurchaseGoodsServicesEvent() {
        return purchaseGoodsServicesEventService.getAllPurchaseGoodsServicesEvent_SortedByDate();
    }

    @GetMapping("/getPurchaseGoodsServices/{id}")
    public ResponseEntity<PurchaseGoodsServicesEvent> getPurchaseGoodsServicesEventById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseGoodsServicesEventService.getPurchaseGoodsServicesEvent_ById(id));
    }

    @PostMapping("/addPurchaseGoodsServices")
    public PurchaseGoodsServicesEvent addPurchaseGoodsServicesEvent(@RequestBody PurchaseGoodsServicesEvent purchaseGoodsServicesEvent) {
        purchaseGoodsServicesEvent.setId(0L);
        annualSummaryService.checkContractorTaxYearExists(purchaseGoodsServicesEvent);

        this.annualSummaryService.updateAnnualSummary(purchaseGoodsServicesEvent, false);
        this.monthlySummaryService.updateMonthlySummary(purchaseGoodsServicesEvent, false);
        return purchaseGoodsServicesEventRepository.save(purchaseGoodsServicesEvent);
    }

    @DeleteMapping("/deletePurchaseGoodsServices/{id}")
    public void deletePurchaseGoodsServicesEvent(@PathVariable Long id) {

        PurchaseGoodsServicesEvent purchaseGoodsServicesEvent = purchaseGoodsServicesEventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Event with provided ID does not exist"));

        this.annualSummaryService.updateAnnualSummary(purchaseGoodsServicesEvent, true);
        this.monthlySummaryService.updateMonthlySummary(purchaseGoodsServicesEvent, true);
        purchaseGoodsServicesEventRepository.deleteById(id);
    }

    @PutMapping("/editPurchaseGoodsServices/{id}")
    PurchaseGoodsServicesEvent editPurchaseGoodsServicesEvent(@RequestBody PurchaseGoodsServicesEvent newPurchaseGoodsServicesEvent, @PathVariable Long id) {
        return purchaseGoodsServicesEventRepository.findById(id).map(
                purchaseGoodsServicesEvent -> {

                    this.annualSummaryService.updateAnnualSummary(purchaseGoodsServicesEvent, true);
                    this.monthlySummaryService.updateMonthlySummary(purchaseGoodsServicesEvent, true);
                    annualSummaryService.checkContractorTaxYearExists(newPurchaseGoodsServicesEvent);
                    this.annualSummaryService.updateAnnualSummary(newPurchaseGoodsServicesEvent, false);
                    this.monthlySummaryService.updateMonthlySummary(newPurchaseGoodsServicesEvent, false);

                    purchaseGoodsServicesEvent.setDateEconomicEvent(newPurchaseGoodsServicesEvent.getDateEconomicEvent());
                    purchaseGoodsServicesEvent.setAccountingDocumentNumber(newPurchaseGoodsServicesEvent.getAccountingDocumentNumber());
                    purchaseGoodsServicesEvent.setDescriptionEconomicEvent(newPurchaseGoodsServicesEvent.getDescriptionEconomicEvent());
                    purchaseGoodsServicesEvent.setPurchaseGoodsServices(newPurchaseGoodsServicesEvent.getPurchaseGoodsServices());
                    purchaseGoodsServicesEvent.setEventNotesComments(newPurchaseGoodsServicesEvent.getEventNotesComments());
                    purchaseGoodsServicesEvent.setBusinessContractor(newPurchaseGoodsServicesEvent.getBusinessContractor());
                    return purchaseGoodsServicesEventRepository.save(purchaseGoodsServicesEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Purchase goods services cost not found!"));
    }
}
