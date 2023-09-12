package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import com.PLCompanyAccountingBackend.services.IncomeEventService;
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
    public void getById_ValidId_validIncomeEvent() {
        //arrange
        IncomeEvent mockIncomeEvent = IncomeEvent.builder().build();
        doReturn(Optional.of(mockIncomeEvent)).when(incomeEventRepository).findById(1L);

        //act
        IncomeEvent incomeEvent = incomeEventService.getIncomeEvent_ById(1L);

        //assert
        Assertions.assertEquals(mockIncomeEvent, incomeEvent);
    }

    @Test
    public void getById_invalidId_throwsException() {
        //arrange

        //act
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () ->
                incomeEventService.getIncomeEvent_ById(1L));

        //assert
        Assertions.assertEquals("Searched item not found!", exception.getMessage());
    }

    @Test
    public void getAllSortedByDate_valid_incomeEventsList() {
        //arrange
        List<IncomeEvent> mockIncomeEvent = new ArrayList<>(1);
        mockIncomeEvent.add(IncomeEvent.builder().build());
        doReturn(mockIncomeEvent).when(incomeEventRepository).findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));

        //act
        List<IncomeEvent> allIncomeEvent = incomeEventService.getAllIncomeEvent_SortedByDate();

        //assert
        Assertions.assertEquals(mockIncomeEvent, allIncomeEvent);
    }

    @Test
    public void createEntryForSummary_deleteModeTrue_returnsSummaryObject() {
        //arrange
        Boolean inDeleteMode = true;

        IncomeEvent mockIncomeEvent = IncomeEvent.builder()
                .saleValue(BigDecimal.ONE)
                .otherIncome(BigDecimal.ONE)
                .totalRevenue(BigDecimal.TWO)
                .build();

        AnnualSummary mockSummary = AnnualSummary.builder()
                .saleValue(BigDecimal.ONE)
                .otherIncome(BigDecimal.ONE)
                .totalRevenue(BigDecimal.TWO)
                .build();

        //act
        Summary summary = incomeEventService.createEntryForSummary(mockIncomeEvent, mockSummary, inDeleteMode);

        //assert
        AnnualSummary expectedSummary = AnnualSummary.builder()
                .saleValue(BigDecimal.ZERO)
                .otherIncome(BigDecimal.ZERO)
                .totalRevenue(BigDecimal.ZERO)
                .build();

        assertThat(summary).usingRecursiveComparison().isEqualTo(expectedSummary);
    }

    @Test
    public void createEntryForSummary_deleteModeFalse_returnsSummaryObject() {
        //arrange
        Boolean inDeleteMode = false;

        IncomeEvent mockIncomeEvent = IncomeEvent.builder()
                .saleValue(BigDecimal.ONE)
                .otherIncome(BigDecimal.ONE)
                .totalRevenue(BigDecimal.TWO)
                .build();

        AnnualSummary mockSummary = AnnualSummary.builder()
                .saleValue(BigDecimal.ONE)
                .otherIncome(BigDecimal.ONE)
                .totalRevenue(BigDecimal.TWO)
                .build();

        //act
        Summary summary = incomeEventService.createEntryForSummary(mockIncomeEvent, mockSummary, inDeleteMode);
        //assert
        AnnualSummary expectedSummary = AnnualSummary.builder()
                .saleValue(BigDecimal.TWO)
                .otherIncome(BigDecimal.TWO)
                .totalRevenue(new BigDecimal(4))
                .build();

        assertThat(summary).usingRecursiveComparison().isEqualTo(expectedSummary);
    }
}
