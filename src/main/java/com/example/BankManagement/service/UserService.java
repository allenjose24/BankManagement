package com.example.BankManagement.service;

import com.example.BankManagement.model.EmployeeType;
import com.example.BankManagement.model.Users;
import com.example.BankManagement.repository.EmployeeRepo;
import com.example.BankManagement.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AccountsService accountsService;
    private final EmployeeRepo employeeRepo;

    public UserService(UserRepository userRepo, AccountsService accountsService, EmployeeRepo employeeRepo) {
        this.userRepo = userRepo;
        this.accountsService = accountsService;
        this.employeeRepo = employeeRepo;
    }

    public boolean isManager(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(emp -> emp.getRole() == EmployeeType.MANAGER)
                .orElse(false);
    }

    private void verifyAccess(Long requesterId, Long targetUserId) {
        if (!requesterId.equals(targetUserId) && !isManager(requesterId)) {
            throw new RuntimeException("Access denied: Only managers can access others' data.");
        }
    }

    public void registerUser(Users user) {
        userRepo.save(user);
        accountsService.defaultAccountsave(user); // create default account
    }

    public Users getUserById(Long userId) {
        // self-access version
        return getUserById(userId, userId);
    }

    public Users getUserById(Long targetUserId, Long requesterId) {
        verifyAccess(requesterId, targetUserId);
        return userRepo.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    public void updateUser(Long userId, Users updatedUser) {
        updateUser(userId, userId, updatedUser);
    }

    public void updateUser(Long targetUserId, Long requesterId, Users updatedUser) {
        verifyAccess(requesterId, targetUserId);
        Users existing = userRepo.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhone(updatedUser.getPhone());
        existing.setAddress(updatedUser.getAddress());
        existing.setPanNumber(updatedUser.getPanNumber());
        existing.setDateOfBirth(updatedUser.getDateOfBirth());
        userRepo.save(existing);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public void deleteUser(Long targetUserId, Long requesterUserId) {
        userRepo.deleteById(targetUserId);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
}
