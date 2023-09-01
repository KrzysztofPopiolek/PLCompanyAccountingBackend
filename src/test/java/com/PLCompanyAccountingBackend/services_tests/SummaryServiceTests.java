package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.*;
import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
import com.PLCompanyAccountingBackend.services.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SummaryServiceTests {

    @Mock
    private AnnualSummaryRepository annualSummaryRepository;
    @Mock
    private MonthlySummaryRepository monthlySummaryRepository;
    @Mock
    private ExpenseEventService expenseEventService;
    @Mock
    private IncomeEventService incomeEventService;
    @Mock
    private OtherPurchaseCostsEventService otherPurchaseCostsEventService;
    @Mock
    private PurchaseGoodsServicesEventService purchaseGoodsServicesEventService;
    @Mock
    private ResearchDevelopmentActivitiesCostsEventService researchDevelopmentActivitiesCostsEventService;

    private SummaryService summaryService;

    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        summaryService = new SummaryService(expenseEventService, incomeEventService,
                otherPurchaseCostsEventService, purchaseGoodsServicesEventService,
                researchDevelopmentActivitiesCostsEventService,
                annualSummaryRepository, monthlySummaryRepository);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void createNewSummary_expenseEvent_callsCreateEntryForSummary() {
        ExpenseEvent mockEvent = ExpenseEvent.builder().build();
        Summary mockSummary = Summary.builder().build();
        summaryService.createNewSummary(mockEvent, mockSummary, true);
        verify(expenseEventService, times(1)).createEntryForSummary(mockEvent, mockSummary, true);
    }

    @Test
    public void createNewSummary_incomeEventEvent_callsCreateEntryForSummary() {
        IncomeEvent mockEvent = IncomeEvent.builder().build();
        Summary mockSummary = Summary.builder().build();
        summaryService.createNewSummary(mockEvent, mockSummary, true);
        verify(incomeEventService, times(1)).createEntryForSummary(mockEvent, mockSummary, true);
    }

    @Test
    public void createNewSummary_otherPurchaseCostsEvent_callsCreateEntryForSummary() {
        OtherPurchaseCostsEvent mockEvent = OtherPurchaseCostsEvent.builder().build();
        Summary mockSummary = Summary.builder().build();
        summaryService.createNewSummary(mockEvent, mockSummary, true);
        verify(otherPurchaseCostsEventService, times(1)).createEntryForSummary(mockEvent, mockSummary, true);
    }

    @Test
    public void createNewSummary_purchaseGoodsServicesEvent_callsCreateEntryForSummary() {
        PurchaseGoodsServicesEvent mockEvent = PurchaseGoodsServicesEvent.builder().build();
        Summary mockSummary = Summary.builder().build();
        summaryService.createNewSummary(mockEvent, mockSummary, true);
        verify(purchaseGoodsServicesEventService, times(1)).createEntryForSummary(mockEvent, mockSummary, true);
    }

    @Test
    public void createNewSummary_researchDevelopmentActivitiesCostsEvent_callsCreateEntryForSummary() {
        ResearchDevelopmentActivitiesCostsEvent mockEvent = ResearchDevelopmentActivitiesCostsEvent.builder().build();
        Summary mockSummary = Summary.builder().build();
        summaryService.createNewSummary(mockEvent, mockSummary, true);
        verify(researchDevelopmentActivitiesCostsEventService, times(1)).createEntryForSummary(mockEvent, mockSummary, true);
    }

    @Test
    public void addMonthsAndYearToSummaries_yearExists_returnsOriginalSummary() {
        //Mocking data in repo
        LocalDate mockDate = LocalDate.of(2020, 1, 1);
        List<AnnualSummary> mockAnnualSummary = new ArrayList<>(1);
        mockAnnualSummary.add(AnnualSummary.builder().date(mockDate).build());
        doReturn(mockAnnualSummary).when(annualSummaryRepository).findAll();

        Summary expectedAnnualSummary = AnnualSummary.builder().date(mockDate).build();

        Summary annualSummary = summaryService.addMonthsAndYearToSummaries(mockDate);

        assertThat(annualSummary).usingRecursiveComparison().isEqualTo(expectedAnnualSummary);
    }

    @Test
    public void addMonthsAndYearToSummaries_yearDoesNotExist_returnsAnnualSummaryWithNewYear() {
        //Data to mock data stored in repo
        LocalDate mockDateInRepo = LocalDate.of(2020, 1, 1);
        List<AnnualSummary> mockAnnualSummary_repo = new ArrayList<>(1);
        mockAnnualSummary_repo.add(AnnualSummary.builder().date(mockDateInRepo).build());
        doReturn(mockAnnualSummary_repo).when(annualSummaryRepository).findAll();

        //Data to mock data passed into repo
        LocalDate newMockDate = LocalDate.of(2021, 1, 1);
        AnnualSummary mockAnnualSummary = AnnualSummary.builder().date(newMockDate).build();
        doReturn(mockAnnualSummary).when(annualSummaryRepository).save(Mockito.any(AnnualSummary.class));

        AnnualSummary expectedAnnualSummary = AnnualSummary.builder().date(newMockDate).build();
        AnnualSummary annualSummary = summaryService.addMonthsAndYearToSummaries(newMockDate);

        assertThat(annualSummary).usingRecursiveComparison().isEqualTo(expectedAnnualSummary);
    }
}
