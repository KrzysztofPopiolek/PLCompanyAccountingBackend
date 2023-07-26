package com.PLCompanyAccountingBackend.repository;

import com.PLCompanyAccountingBackend.models.BusinessEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessEventRepository extends JpaRepository<BusinessEvent, Long> {
}
