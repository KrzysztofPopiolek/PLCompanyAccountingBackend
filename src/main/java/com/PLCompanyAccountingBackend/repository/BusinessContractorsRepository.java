package com.PLCompanyAccountingBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PLCompanyAccountingBackend.models.BusinessContractors;

public interface BusinessContractorsRepository extends JpaRepository<BusinessContractors, Long> {
}
