package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfitCalculationRepository extends JpaRepository<ProfitCalculation, Long> {

//    Optional<Object> findAll(long dateProfitCalculationTime);
}
