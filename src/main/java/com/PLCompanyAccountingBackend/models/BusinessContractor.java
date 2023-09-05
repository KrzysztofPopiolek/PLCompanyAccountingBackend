package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "BusinessContractors")
public class BusinessContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C4 - Business/Contractors name", nullable = false)
    private String businessContractorName;

    @Column(name = "C5 - Business/Contractors address", nullable = false)
    private String businessContractorAddress;
}
