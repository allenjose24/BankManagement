package com.example.BankManagement.service;

import com.example.BankManagement.model.EmployeeType;
import com.example.BankManagement.model.Investments;
import com.example.BankManagement.repository.EmployeeRepo;
import com.example.BankManagement.repository.InvestmentsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    private final InvestmentsRepo investmentRepo;
    private final EmployeeRepo employeeRepo;

    public InvestmentService(InvestmentsRepo investmentRepo, EmployeeRepo employeeRepo) {
        this.investmentRepo = investmentRepo;
        this.employeeRepo = employeeRepo;
    }

    public boolean isManager(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(emp -> emp.getRole() == EmployeeType.MANAGER)
                .orElse(false);
    }

    public Investments createInvestment(Investments investment) {
        return investmentRepo.save(investment);
    }

    public List<Investments> getInvestmentsByUser(Long userId) {
        return investmentRepo.findByUserId(userId);
    }

    public List<Investments> getAllInvestments(Long requesterId) {
        if (!isManager(requesterId)) {
            throw new RuntimeException("Only managers can view all investments");
        }
        return investmentRepo.findAll();
    }
}

