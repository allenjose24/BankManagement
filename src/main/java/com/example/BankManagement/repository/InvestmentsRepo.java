package com.example.BankManagement.repository;

import com.example.BankManagement.model.Investments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentsRepo extends JpaRepository<Investments,Long> {
}
