package com.example.BankManagement.service;

import com.example.BankManagement.model.AccountType;
import com.example.BankManagement.model.Accounts;
import com.example.BankManagement.model.Users;
import com.example.BankManagement.repository.AccountsRepo;
import com.example.BankManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private UserRepository userRepo;

    //create
    public void saveAccount(Accounts account) {
        accountsRepo.save(account);
    }

    public void defaultAccountsave(Users user){
        Accounts account = new Accounts();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setOpenedDate(LocalDate.from(LocalDateTime.now()));
        account.setType(AccountType.SAVINGS);
        accountsRepo.save(account);
    }

    //delete
    public void deleteAccountById(long id){
        accountsRepo.deleteById(id);
    }

    //update
    public void updateAccount(Accounts account){
        accountsRepo.save(account);
    }

    //get
    public List<Accounts> getAllAccounts(){
        return accountsRepo.findAll();
    }

    public Accounts getAccountById(long id){
        return accountsRepo.findById(id).get();
    }
}
