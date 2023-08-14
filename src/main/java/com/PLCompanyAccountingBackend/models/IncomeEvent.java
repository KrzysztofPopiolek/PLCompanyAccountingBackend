package com.PLCompanyAccountingBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "IncomeEvents")
public class IncomeEvent extends BusinessEvent {

    @Column(name = "C7 - Sale value")
    private BigDecimal saleValue;

    @Column(name = "C8 - Other income")
    private BigDecimal otherIncome;

    @Column(name = "C9 - Total revenue", nullable = false)
    private BigDecimal totalRevenue;

    public IncomeEvent(IncomeEvent otherIncomeEvent) {
        super(otherIncomeEvent);
        this.saleValue = otherIncomeEvent.saleValue;
        this.otherIncome = otherIncomeEvent.otherIncome;
        this.totalRevenue = otherIncomeEvent.totalRevenue;
    }
}
