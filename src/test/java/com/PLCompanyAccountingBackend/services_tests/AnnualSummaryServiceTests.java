package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.SummaryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AnnualSummaryServiceTests {

    private AnnualSummaryService annualSummaryService;

    @Mock
    private AnnualSummaryRepository annualSummaryRepository;

    @Mock
    private SummaryService summaryService;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        annualSummaryService = new AnnualSummaryService(annualSummaryRepository, summaryService);

    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void updateAnnualSummary_callsAnnualSummaryRepositorySave_withCorrectData() {
        LocalDate mockDate = LocalDate.of(2020, 1, 1);

        IncomeEvent mockBusinessEvent = IncomeEvent.builder().dateEconomicEvent(mockDate).build();

        //Mocking data in repo
        List<AnnualSummary> mockAnnualSummaries = new ArrayList<>(1);
        AnnualSummary mockAnnualSummary = AnnualSummary.builder().date(mockDate).build();
        mockAnnualSummaries.add(mockAnnualSummary);
        doReturn(mockAnnualSummaries).when(annualSummaryRepository).findAll();
        doReturn(mockAnnualSummary).when(summaryService).createNewSummary(eq(mockBusinessEvent), Mockito.any(Summary.class), eq(true));

        annualSummaryService.updateAnnualSummary(mockBusinessEvent, true);

        verify(annualSummaryRepository, times(1)).save(mockAnnualSummary);

    }

    @Test
    public void taxYearExists_yearExists_returnsTrue() {
        List<AnnualSummary> mockAnnualSummaries = new ArrayList<>();
        mockAnnualSummaries.add(AnnualSummary.builder().date(LocalDate.of(2020, 1, 1)).build());
        doReturn(mockAnnualSummaries).when(annualSummaryRepository).findAll();
        Boolean taxYearExists = annualSummaryService.taxYearExists(2020);
        Assertions.assertEquals(taxYearExists, true);
    }

    @Test
    public void taxYearExists_yearDoesNotExists_returnsFalse() {
        List<AnnualSummary> mockAnnualSummaries = new ArrayList<>();
        mockAnnualSummaries.add(AnnualSummary.builder().date(LocalDate.of(2021, 1, 1)).build());
        doReturn(mockAnnualSummaries).when(annualSummaryRepository).findAll();

        Boolean taxYearExists = annualSummaryService.taxYearExists(2020);

        Assertions.assertEquals(taxYearExists, false);
    }

    @Test
    public void getLastAnnualSummary_returnsLastElementInAnnualSummaryRepo() {
        List<AnnualSummary> mockAnnualSummaries = new ArrayList<>();
        mockAnnualSummaries.add(AnnualSummary.builder().id(1L).build());
        AnnualSummary mockLastEntry = AnnualSummary.builder().id(3L).build();
        mockAnnualSummaries.add(mockLastEntry);
        doReturn(mockAnnualSummaries).when(annualSummaryRepository).findAll();

        AnnualSummary annualSummary = annualSummaryService.getLastAnnualSummary();

        assertThat(annualSummary).usingRecursiveComparison().isEqualTo(mockLastEntry);
    }
}
