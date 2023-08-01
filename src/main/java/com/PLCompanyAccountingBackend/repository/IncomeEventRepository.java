package com.PLCompanyAccountingBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PLCompanyAccountingBackend.models.IncomeEvent;

public interface IncomeEventRepository extends JpaRepository<IncomeEvent,Long>  {
}
