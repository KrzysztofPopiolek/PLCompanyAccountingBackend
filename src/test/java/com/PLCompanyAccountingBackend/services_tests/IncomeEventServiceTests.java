package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

public class IncomeEventServiceTests {


    private IncomeEventService incomeEventService;
    @Mock
    private IncomeEventRepository incomeEventRepository;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        incomeEventService = new IncomeEventService(incomeEventRepository);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void getById_ValidId() {
        IncomeEvent mockIncomeEvent = IncomeEvent.builder().build();
        doReturn(Optional.of(mockIncomeEvent)).when(incomeEventRepository).findById(1L);
        IncomeEvent incomeEvent = incomeEventService.getIncomeEvent_ById(1L);
        Assertions.assertEquals(mockIncomeEvent, incomeEvent);
    }

    @Test
    public void getById_InvalidId() {
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () ->
                incomeEventService.getIncomeEvent_ById(1L));
        Assertions.assertEquals("Searched item not found!", exception.getMessage());
    }

}
