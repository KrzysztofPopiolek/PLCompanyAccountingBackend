package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceIsLockedException;
import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntries;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import com.PLCompanyAccountingBackend.services.InventoryEntriesService;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class InventoryEntriesController {

    private final InventoryEntriesService inventoryEntriesService;
    private final InventoryGeneralDetailsService inventoryGeneralDetailsService;

    @Autowired
    private InventoryEntriesRepository inventoryEntriesRepository;

    public InventoryEntriesController(InventoryEntriesService inventoryEntriesService,
                                      InventoryGeneralDetailsService inventoryGeneralDetailsService) {
        this.inventoryEntriesService = inventoryEntriesService;
        this.inventoryGeneralDetailsService = inventoryGeneralDetailsService;
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
    public InventoryEntries addInventoryEntries(@RequestBody InventoryEntries inventoryEntry) {

        if (inventoryGeneralDetailsService.getAllInventoryGeneralDetails_SortedByDate().size() == 0) {
            throw new ResourceNotFoundException("No entries in inventoryGeneralDetails");
        } else if (!inventoryGeneralDetailsService.getLastInventoryGeneralDetails().getIsStartInventory()) {
            throw new ResourceIsLockedException("Inventory entries locked");
        }
        inventoryEntry.setId(0L);

        InventoryGeneralDetails inventoryGeneralDetails_forID = new InventoryGeneralDetails();
        inventoryGeneralDetails_forID.setId(inventoryGeneralDetailsService.getLastInventoryGeneralDetails().getId());
        inventoryEntry.setInventoryGeneralDetails(inventoryGeneralDetails_forID);

        BigDecimal amount = inventoryEntry.getAmount() == null ? new BigDecimal(0) : inventoryEntry.getAmount();
        BigDecimal pricePerUnit = inventoryEntry.getPricePerUnit() == null ? new BigDecimal(0) : inventoryEntry.getPricePerUnit();
        inventoryEntry.setValuation(amount.multiply(pricePerUnit));

        inventoryGeneralDetailsService.addEntryToGeneralDetails(inventoryEntry);

        return inventoryEntriesRepository.save(inventoryEntry);
    }
    @DeleteMapping("/deleteInventoryEntries/{id}")
    public void deleteInventoryEntries(@PathVariable Long id) {
        inventoryEntriesRepository.findById(id).orElseThrow(() ->
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
                    return inventoryEntriesRepository.save(inventoryEntries);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));
    }
}
