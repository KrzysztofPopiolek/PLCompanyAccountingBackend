package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntries;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;

import java.util.List;

public class InventoryEntriesService {
    private final InventoryEntriesRepository inventoryEntriesRepository;


    public InventoryEntriesService(InventoryEntriesRepository inventoryEntriesRepository) {
        this.inventoryEntriesRepository = inventoryEntriesRepository;

    }

    public List<InventoryEntries> getAllInventoryEntries() {
        return inventoryEntriesRepository.findAll();
    }

    public InventoryEntries getInventoryEntries_ById(Long id) {
        return inventoryEntriesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Searched inventory not found!"));
    }
}
