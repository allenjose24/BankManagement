package com.example.BankManagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters and include uppercase, lowercase, number, and special character")
    String password;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    Branches branch;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
