package com.example.BankManagement.dto;

import com.example.BankManagement.model.AccountType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistrationDto {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String panNumber;
    private LocalDate dateOfBirth;
    private String password;
    private AccountType accountType; // e.g. "SAVINGS" or "CURRENT"
    private double initialDeposit;
}
