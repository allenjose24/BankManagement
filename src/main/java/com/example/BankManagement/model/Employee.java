package com.example.BankManagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name="employee")
public class Employee {
    @Id
    Long id;
    String name;
    EmployeeType role; // MANAGER, CLERK, LOAN_OFFICER
    String email;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    Branches branch;
}
