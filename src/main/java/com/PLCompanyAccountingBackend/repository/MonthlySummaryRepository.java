package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.MonthlySummary;
import com.PLCompanyAccountingBackend.models.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlySummaryRepository extends JpaRepository<MonthlySummary, Long> {
}
