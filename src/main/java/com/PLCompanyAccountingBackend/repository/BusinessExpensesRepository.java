package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.BusinessExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessExpensesRepository extends JpaRepository<BusinessExpenses, Long> {
}
