package com.PLCompanyAccountingBackend.models;

import com.PLCompanyAccountingBackend.enums.AccountingMeasureUnits;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "InventoryEntries")
public class InventoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Goods name", nullable = false)
    private String goodsName;

    @Column(name = "Units type", nullable = false)
    private AccountingMeasureUnits unitsType;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "Price per unit")
    private BigDecimal pricePerUnit;

    @Column(name = "Valuation")
    private BigDecimal valuation;

    @ManyToOne
    @JoinColumn(name = "Inventory_id", nullable = false)
    private InventoryGeneralDetails inventoryGeneralDetails;
}
