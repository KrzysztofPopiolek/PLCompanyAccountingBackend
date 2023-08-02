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

    @Column(name = "Business/Contractors name - C.4", nullable = false)
    private String businessContractorsName;

    @Column(name = "Business/Contractors address - C.5", nullable = false)
    private String businessContractorsAddress;
}
