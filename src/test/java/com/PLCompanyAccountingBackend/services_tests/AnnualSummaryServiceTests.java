package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.BusinessEvent;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.BusinessContractorService;
import com.PLCompanyAccountingBackend.services.SummaryService;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AnnualSummaryServiceTests {

    private AnnualSummaryService annualSummaryService;

    @Mock
    private AnnualSummaryRepository annualSummaryRepository;
    @Mock
    private BusinessContractorService businessContractorService;
    @Mock
    private SummaryService summaryService;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        annualSummaryService = new AnnualSummaryService(annualSummaryRepository, businessContractorService, summaryService);

    }
    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void updateAnnualSummary_callsAnnualSummaryRepositorySave_exactlyOnce() {
        LocalDate mockDate = LocalDate.of(2020,1,1);

        IncomeEvent mockBusinessEvent = IncomeEvent.builder().dateEconomicEvent(mockDate).build();

        //Mocking data in repo
        List<AnnualSummary> mockAnnualSummaries = new ArrayList<>(1);
        mockAnnualSummaries.add(AnnualSummary.builder().date(mockDate).build());
        doReturn(mockAnnualSummaries).when(annualSummaryRepository).findAll();

        doReturn(AnnualSummary.builder().date(mockDate).build()).when(summaryService).createNewSummary(eq(mockBusinessEvent), Mockito.any(Summary.class), eq(true));

        annualSummaryService.updateAnnualSummary(mockBusinessEvent, true);

        verify(summaryService, times(1)).createNewSummary(eq(mockBusinessEvent), Mockito.any(Summary.class), eq(true));
        verify(annualSummaryRepository, times(1)).save(Mockito.any(AnnualSummary.class));

    }
}
