package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "AnnualSummaries")
public class AnnualSummary extends Summary {
}
