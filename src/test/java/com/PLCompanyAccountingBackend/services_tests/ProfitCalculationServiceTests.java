package com.PLCompanyAccountingBackend.services_tests;

import com.PLCompanyAccountingBackend.models.AnnualSummary;
import com.PLCompanyAccountingBackend.models.InventoryGeneralDetails;
import com.PLCompanyAccountingBackend.models.ProfitCalculation;
import com.PLCompanyAccountingBackend.repository.ProfitCalculationRepository;
import com.PLCompanyAccountingBackend.services.AnnualSummaryService;
import com.PLCompanyAccountingBackend.services.InventoryGeneralDetailsService;
import com.PLCompanyAccountingBackend.services.ProfitCalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class ProfitCalculationServiceTests {

    @Mock
    private ProfitCalculationRepository profitCalculationRepository;
    @Mock
    private InventoryGeneralDetailsService inventoryGeneralDetailsService;
    @Mock
    private AnnualSummaryService annualSummaryService;
    private ProfitCalculationService profitCalculationService;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        profitCalculationService = new ProfitCalculationService(profitCalculationRepository,
                inventoryGeneralDetailsService,
                annualSummaryService);

    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void calculateProfit_savesCorrectProfitToRepo() {
        //arrange
        LocalDate mockInventoryDate = LocalDate.of(2022, 1, 1);
        InventoryGeneralDetails mockLatestInventoryGeneralDetails = InventoryGeneralDetails.builder().inventoryDate(mockInventoryDate).totalInventory(BigDecimal.ONE).build();
        InventoryGeneralDetails mockLastInventoryGeneralDetails = InventoryGeneralDetails.builder().totalInventory(BigDecimal.ONE).build();
        AnnualSummary mockLastAnnualSummary = AnnualSummary.builder()
                .purchaseGoodsMaterialsCost(BigDecimal.ONE)
                .otherPurchaseCosts(BigDecimal.ONE)
                .totalExpenses(BigDecimal.ONE)
                .financialEconomicIssues(BigDecimal.ONE)
                .totalRevenue(BigDecimal.TEN)
                .build();

        doReturn(mockLastInventoryGeneralDetails).when(inventoryGeneralDetailsService).getLastInventoryGeneralDetails();
        doReturn(mockLastAnnualSummary).when(annualSummaryService).getLastAnnualSummary();

        //act
        ProfitCalculation profitCalculation = profitCalculationService.calculateProfit(mockLatestInventoryGeneralDetails);
        //assert
        ProfitCalculation expectedProfitCalculation = ProfitCalculation.builder().build();
        expectedProfitCalculation.setDateProfitCalculation(mockInventoryDate);
        expectedProfitCalculation.setTotalDeductibleCosts(BigDecimal.TWO);
        expectedProfitCalculation.setTotalProfit(new BigDecimal(8));
        assertThat(profitCalculation).usingRecursiveComparison().isEqualTo(expectedProfitCalculation);
    }
}
