package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.InventoryEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryEntriesRepository extends JpaRepository<InventoryEntries, Long> {
}
