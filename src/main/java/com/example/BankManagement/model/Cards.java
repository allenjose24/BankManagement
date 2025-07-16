package com.example.BankManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="cards")
public class Cards {
    @Id
    Long id;
    CardType type; // CREDIT, DEBIT
    String cardNumber;

    boolean active;
    LocalDate issueDate;
    LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;


}
