package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntries;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import com.PLCompanyAccountingBackend.services.InventoryEntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class InventoryEntriesController {

    private final InventoryEntriesService inventoryEntriesService;

    @Autowired
    private InventoryEntriesRepository inventoryEntriesRepository;

    public InventoryEntriesController(InventoryEntriesService inventoryEntriesService) {
        this.inventoryEntriesService = inventoryEntriesService;
    }

    @GetMapping("/getAllInventoryEntries")
    public List<InventoryEntries> getAllInventoryEntries() {
        return inventoryEntriesService.getAllInventoryEntries();
    }

    @GetMapping("/getInventoryEntries/{id}")
    public ResponseEntity<InventoryEntries> getInventoryEntriesById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryEntriesService.getInventoryEntries_ById(id));
    }

    @PostMapping("/addInventoryEntries")
    public InventoryEntries addInventoryEntries(@RequestBody InventoryEntries inventoryEntries) {
        inventoryEntries.setId(0L);

        BigDecimal amount = inventoryEntries.getAmount() == null ? new BigDecimal(0): inventoryEntries.getAmount();
        BigDecimal pricePerUnit = inventoryEntries.getPricePerUnit() == null ?  new BigDecimal(0): inventoryEntries.getPricePerUnit();
        inventoryEntries.setValuation(amount.multiply(pricePerUnit));
        return inventoryEntriesRepository.save(inventoryEntries);
    }

    @DeleteMapping("/deleteInventoryEntries/{id}")
    public void deleteInventoryEntries(@PathVariable Long id) {

        InventoryEntries inventoryEntries = inventoryEntriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Inventory with provided ID does not exist"));
        inventoryEntriesRepository.deleteById(id);
    }

    @PutMapping("/editInventoryEntries/{id}")
    InventoryEntries editInventoryEntries(@RequestBody InventoryEntries newInventoryEntries, @PathVariable Long id) {
        return inventoryEntriesRepository.findById(id).map(
                inventoryEntries -> {
                    inventoryEntries.setGoodsName(newInventoryEntries.getGoodsName());
                    inventoryEntries.setUnitsType(newInventoryEntries.getUnitsType());
                    inventoryEntries.setAmount(newInventoryEntries.getAmount());
                    inventoryEntries.setPricePerUnit(newInventoryEntries.getPricePerUnit());
                    inventoryEntries.setValuation(newInventoryEntries.getValuation());
                    inventoryEntries.setInventoryGeneralDetails(newInventoryEntries.getInventoryGeneralDetails());
                    return inventoryEntriesRepository.save(inventoryEntries);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));
    }

}
