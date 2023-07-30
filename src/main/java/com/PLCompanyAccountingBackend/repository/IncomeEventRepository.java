package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.IncomeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeEventRepository extends JpaRepository<IncomeEvent,Long>  {
}
