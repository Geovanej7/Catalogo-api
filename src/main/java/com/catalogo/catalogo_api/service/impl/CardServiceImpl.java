package com.catalogo.catalogo_api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.catalogo.catalogo_api.model.Card;
import com.catalogo.catalogo_api.model.util.CardException;
import com.catalogo.catalogo_api.repository.CardRepository;
import com.catalogo.catalogo_api.service.CardService;

import jakarta.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Card create(Card cardToCreate) {
        cardToCreate.setEnabled(Boolean.TRUE);
        cardToCreate.setVersion(1L);
        cardToCreate.setCreationDate(LocalDate.now());
        return cardRepository.save(cardToCreate);
    }

    @Transactional
    public Card findById(Long id) {
        return cardRepository.findById(id)
        .orElseThrow(() -> CardException.notFound(id));
    }

    @Transactional
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional
    public void update(Long id, Card newCard) {

        Card card = cardRepository.findById(id)
        .orElseThrow(() -> CardException.notFound(id));

        card.setTitle(newCard.getTitle());
        card.setPrice(newCard.getPrice());
        card.setDescription(newCard.getDescription());
        card.setImage(newCard.getImage());
        card.setLastModifiedDate(LocalDate.now());
        card.setVersion(card.getVersion()+1);
        cardRepository.save(card);
    }

    @Transactional
    public void delete(Long id) {
        
        Card card = cardRepository.findById(id)
        .orElseThrow(() -> CardException.notFound(id));

        card.setEnabled(Boolean.FALSE);
        card.setLastModifiedDate(LocalDate.now());
        card.setVersion(card.getVersion()+1);
        cardRepository.save(card);
    }
}
