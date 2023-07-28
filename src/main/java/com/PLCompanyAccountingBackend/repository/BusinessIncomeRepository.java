package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.BusinessIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessIncomeRepository extends JpaRepository <BusinessIncome,Long> {
}
