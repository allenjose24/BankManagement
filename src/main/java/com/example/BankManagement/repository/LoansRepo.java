package com.example.BankManagement.repository;

import com.example.BankManagement.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepo extends JpaRepository<Loans,Long> {
    List<Loans> findByUserId(Long userId);
}
