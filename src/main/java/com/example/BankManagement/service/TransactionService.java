package com.example.BankManagement.service;

import com.example.BankManagement.model.*;
import com.example.BankManagement.repository.AccountsRepo;
import com.example.BankManagement.repository.EmployeeRepo;
import com.example.BankManagement.repository.TransactionsRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionsRepo transactionRepo;
    private final AccountsRepo accountRepo;
    private final EmployeeRepo employeeRepo;

    public TransactionService(TransactionsRepo transactionRepo, AccountsRepo accountRepo, EmployeeRepo employeeRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.employeeRepo = employeeRepo;
    }

    private boolean isManager(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(e -> e.getRole() == EmployeeType.MANAGER)
                .orElse(false);
    }

    private boolean isClerk(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(e -> e.getRole() == EmployeeType.CLERK)
                .orElse(false);
    }

    // User creates a transaction (e.g. deposit, withdrawal, transfer)
    public Transactions createTransaction(Transactions txn, Long requesterId) {
        Accounts account = txn.getAccount();
        if (!account.getUser().getId().equals(requesterId)) {
            throw new RuntimeException("Not your account.");
        }

        txn.setStatus(TransactionState.PENDING);
        txn.setTimestamp(LocalDateTime.now());
        return transactionRepo.save(txn);
    }

    // Clerk approves a transaction
    public Transactions approveTransaction(Long txnId, Long clerkId) {
        if (!isClerk(clerkId)) {
            throw new RuntimeException("Only clerks can approve transactions.");
        }

        Transactions txn = transactionRepo.findById(txnId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (!txn.getStatus().equals("PENDING")) {
            throw new RuntimeException("Only pending transactions can be approved.");
        }

        Accounts account = txn.getAccount();

        if (txn.getType()== TransactionType.DEBIT) {
            if (account.getBalance().compareTo(txn.getAmount()) < 0) {
                throw new RuntimeException("Insufficient balance.");
            }
            account.setBalance(account.getBalance().subtract(txn.getAmount()));
        } else if (txn.getType()==TransactionType.CREDIT) {
            account.setBalance(account.getBalance().add(txn.getAmount()));
        }

        txn.setStatus(TransactionState.SUCCESS);
        txn.setTimestamp(LocalDateTime.now());

        accountRepo.save(account);
        return transactionRepo.save(txn);
    }

    // User retrieves only their transactions
    public List<Transactions> getUserTransactions(Long requesterId) {
        return transactionRepo.findByAccountUserId(requesterId);
    }

    // Manager can view all transactions
    public List<Transactions> getAllTransactions(Long requesterId) {
        if (!isManager(requesterId)) {
            throw new RuntimeException("Only managers can access all transactions.");
        }
        return transactionRepo.findAll();
    }
}

