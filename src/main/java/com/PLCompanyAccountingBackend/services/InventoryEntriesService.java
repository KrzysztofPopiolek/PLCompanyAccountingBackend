package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntry;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;

import java.util.List;

public class InventoryEntriesService {
    private final InventoryEntriesRepository inventoryEntriesRepository;


    public InventoryEntriesService(InventoryEntriesRepository inventoryEntriesRepository) {
        this.inventoryEntriesRepository = inventoryEntriesRepository;
    }

    public List<InventoryEntry> getAllInventoryEntries() {
        return inventoryEntriesRepository.findAll();
    }

    public InventoryEntry getInventoryEntries_ById(Long id) {
        return inventoryEntriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Searched inventory not found!"));
    }
}
