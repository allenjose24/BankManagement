package com.example.BankManagement.repository;

import com.example.BankManagement.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepo extends JpaRepository<Cards,Long> {
}
