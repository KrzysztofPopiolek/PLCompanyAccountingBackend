package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.enums.AccountingMeasureUnits;
import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.InventoryEntries;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class InventoryEntriesService {
    private final InventoryEntriesRepository inventoryEntriesRepository;

    public InventoryEntriesService(InventoryEntriesRepository inventoryEntriesRepository) {
        this.inventoryEntriesRepository = inventoryEntriesRepository;
    }

    public List<InventoryEntries> getAllInventoryEntries() {
        List<InventoryEntries> inventoryEntries = inventoryEntriesRepository.findAll();

//        for (int i = 0; i < inventoryEntries.size(); i++) {
//            System.out.println(inventoryEntries.get(i).getGoodsName());
//            //inventoryEntries.get(i).setUnitsType(inventoryEntries.get(i).getUnitsType().getDisplayName());
//        }
//
//        AccountingMeasureUnits units = AccountingMeasureUnits.LITRE;
//        System.out.println(units.getDisplayName());

        return inventoryEntriesRepository.findAll();
    }

    public InventoryEntries getInventoryEntries_ById(Long id) {
        return inventoryEntriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched inventory not found!"));
    }
}
