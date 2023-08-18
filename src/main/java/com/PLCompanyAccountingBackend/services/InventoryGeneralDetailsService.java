package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class InventoryGeneralDetailsService {
    private final InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository;

    public InventoryGeneralDetailsService(InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository) {
        this.inventoryGeneralDetailsRepository = inventoryGeneralDetailsRepository;
    }

    public List<InventoryGeneralDetails> getAllInventoryGeneralDetails_SortedByDate() {
        return inventoryGeneralDetailsRepository.findAll(Sort.by(Sort.Direction.ASC, "dateInventoryDetails"));
    }
    public InventoryGeneralDetails getInventoryGeneralDetails_ById(Long id) {
        return inventoryGeneralDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched inventory details not found!"));
    }
}
