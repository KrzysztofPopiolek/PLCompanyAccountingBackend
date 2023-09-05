package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.InventoryEntry;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.repository.InventoryGeneralDetailsRepository;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class InventoryGeneralDetailsTests {

    @Captor
    ArgumentCaptor<InventoryGeneralDetails> inventoryGeneralDetailsArgumentCaptor;
    @Mock
    private InventoryGeneralDetailsRepository inventoryGeneralDetailsRepository;
    private InventoryGeneralDetailsService inventoryGeneralDetailsService;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        inventoryGeneralDetailsService = new InventoryGeneralDetailsService(inventoryGeneralDetailsRepository);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void addEntryToGeneralDetails_savesCorrectData() {
        InventoryEntry inventoryEntry = InventoryEntry.builder().valuation(BigDecimal.TWO).build();
        List<InventoryGeneralDetails> mockInventoryGeneralDetails = new ArrayList<>();
        mockInventoryGeneralDetails.add(InventoryGeneralDetails.builder().totalInventory(BigDecimal.TWO).build());
        doReturn(mockInventoryGeneralDetails).when(inventoryGeneralDetailsRepository).findAll();

        InventoryGeneralDetails expectedInventoryGeneralDetailsEntry = InventoryGeneralDetails.builder().totalInventory(new BigDecimal(4)).build();

        inventoryGeneralDetailsService.addEntryToGeneralDetails(inventoryEntry);

        verify(inventoryGeneralDetailsRepository, times(1)).save(inventoryGeneralDetailsArgumentCaptor.capture());

        InventoryGeneralDetails capturedArgumentValue = inventoryGeneralDetailsArgumentCaptor.getValue();
        assertThat(capturedArgumentValue).usingRecursiveComparison().isEqualTo(expectedInventoryGeneralDetailsEntry);
    }
}
