package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCostsEvent;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import com.PLCompanyAccountingBackend.services.ResearchDevelopmentActivitiesCostsEventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

public class ResearchDevelopmentActivitiesCostsEventServiceTests {

    private ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService;
    @Mock
    private ResearchDevelopmentActivitiesCostsEventRepository researchDevelopmentActivitiesCostsEventRepository;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        researchDevelopmentActivitiesCostsEventService = new ResearchDevelopmentActivitiesCostsEventService(researchDevelopmentActivitiesCostsEventRepository);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void getById_ValidId() {
        ResearchDevelopmentActivitiesCostsEvent mockEvent = ResearchDevelopmentActivitiesCostsEvent.builder().build();
        doReturn(Optional.of(mockEvent)).when(researchDevelopmentActivitiesCostsEventRepository).findById(1L);
        ResearchDevelopmentActivitiesCostsEvent event = researchDevelopmentActivitiesCostsEventService.getResearchDevelopmentActivitiesCostsEvent_ById(1L);
        Assertions.assertEquals(mockEvent, event);
    }

    @Test
    public void getById_InvalidId() {
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () ->
                researchDevelopmentActivitiesCostsEventService.getResearchDevelopmentActivitiesCostsEvent_ById(1L));
        Assertions.assertEquals("Searched item not found!", exception.getMessage());
    }
}
