package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualSummaryRepository extends JpaRepository<AnnualSummary, Long> {
}
