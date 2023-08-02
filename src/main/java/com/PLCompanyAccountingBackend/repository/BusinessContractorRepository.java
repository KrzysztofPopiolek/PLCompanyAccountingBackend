package com.PLCompanyAccountingBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PLCompanyAccountingBackend.models.BusinessContractor;

public interface BusinessContractorRepository extends JpaRepository<BusinessContractor, Long> {
}
