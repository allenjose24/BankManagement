package com.example.BankManagement.service;

import com.example.BankManagement.model.Users;
import com.example.BankManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AccountsService accountsService;

    public UserService(UserRepository userRepo, AccountsService accountsService) {
        this.userRepo = userRepo;
        this.accountsService = accountsService;
    }

    public void saveUser(Users user){
        userRepo.save(user);
        accountsService.defaultAccountsave(user);
    }

    public Users findById(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<Users> findAll(){
        return userRepo.findAll();
    }


}
