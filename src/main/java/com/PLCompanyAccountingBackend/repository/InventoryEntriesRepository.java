package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.InventoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryEntriesRepository extends JpaRepository<InventoryEntry, Long> {
}
