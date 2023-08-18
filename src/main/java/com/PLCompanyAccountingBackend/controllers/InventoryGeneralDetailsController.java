package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class InventoryGeneralDetailsController {
    @Autowired
    private InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository;

    private final InventoryGeneralDetailsService inventoryGeneralDetailsService;

    public InventoryGeneralDetailsController(InventoryGeneralDetailsService inventoryGeneralDetailsService) {
        this.inventoryGeneralDetailsService = inventoryGeneralDetailsService;
    }

    @GetMapping("/getAllInventoryGeneralDetails")
    public List<InventoryGeneralDetails> getAllInventoryGeneralDetails() {
        return inventoryGeneralDetailsService.getAllInventoryGeneralDetails_SortedByDate();
    }

    @GetMapping("/getInventoryGeneralDetails/{id}")
    public ResponseEntity<InventoryGeneralDetails> getInventoryGeneralDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryGeneralDetailsService.getInventoryGeneralDetails_ById(id));
    }

    @PostMapping("/addInventoryGeneralDetails")
    public InventoryGeneralDetails addInventoryGeneralDetails(@RequestBody InventoryGeneralDetails inventoryGeneralDetails) {
        inventoryGeneralDetails.setId(0L);



        return inventoryGeneralDetailsRepository.save(inventoryGeneralDetails);
    }

    @DeleteMapping("/deleteInventoryGeneralDetails/{id}")
    public void deleteInventoryGeneralDetails(@PathVariable Long id) {

        InventoryGeneralDetails inventoryGeneralDetails = inventoryGeneralDetailsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Inventory details, with provided ID does not exist"));
        inventoryGeneralDetailsRepository.deleteById(id);
    }

    @PutMapping("/editInventoryGeneralDetails/{id}")
    InventoryGeneralDetails editInventoryGeneralDetails(@RequestBody InventoryGeneralDetails newInventoryGeneralDetails, @PathVariable Long id) {
        return inventoryGeneralDetailsRepository.findById(id).map(
                inventoryGeneralDetails -> {
                    inventoryGeneralDetails.setInventoryDate(newInventoryGeneralDetails.getInventoryDate());
                    inventoryGeneralDetails.setInventoryBusinessName(newInventoryGeneralDetails.getInventoryBusinessName());
                    inventoryGeneralDetails.setInventoryAuthorsName(newInventoryGeneralDetails.getInventoryAuthorsName());
                    inventoryGeneralDetails.setInventoryCompanyOwnerOrManager(newInventoryGeneralDetails.getInventoryCompanyOwnerOrManager());
                    inventoryGeneralDetails.setTotalInventory(newInventoryGeneralDetails.getTotalInventory());
                    inventoryGeneralDetails.setIsStartInventory(newInventoryGeneralDetails.getIsStartInventory());
                    return inventoryGeneralDetailsRepository.save(inventoryGeneralDetails);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));
    }
}
