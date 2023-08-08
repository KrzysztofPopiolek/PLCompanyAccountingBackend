package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseEventRepository extends JpaRepository<ExpenseEvent, Long> {
}
