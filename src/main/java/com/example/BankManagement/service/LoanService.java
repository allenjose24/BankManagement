package com.example.BankManagement.service;

import com.example.BankManagement.model.EmployeeType;
import com.example.BankManagement.model.LoanStatus;
import com.example.BankManagement.model.Loans;
import com.example.BankManagement.repository.EmployeeRepo;
import com.example.BankManagement.repository.LoansRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoansRepo loanRepo;
    private final EmployeeRepo employeeRepo;

    public LoanService(LoansRepo loanRepo, EmployeeRepo employeeRepo) {
        this.loanRepo = loanRepo;
        this.employeeRepo = employeeRepo;
    }

    public boolean isLoanOfficer(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(emp -> emp.getRole() == EmployeeType.LOAN_OFFICER)
                .orElse(false);
    }

    public Loans applyForLoan(Loans loan) {
        loan.setStatus(LoanStatus.PENDING);
        return loanRepo.save(loan);
    }

    public List<Loans> getUserLoans(Long userId) {
        return loanRepo.findByUserId(userId);
    }

    public List<Loans> getAllLoans(Long requesterId) {
        if (!isLoanOfficer(requesterId)) {
            throw new RuntimeException("Only loan officers can view all loan applications");
        }
        return loanRepo.findAll();
    }

    public Loans approveLoan(Long loanId, Long officerId) {
        if (!isLoanOfficer(officerId)) {
            throw new RuntimeException("Only loan officers can approve loans");
        }
        Loans loan = loanRepo.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(LoanStatus.APPROVED);
        return loanRepo.save(loan);
    }
}

