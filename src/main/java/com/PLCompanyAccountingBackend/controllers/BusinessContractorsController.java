package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.repository.BusinessContractorsRepository;
import com.PLCompanyAccountingBackend.models.BusinessContractors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getBusinessContractor/{id}")
    public ResponseEntity<BusinessContractors> getBusinessContractorById(@PathVariable Long id) {
        BusinessContractors businessContractor = businessContractorsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(businessContractor);
    }

    @PostMapping("/addBusinessContractor")
    public BusinessContractors addBusinessContractors(@RequestBody BusinessContractors businessContractors) {
        return businessContractorsRepository.save(businessContractors);
    }

    @DeleteMapping("/deleteBusinessContractor/{id}")
    public void deleteBusinessContractor(@PathVariable Long id) {

        if (businessContractorsRepository.existsById(id)) {
            businessContractorsRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editBusinessContractor/{id}")
    BusinessContractors editBusinessContractor(@RequestBody BusinessContractors newBusinessContractor, @PathVariable Long id) {
        return businessContractorsRepository.findById(id).map(
                businessContractor -> {
                    businessContractor.setBusinessContractorsName(newBusinessContractor.getBusinessContractorsName());
                    businessContractor.setBusinessContractorsAddress(newBusinessContractor.getBusinessContractorsAddress());
                    return businessContractorsRepository.save(businessContractor);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Business contractor not found!"));
    }
}
