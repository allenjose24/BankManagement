package com.example.BankManagement.repository;

import com.example.BankManagement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions,Long> {

    List<Transactions> findByAccountUserId(Long requesterId);
}
