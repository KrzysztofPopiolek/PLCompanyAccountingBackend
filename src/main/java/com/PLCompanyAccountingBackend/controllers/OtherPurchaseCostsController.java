package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.OtherPurchaseCosts;
import com.PLCompanyAccountingBackend.repository.OtherPurchaseCostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class OtherPurchaseCostsController {
    @Autowired
    private OtherPurchaseCostsRepository otherPurchaseCostsRepository;

    @GetMapping("/getAllOtherPurchaseCosts")
    public List<OtherPurchaseCosts> getAllOtherPurchaseCosts() {
        return otherPurchaseCostsRepository.findAll();
    }
    @GetMapping("/getOtherPurchaseCosts/{id}")
    public ResponseEntity<OtherPurchaseCosts> getOtherPurchaseCostsById(@PathVariable Long id) {
        OtherPurchaseCosts otherPurchaseCosts = otherPurchaseCostsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(otherPurchaseCosts);
    }
    @PostMapping("/addOtherPurchaseCosts")
    public OtherPurchaseCosts addOtherPurchaseCosts(@RequestBody OtherPurchaseCosts otherPurchaseCosts) {
        return otherPurchaseCostsRepository.save(otherPurchaseCosts);
    }

    @DeleteMapping("/deleteOtherPurchaseCosts/{id}")
    public void deleteOtherPurchaseCosts(@PathVariable Long id) {
        otherPurchaseCostsRepository.deleteById(id);
    }

    @PutMapping("/editOtherPurchaseCosts/{id}")
    OtherPurchaseCosts editOtherPurchaseCosts(@RequestBody OtherPurchaseCosts newOtherPurchaseCosts, @PathVariable Long id) {
        return otherPurchaseCostsRepository.findById(id).map(
                otherPurchaseCosts -> {
                    otherPurchaseCosts.setOtherPurchaseCosts(newOtherPurchaseCosts.getOtherPurchaseCosts());
                    return otherPurchaseCostsRepository.save(otherPurchaseCosts);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Other purchase costs not found!"));
    }
}
