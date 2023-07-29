package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCosts;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class ResearchDevelopmentActivitiesCostsController {
    @Autowired
    private ResearchDevelopmentActivitiesCostsRepository researchDevelopmentActivitiesCostsRepository;

    @GetMapping("/getAllResearchDevelopmentActivitiesCosts")
    public List<ResearchDevelopmentActivitiesCosts> getAllResearchDevelopmentActivitiesCosts() {
        return researchDevelopmentActivitiesCostsRepository.findAll();
    }

    @GetMapping("/getResearchDevelopmentActivitiesCosts/{id}")
    public ResponseEntity<ResearchDevelopmentActivitiesCosts> getResearchDevelopmentActivitiesCostsById(@PathVariable Long id) {
        ResearchDevelopmentActivitiesCosts researchDevelopmentActivitiesCosts = researchDevelopmentActivitiesCostsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(researchDevelopmentActivitiesCosts);
    }

    @PostMapping("/addResearchDevelopmentActivitiesCosts")
    public ResearchDevelopmentActivitiesCosts addResearchDevelopmentActivitiesCosts(@RequestBody ResearchDevelopmentActivitiesCosts researchDevelopmentActivitiesCosts) {
        return researchDevelopmentActivitiesCostsRepository.save(researchDevelopmentActivitiesCosts);
    }

    @DeleteMapping("/deleteResearchDevelopmentActivitiesCosts/{id}")
    public void deleteResearchDevelopmentActivitiesCosts(@PathVariable Long id) {
        if (researchDevelopmentActivitiesCostsRepository.existsById(id)) {
            researchDevelopmentActivitiesCostsRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editResearchDevelopmentActivitiesCosts/{id}")
    ResearchDevelopmentActivitiesCosts editResearchDevelopmentActivitiesCosts(@RequestBody ResearchDevelopmentActivitiesCosts newResearchDevelopmentActivitiesCosts, @PathVariable Long id) {
        return researchDevelopmentActivitiesCostsRepository.findById(id).map(
                researchDevelopmentActivitiesCosts -> {
                    researchDevelopmentActivitiesCosts.setResearchDevelopmentActivitiesCosts(newResearchDevelopmentActivitiesCosts.getResearchDevelopmentActivitiesCosts());
                    return researchDevelopmentActivitiesCostsRepository.save(researchDevelopmentActivitiesCosts);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Research development activities costs not found!"));
    }
}
