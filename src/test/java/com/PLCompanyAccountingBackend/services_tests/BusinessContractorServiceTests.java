package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class BusinessContractorServiceTests {

    @Mock
    private BusinessContractorRepository businessContractorRepository;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}
