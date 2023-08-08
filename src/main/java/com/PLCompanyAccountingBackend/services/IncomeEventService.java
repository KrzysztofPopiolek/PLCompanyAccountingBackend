package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.models.IncomeEvent;
import com.PLCompanyAccountingBackend.repository.IncomeEventRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class IncomeEventService {
    private final IncomeEventRepository incomeEventRepository;

    public IncomeEventService(IncomeEventRepository incomeEventRepository) {
        this.incomeEventRepository = incomeEventRepository;
    }

    public List<IncomeEvent> getAllIncomeEvent_SortedByDate() {
        return incomeEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

}
