package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BusinessContractors")
public class BusinessContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C4 - Business/Contractors name", nullable = false)
    private String businessContractorsName;

    @Column(name = "C5 - Business/Contractors address", nullable = false)
    private String businessContractorsAddress;
}
