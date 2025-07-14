package com.example.BankManagement.repository;

import com.example.BankManagement.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepo extends JpaRepository<Loans,Long> {
}
