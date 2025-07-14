package com.example.BankManagement.repository;

import com.example.BankManagement.model.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepo extends JpaRepository<Branches,Long> {

}
