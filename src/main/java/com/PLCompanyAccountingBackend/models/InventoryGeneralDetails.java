package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Table(name = "InventoryGeneralDetails")
public class InventoryGeneralDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Inventory date", nullable = false)
    private LocalDate inventoryDate;

    @Column(name = "Business name", nullable = false)
    private String businessName;

    @Column(name = "Inventory authors name", nullable = false)
    private String inventoryAuthorsName;

    @Column(name = "Company owner or manager", nullable = false)
    private String companyOwnerOrManager;

    @Column(name = "Inventory total sum", nullable = false)
    private BigDecimal totalInventory;

    @Column(name = "Start/initial inventory", nullable = false)
    private Boolean isStartInventory;
}
