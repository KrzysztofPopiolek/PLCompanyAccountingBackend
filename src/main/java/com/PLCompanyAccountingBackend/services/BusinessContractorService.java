package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;

public class BusinessContractorService {

    private final BusinessContractorRepository businessContractorRepository;

    public BusinessContractorService(BusinessContractorRepository businessContractorRepository) {
        this.businessContractorRepository = businessContractorRepository;
    }

    public boolean checkIfContractorExists(Long id) {
        if (!businessContractorRepository.existsById(id)) {
            return false;
        }
        return true;
    }
}
