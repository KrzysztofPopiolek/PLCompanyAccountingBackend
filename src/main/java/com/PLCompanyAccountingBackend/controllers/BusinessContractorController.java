package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.BusinessContractor;
import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessContractorController {
    @Autowired
    private BusinessContractorRepository businessContractorRepository;

    @GetMapping("/getAllBusinessContractors")
    public List<BusinessContractor> getAllBusinessContractors() {
        return businessContractorRepository.findAll();
    }

    @GetMapping("/getBusinessContractor/{id}")
    public ResponseEntity<BusinessContractor> getBusinessContractorById(@PathVariable Long id) {
        BusinessContractor businessContractor = businessContractorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(businessContractor);
    }

    @PostMapping("/addBusinessContractor")
    public BusinessContractor addBusinessContractor(@RequestBody BusinessContractor businessContractors) {
        businessContractors.setId(0L);
        return businessContractorRepository.save(businessContractors);
    }

    @DeleteMapping("/deleteBusinessContractor/{id}")
    public void deleteBusinessContractor(@PathVariable Long id) {

        if (businessContractorRepository.existsById(id)) {
            businessContractorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editBusinessContractor/{id}")
    BusinessContractor editBusinessContractor(@RequestBody BusinessContractor newBusinessContractor, @PathVariable Long id) {
        return businessContractorRepository.findById(id).map(
                businessContractor -> {
                    businessContractor.setBusinessContractorName(newBusinessContractor.getBusinessContractorName());
                    businessContractor.setBusinessContractorAddress(newBusinessContractor.getBusinessContractorAddress());
                    return businessContractorRepository.save(businessContractor);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business contractor not found!"));
    }
}
