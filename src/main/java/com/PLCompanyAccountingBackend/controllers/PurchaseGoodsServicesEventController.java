package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.PurchaseGoodsServicesEvent;
import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class PurchaseGoodsServicesEventController {
    @Autowired
    private PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository;

    @Autowired
    private BusinessContractorRepository businessContractorRepository;

    @GetMapping("/getAllPurchaseGoodsServices")
    public List<PurchaseGoodsServicesEvent> getAllPurchaseGoodsServicesEvent() {
        return purchaseGoodsServicesEventRepository.findAll();
    }

    @GetMapping("/getPurchaseGoodsServices/{id}")
    public ResponseEntity<PurchaseGoodsServicesEvent> getPurchaseGoodsServicesEventById(@PathVariable Long id) {
        PurchaseGoodsServicesEvent purchaseGoodsServicesEvent = purchaseGoodsServicesEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(purchaseGoodsServicesEvent);
    }

    @PostMapping("/addPurchaseGoodsServices")
    public PurchaseGoodsServicesEvent addPurchaseGoodsServicesEvent(@RequestBody PurchaseGoodsServicesEvent purchaseGoodsServicesEvent) {
        if (!businessContractorRepository.existsById(purchaseGoodsServicesEvent.getId())) {
            throw new ResourceNotFoundException("Contractor not found");
        }
        return purchaseGoodsServicesEventRepository.save(purchaseGoodsServicesEvent);
    }

    @DeleteMapping("/deletePurchaseGoodsServices/{id}")
    public void deletePurchaseGoodsServicesEvent(@PathVariable Long id) {

        if (purchaseGoodsServicesEventRepository.existsById(id)) {
            purchaseGoodsServicesEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editPurchaseGoodsServices/{id}")
    PurchaseGoodsServicesEvent editPurchaseGoodsServicesEvent(@RequestBody PurchaseGoodsServicesEvent newPurchaseGoodsServicesEvent, @PathVariable Long id) {
        return purchaseGoodsServicesEventRepository.findById(id).map(
                purchaseGoodsServicesEvent -> {
                    if (!businessContractorRepository.existsById(newPurchaseGoodsServicesEvent.getId())) {
                        throw new ResourceNotFoundException("Contractor not found");
                    }
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
