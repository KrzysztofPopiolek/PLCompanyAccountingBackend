package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "BusinessExpenses")
public class BusinessExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
