package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.repository.BusinessEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v_1/")
public class BusinessEventController {
    @Autowired
    private BusinessEventRepository businessEventRepository;

    @GetMapping("/businessEventController")
    public List<BusinessEvent> getAllBusinessEvents(){
        return businessEventRepository.findAll();
    }
}
