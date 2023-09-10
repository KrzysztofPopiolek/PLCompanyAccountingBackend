package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.InventoryEntry;
import com.PLCompanyAccountingBackend.repository.InventoryEntriesRepository;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import com.PLCompanyAccountingBackend.services.InventoryEntriesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class InventoryEntriesServiceTests {

    private InventoryEntriesService inventoryEntriesService;

    @Mock
    private InventoryEntriesRepository inventoryEntriesRepository;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        inventoryEntriesService = new InventoryEntriesService(inventoryEntriesRepository);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllInventoryEntries_returnsInventoryEntries(){
        //arrange
        List<InventoryEntry> mockInventoryEntries = new ArrayList<>();

        mockInventoryEntries.add(InventoryEntry.builder()
                .id(1L)
                .valuation(BigDecimal.ONE)
                .build());

        doReturn(mockInventoryEntries).when(inventoryEntriesRepository).findAll();

        //act
        List<InventoryEntry> inventoryEntries = inventoryEntriesService.getAllInventoryEntries();

        //assert
        Assertions.assertEquals(mockInventoryEntries, inventoryEntries);
    }
}
