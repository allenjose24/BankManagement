package com.example.BankManagement.service;

import com.example.BankManagement.model.AccountType;
import com.example.BankManagement.model.Accounts;
import com.example.BankManagement.model.Users;
import com.example.BankManagement.repository.AccountsRepo;
import com.example.BankManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private UserRepo userRepo;

    private void saveAccount(Accounts account) {
        accountsRepo.save(account);
    }

    private void defaultAccountsave(Users user){
        Accounts account = new Accounts();

        account.setAccountNumber(generateUniqueAccountNumber());
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setOpenedDate(LocalDate.from(LocalDateTime.now()));
        account.setType(AccountType.SAVINGS);
         accountsRepo.save(account);
    }

    private String generateUniqueAccountNumber() {
        long timestamp = System.currentTimeMillis();
        return "SB" + timestamp;
    }


}
