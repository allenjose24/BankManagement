package com.example.BankManagement.repository;

import com.example.BankManagement.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepo extends JpaRepository<Cards,Long> {
    List<Cards> findByUserId(Long userId);
}
