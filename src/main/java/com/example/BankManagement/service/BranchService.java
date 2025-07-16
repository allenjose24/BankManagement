package com.example.BankManagement.service;

import com.example.BankManagement.model.Branches;
import com.example.BankManagement.repository.BranchesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchesRepo branchRepo;

    // Create a new branch
    public Branches createBranch(Branches branch) {
        return branchRepo.save(branch);
    }

    // Get all branches
    public List<Branches> getAllBranches() {
        return branchRepo.findAll();
    }

    // Get branch by ID
    public Branches getBranchById(Long id) {
        return branchRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
    }

    // Update an existing branch
    public Branches updateBranch(Long id, Branches updatedBranch) {
        Branches existingBranch = getBranchById(id);
        existingBranch.setName(updatedBranch.getName());
        existingBranch.setCode(updatedBranch.getCode());
        existingBranch.setAddress(updatedBranch.getAddress());
        return branchRepo.save(existingBranch);
    }

    // Delete branch
    public void deleteBranch(Long id) {
        Branches branch = getBranchById(id);
        branchRepo.delete(branch);
    }

    // Check if a branch exists
    public boolean branchExists(Long id) {
        return branchRepo.existsById(id);
    }
}

