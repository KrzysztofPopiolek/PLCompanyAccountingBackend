package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitCalculationRepository extends JpaRepository<ProfitCalculation, Long> {

//    Optional<Object> findAll(long dateProfitCalculationTime);
}
