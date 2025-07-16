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
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="txn_seq")
    @SequenceGenerator(
            name = "txn_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1
    )
    private long accountNumber;
    private BigDecimal balance;
    private AccountType type; // SAVINGS, CURRENT, etc.
    private LocalDate openedDate;

    @ManyToOne
    private Users user;
}
