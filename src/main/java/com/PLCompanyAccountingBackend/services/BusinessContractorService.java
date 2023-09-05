package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;

public class BusinessContractorService {

    private final BusinessContractorRepository businessContractorRepository;

    public BusinessContractorService(BusinessContractorRepository businessContractorRepository) {
        this.businessContractorRepository = businessContractorRepository;
    }

    public boolean checkIfContractorExists(Long id) {
        return businessContractorRepository.existsById(id);
    }
}
