package com.PLCompanyAccountingBackend.services;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.PurchaseGoodsServicesEvent;
import com.PLCompanyAccountingBackend.models.Summary;
import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseGoodsServicesEventService {
    private final PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository;

    public PurchaseGoodsServicesEventService(PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository) {
        this.purchaseGoodsServicesEventRepository = purchaseGoodsServicesEventRepository;
    }
    public List<PurchaseGoodsServicesEvent> getAllPurchaseGoodsServicesEvent_SortedByDate() {
//        List<PurchaseGoodsServicesEvent> purchaseGoodsServicesEvents = purchaseGoodsServicesEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
//
//        if(!purchaseGoodsServicesEvents.isEmpty()){
//            return purchaseGoodsServicesEvents;
//        }
//        throw new ResourceNotFoundException("No entries in database!");
        return purchaseGoodsServicesEventRepository.findAll(Sort.by(Sort.Direction.ASC, "dateEconomicEvent"));
    }


    public PurchaseGoodsServicesEvent getPurchaseGoodsServicesEvent_ById(Long id) {
        return purchaseGoodsServicesEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

    /**
     * Creates an entry which will be added to the summary tables from the provided event.
     *
     * @param purchaseGoodsServicesEvent The event we added to the purchaseGoodsServicesEvent table.
     * @param summary                    The object which represents the current state of the summary table.
     * @return A summary object which represents the new state of the summary table.
     */
    public Summary createEntryForSummary(PurchaseGoodsServicesEvent purchaseGoodsServicesEvent, Summary summary, Boolean deleteMode) {
        BigDecimal purchaseGoodsServices = summary.getPurchaseGoodsServices() == null ? new BigDecimal(0) : summary.getPurchaseGoodsServices();

        if (deleteMode) {
            summary.setPurchaseGoodsServices(purchaseGoodsServices.add(purchaseGoodsServicesEvent.getPurchaseGoodsServices().negate()));
        } else {
            summary.setPurchaseGoodsServices(purchaseGoodsServices.add(purchaseGoodsServicesEvent.getPurchaseGoodsServices()));
        }
        return summary;
    }
}
