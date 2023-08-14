package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.OtherPurchaseCostsEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.OtherPurchaseCostsEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class OtherPurchaseCostsEventService {
    private final OtherPurchaseCostsEventRepository otherPurchaseCostsEventRepository;

    public OtherPurchaseCostsEventService(OtherPurchaseCostsEventRepository otherPurchaseCostsEventRepository) {
        this.otherPurchaseCostsEventRepository = otherPurchaseCostsEventRepository;
    }

    public List<OtherPurchaseCostsEvent> getAllOtherPurchaseCostsEvent_SortedByDate() {
        return otherPurchaseCostsEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public OtherPurchaseCostsEvent getOtherPurchaseCostsEvent_ById(Long id) {
        return otherPurchaseCostsEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    public Summary createEntryForSummary(OtherPurchaseCostsEvent otherPurchaseCostsEvent, Summary summary, Boolean deleteMode) {
        BigDecimal otherPurchaseCosts = summary.getOtherPurchaseCosts() == null ? new BigDecimal(0) : summary.getOtherPurchaseCosts();

        if (deleteMode) {
            summary.setOtherPurchaseCosts(otherPurchaseCosts.add(otherPurchaseCostsEvent.getOtherPurchaseCosts().negate()));
        } else {
            summary.setOtherPurchaseCosts(otherPurchaseCosts.add(otherPurchaseCostsEvent.getOtherPurchaseCosts()));
        }
        return summary;
    }
}
