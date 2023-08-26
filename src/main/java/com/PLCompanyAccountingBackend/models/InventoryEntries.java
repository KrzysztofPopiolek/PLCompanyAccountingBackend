package com.PLCompanyAccountingBackend.models;

import com.PLCompanyAccountingBackend.enums.AccountingMeasureUnits;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "InventoryEntries")
public class InventoryEntries {

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

    public InventoryEntries(InventoryEntries otherInventoryEntries) {
        this.id = otherInventoryEntries.id;
        this.goodsName = otherInventoryEntries.goodsName;
        this.unitsType = otherInventoryEntries.unitsType;
        this.amount = otherInventoryEntries.amount;
        this.pricePerUnit = otherInventoryEntries.pricePerUnit;
        this.valuation = otherInventoryEntries.valuation;
        this.inventoryGeneralDetails = otherInventoryEntries.inventoryGeneralDetails;
    }
}
