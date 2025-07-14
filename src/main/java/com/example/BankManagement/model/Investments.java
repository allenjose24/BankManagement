package com.example.BankManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="investments")
public class Investments {
    @Id
    Long id;
    InvestmentType type; // FIXED_DEPOSIT, MUTUAL_FUND, etc.
    BigDecimal investedAmount;
    BigDecimal expectedReturn;
    LocalDate startDate;
    LocalDate maturityDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
