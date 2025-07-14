package com.example.BankManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_seq")
    @SequenceGenerator(
            name = "txn_seq",
            sequenceName = "transaction_seq",
            allocationSize = 1
    )
    Long id;
    String name;
    String email;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    String phone;
    String address;
    String panNumber;
    LocalDate dateOfBirth;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters and include uppercase, lowercase, number, and special character")
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Accounts> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transactions> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loans> loans;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Investments> investments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cards> cards;
}
