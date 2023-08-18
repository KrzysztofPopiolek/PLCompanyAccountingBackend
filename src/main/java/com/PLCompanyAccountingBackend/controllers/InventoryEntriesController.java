package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v_1/")
public class InventoryEntriesController {

    @Autowired
    private InventoryEntriesRepository inventoryEntriesRepository;


}
