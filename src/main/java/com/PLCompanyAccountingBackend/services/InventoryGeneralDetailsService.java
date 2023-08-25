package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntries;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class InventoryGeneralDetailsService {
    private final InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository;

    public InventoryGeneralDetailsService(InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository) {
        this.inventoryGeneralDetailsRepository = inventoryGeneralDetailsRepository;
    }

    public List<InventoryGeneralDetails> getAllInventoryGeneralDetails_SortedByDate() {
        return inventoryGeneralDetailsRepository.findAll(Sort.by(Sort.Direction.ASC, "inventoryDate"));
    }

    public InventoryGeneralDetails getInventoryGeneralDetails_ById(Long id) {
        return inventoryGeneralDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched inventory details not found!"));
    }

    public InventoryGeneralDetails getLastInventoryGeneralDetails() {
        List<InventoryGeneralDetails> allInventoryGeneralDetails = inventoryGeneralDetailsRepository.findAll();
        return allInventoryGeneralDetails.get(allInventoryGeneralDetails.size() - 1);
    }

    public boolean inventoryGeneralDetailsIsEmpty() {
        return inventoryGeneralDetailsRepository.findAll().isEmpty();
    }

    public void addEntryToGeneralDetails(InventoryEntries inventoryEntry) {
        InventoryGeneralDetails lastInventoryGeneralDetail = getLastInventoryGeneralDetails();
        BigDecimal totalInventory = lastInventoryGeneralDetail.getTotalInventory();

        BigDecimal newTotal = totalInventory.add(inventoryEntry.getValuation());
        lastInventoryGeneralDetail.setTotalInventory(newTotal);
        inventoryGeneralDetailsRepository.save(lastInventoryGeneralDetail);
    }
}
