package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "InventoryGeneralDetails")
public class InventoryGeneralDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Inventory date", nullable = false)
    private LocalDate inventoryDate;

    @Column(name = "Inventory business name", nullable = false)
    private String inventoryBusinessName;

    @Column(name = "Inventory authors name", nullable = false)
    private String inventoryAuthorsName;

    @Column(name = "Company owner or manager", nullable = false)
    private String inventoryCompanyOwnerOrManager;

    @Column(name = "Inventory total sum", nullable = false)
    private BigDecimal totalInventory;

    @Column(name = "Start/initial inventory", nullable = false)
    private Boolean isStartInventory;

}
