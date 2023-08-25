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
//        List<OtherPurchaseCostsEvent> otherPurchaseCostsEvents = otherPurchaseCostsEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
//        if(!otherPurchaseCostsEvents.isEmpty()){
//            return otherPurchaseCostsEvents;
//        }   throw new ResourceNotFoundException("No entries in database!");
//
        return otherPurchaseCostsEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }

    public OtherPurchaseCostsEvent getOtherPurchaseCostsEvent_ById(Long id) {
        return otherPurchaseCostsEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    /**
     * Creates an entry which will be added to the summary tables from the provided event.
     *
     * @param otherPurchaseCostsEvent The event we added to the otherPurchaseCostsEvent table.
     * @param summary                 The object which represents the current state of the summary table.
     * @return A summary object which represents the new state of the summary table.
     */
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
