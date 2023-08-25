package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.RequestNotAcceptableException;
import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class InventoryGeneralDetailsController {
    private final InventoryGeneralDetailsService inventoryGeneralDetailsService;
    @Autowired
    private InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository;

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

        if (inventoryGeneralDetailsService.inventoryGeneralDetailsIsEmpty()) {
            if (!inventoryGeneralDetails.getIsStartInventory()) {
                throw new RequestNotAcceptableException("Cannot create ending inventory as first entry");
            }
        } else {
            if (!inventoryGeneralDetailsService.getLastInventoryGeneralDetails().getIsStartInventory() && !inventoryGeneralDetails.getIsStartInventory()) {
                throw new RequestNotAcceptableException("Cannot create second entry in a row with false isStartInventory");
            } else if (inventoryGeneralDetails.getInventoryDate().isBefore(inventoryGeneralDetailsService.getLastInventoryGeneralDetails().getInventoryDate())) {
                throw new RequestNotAcceptableException("Cannot create inventory before an existing one");
            }
        }

        //  tu wprowadziś metode wpisu/sumowanie Profit Calculation
        //  inventoryGeneralDetails.setIsStartInventory();


        inventoryGeneralDetails.setId(0L);
        inventoryGeneralDetails.setTotalInventory(new BigDecimal(0));
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
