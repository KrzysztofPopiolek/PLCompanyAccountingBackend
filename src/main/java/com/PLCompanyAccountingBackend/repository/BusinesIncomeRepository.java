package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.BusinessIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinesIncomeRepository extends JpaRepository <BusinessIncome,Long> {
}
