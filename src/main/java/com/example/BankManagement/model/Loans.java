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
@Table(name="loans")
public class Loans {
    @Id
    Long id;
    BigDecimal amount;
    BigDecimal interestRate;
    int durationInMonths;
    LoanStatus status; // PENDING, APPROVED, REJECTED, COMPLETED
    LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
