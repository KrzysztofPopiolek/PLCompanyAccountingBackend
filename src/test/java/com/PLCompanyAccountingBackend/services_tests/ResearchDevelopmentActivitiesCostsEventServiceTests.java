package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.ResearchDevelopmentActivitiesCostsEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.ResearchDevelopmentActivitiesCostsEventRepository;
import com.PLCompanyAccountingBackend.services.ResearchDevelopmentActivitiesCostsEventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void getAllSortedByDate_valid_resAndDevList() {
        List<ResearchDevelopmentActivitiesCostsEvent> mockResAndDevEvent = new ArrayList<ResearchDevelopmentActivitiesCostsEvent>(1);
        mockResAndDevEvent.add(ResearchDevelopmentActivitiesCostsEvent.builder().build());
        doReturn(mockResAndDevEvent).when(researchDevelopmentActivitiesCostsEventRepository).findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
        List<ResearchDevelopmentActivitiesCostsEvent> allResAndDevEvents = researchDevelopmentActivitiesCostsEventService.getAllResearchDevelopmentActivitiesCostsEvent_SortedByDate();
        Assertions.assertEquals(mockResAndDevEvent, allResAndDevEvents);
    }

    @Test
    public void createEntryForSummary_deleteModeTrue_returnsSummaryObject() {
        Boolean inDeleteMode = true;

        ResearchDevelopmentActivitiesCostsEvent mockResAndDevEvent = ResearchDevelopmentActivitiesCostsEvent.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.ONE)
                .build();

        Summary mockSummary = Summary.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.ONE)
                .build();

        Summary expectedSummary = Summary.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.ZERO)
                .build();

        Summary summary = researchDevelopmentActivitiesCostsEventService.createEntryForSummary(mockResAndDevEvent, mockSummary, inDeleteMode);

        assertThat(summary).usingRecursiveComparison().isEqualTo(expectedSummary);
    }

    @Test
    public void createEntryForSummary_deleteModeFalse_returnsSummaryObject() {
        Boolean inDeleteMode = false;

        ResearchDevelopmentActivitiesCostsEvent mockResAndDevEvent = ResearchDevelopmentActivitiesCostsEvent.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.ONE)
                .build();

        Summary mockSummary = Summary.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.ONE)
                .build();

        Summary expectedSummary = Summary.builder()
                .researchDevelopmentActivitiesCosts(BigDecimal.TWO)
                .build();

        Summary summary = researchDevelopmentActivitiesCostsEventService.createEntryForSummary(mockResAndDevEvent, mockSummary, inDeleteMode);

        assertThat(summary).usingRecursiveComparison().isEqualTo(expectedSummary);
    }
}
