package com.example.BankManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="transactions")
public class Transactions {
    @Id
    Long id;
    TransactionType type; // CREDIT, DEBIT, TRANSFER
    BigDecimal amount;
    LocalDateTime timestamp;
    String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
