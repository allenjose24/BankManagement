package com.example.BankManagement.service;

import com.example.BankManagement.model.Cards;
import com.example.BankManagement.model.EmployeeType;
import com.example.BankManagement.repository.CardsRepo;
import com.example.BankManagement.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardsRepo cardRepo;
    private final EmployeeRepo employeeRepo;

    public CardService(CardsRepo cardRepo, EmployeeRepo employeeRepo) {
        this.cardRepo = cardRepo;
        this.employeeRepo = employeeRepo;
    }

    public boolean isManager(Long userId) {
        return employeeRepo.findByUserId(userId)
                .map(emp -> emp.getRole() == EmployeeType.MANAGER)
                .orElse(false);
    }

    public List<Cards> getAllCards(Long requesterId) {
        if (!isManager(requesterId)) {
            throw new RuntimeException("Only managers can view all cards");
        }
        return cardRepo.findAll();
    }

    public List<Cards> getCardsByUser(Long userId) {
        return cardRepo.findByUserId(userId);
    }

    public Cards issueCard(Cards card) {
        card.setActive(true);
        return cardRepo.save(card);
    }

    public void deactivateCard(Long cardId, Long requesterId) {
        Cards card = cardRepo.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
        if (!isManager(requesterId) && !card.getUser().getId().equals(requesterId)) {
            throw new RuntimeException("Not authorized");
        }
        card.setActive(false); // Assume such a field
        cardRepo.save(card);
    }
}

