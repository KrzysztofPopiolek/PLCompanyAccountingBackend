package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.models.BusinessContractors;
import com.PLCompanyAccountingBackend.repository.BusinessContractorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessContractorsController {
    @Autowired
    private BusinessContractorsRepository businessContractorsRepository;

    @GetMapping("/getAllBusinessContractors")
    public List<BusinessContractors> getAllBusinessContractors() {
        return businessContractorsRepository.findAll();
    }
}
