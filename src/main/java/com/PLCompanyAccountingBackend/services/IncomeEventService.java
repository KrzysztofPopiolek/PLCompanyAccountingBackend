package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class IncomeEventService {
    private final IncomeEventRepository incomeEventRepository;

    public IncomeEventService(IncomeEventRepository incomeEventRepository) {
        this.incomeEventRepository = incomeEventRepository;
    }

    public List<IncomeEvent> getAllIncomeEvent_SortedByDate() {
        return incomeEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public IncomeEvent getIncomeEvent_ById(Long id) {
        return incomeEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    public Summary createAddEntryForSummary(IncomeEvent incomeEvent, Summary summary) {
        BigDecimal saleValue = summary.getSaleValue() == null ? new BigDecimal(0) : summary.getSaleValue();
        BigDecimal otherIncome = summary.getOtherIncome() == null ? new BigDecimal(0) : summary.getOtherIncome();
        BigDecimal totalRevenue = summary.getTotalRevenue() == null ? new BigDecimal(0) : summary.getTotalRevenue();

        summary.setSaleValue(saleValue.add(incomeEvent.getSaleValue()));
        summary.setOtherIncome(otherIncome.add(incomeEvent.getOtherIncome()));
        summary.setTotalRevenue(totalRevenue.add(incomeEvent.getTotalRevenue()));
        return summary;
    }


    public Summary createDeleteEntryForSummary(IncomeEvent incomeEvent, Summary summary) {
        BigDecimal saleValue = summary.getSaleValue() == null ? new BigDecimal(0) : summary.getSaleValue();
        BigDecimal otherIncome = summary.getOtherIncome() == null ? new BigDecimal(0) : summary.getOtherIncome();
        BigDecimal totalRevenue = summary.getTotalRevenue() == null ? new BigDecimal(0) : summary.getTotalRevenue();

        summary.setSaleValue(saleValue.add(incomeEvent.getSaleValue().negate()));
        summary.setOtherIncome(otherIncome.add(incomeEvent.getOtherIncome().negate()));
        summary.setTotalRevenue(totalRevenue.add(incomeEvent.getTotalRevenue().negate()));
        return summary;
    }
}
