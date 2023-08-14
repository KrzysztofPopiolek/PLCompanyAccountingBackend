package com.PLCompanyAccountingBackend.services;
import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.PurchaseGoodsServicesEvent;
import com.PLCompanyAccountingBackend.repository.PurchaseGoodsServicesEventRepository;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
public class PurchaseGoodsServicesEventService {
private final PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository;

    public PurchaseGoodsServicesEventService(PurchaseGoodsServicesEventRepository purchaseGoodsServicesEventRepository) {
        this.purchaseGoodsServicesEventRepository = purchaseGoodsServicesEventRepository;
    }

    public List<PurchaseGoodsServicesEvent>getAllPurchaseGoodsServicesEvent_SortedByDate(){
        return purchaseGoodsServicesEventRepository.findAll(Sort.by(Sort.Direction.ASC,"dateEconomicEvent"))
    }

    public PurchaseGoodsServicesEvent getPurchaseGoodsServicesEvent_ById(Long id) {
        return purchaseGoodsServicesEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
    }

}
