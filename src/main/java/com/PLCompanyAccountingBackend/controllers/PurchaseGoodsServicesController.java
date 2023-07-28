package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.PurchaseGoodsServices;
import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class PurchaseGoodsServicesController {
    @Autowired
    private PurchaseGoodsServicesRepository purchaseGoodsServicesRepository;

    @GetMapping("/getAllPurchaseGoodsServices")
    public List<PurchaseGoodsServices> getAllPurchaseGoodsServices() {
        return purchaseGoodsServicesRepository.findAll();
    }

    @GetMapping("/getPurchaseGoodsServices/{id}")
    public ResponseEntity<PurchaseGoodsServices> getPurchaseGoodsServicesById(@PathVariable Long id) {
        PurchaseGoodsServices purchaseGoodsServices = purchaseGoodsServicesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(purchaseGoodsServices);
    }

    @PostMapping("/addPurchaseGoodsServices")
    public PurchaseGoodsServices addPurchaseGoodsServices(@RequestBody PurchaseGoodsServices purchaseGoodsServices) {
        return purchaseGoodsServicesRepository.save(purchaseGoodsServices);
    }

    @DeleteMapping("/deletePurchaseGoodsServices/{id}")
    public void deletePurchaseGoodsServices(@PathVariable Long id) {
        purchaseGoodsServicesRepository.deleteById(id);
    }

    @PutMapping("/editPurchaseGoodsServices/{id}")
    PurchaseGoodsServices editPurchaseGoodsServices(@RequestBody PurchaseGoodsServices newPurchaseGoodsServices, @PathVariable Long id) {
        return purchaseGoodsServicesRepository.findById(id).map(
                purchaseGoodsServices -> {
                    purchaseGoodsServices.setPurchaseGoodsServices(newPurchaseGoodsServices.getPurchaseGoodsServices());
                    return purchaseGoodsServicesRepository.save(purchaseGoodsServices);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Purchase goods services cost not found!"));
    }
}
