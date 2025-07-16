package com.example.BankManagement.service;

import com.example.BankManagement.model.AccountType;
import com.example.BankManagement.model.Accounts;
import com.example.BankManagement.model.EmployeeType;
import com.example.BankManagement.model.Users;
import com.example.BankManagement.repository.AccountsRepo;
import com.example.BankManagement.repository.EmployeeRepo;
import com.example.BankManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public boolean isManager(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(emp -> emp.getRole() == EmployeeType.MANAGER)
                .orElse(false);
    }

    public void defaultAccountsave(Users user) {
        Accounts account = new Accounts();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setType(AccountType.SAVINGS);
        accountsRepo.save(account);
    }

    public List<Accounts> getAllAccounts(Long requesterId) {
        if (!isManager(requesterId)) {
            throw new RuntimeException("Only managers can access all accounts!");
        }
        return accountsRepo.findAll();
    }

    public Accounts getAccountById(Long accountId, Long requesterId) {
        Accounts account = accountsRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!account.getUser().getId().equals(requesterId) && !isManager(requesterId)) {
            throw new RuntimeException("Access denied: Not your account and you're not a manager");
        }
        return account;
    }

    public Accounts updateAccount(Long accountId, Accounts updatedData, Long requesterId) {
        Accounts account = getAccountById(accountId, requesterId);

        account.setType(updatedData.getType());
        //fields to update
        return accountsRepo.save(account);
    }

    public void deleteAccount(Long accountId, Long requesterId) {
        Accounts account = getAccountById(accountId, requesterId);
        accountsRepo.delete(account);
    }

    public void withdraw(Long accountId, BigDecimal amount, Long requesterId) {
        Accounts account = getAccountById(accountId, requesterId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountsRepo.save(account);
    }
}
