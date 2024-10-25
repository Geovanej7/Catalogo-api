package com.catalogo.catalogo_api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.catalogo.catalogo_api.model.Card;
import com.catalogo.catalogo_api.repository.CardRepository;
import com.catalogo.catalogo_api.service.CardService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card cardToCreate) {
        cardToCreate.setEnabled(Boolean.TRUE);
        cardToCreate.setVersion(1L);
        cardToCreate.setCreationDate(LocalDate.now());
        return cardRepository.save(cardToCreate);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public void update(Long id, Card newCard) {
        Card card = cardRepository.findById(id).get();
        card.setTitle(newCard.getTitle());
        card.setPrice(newCard.getPrice());
        card.setDescription(newCard.getDescription());
        card.setImage(newCard.getImage());
        card.setLastModifiedDate(LocalDate.now());
        card.setVersion(card.getVersion()+1);
        cardRepository.save(card);
    }

    @Override
    public void delete(Long id) {
        
        Card card = cardRepository.findById(id).get();
        card.setEnabled(Boolean.FALSE);
        card.setLastModifiedDate(LocalDate.now());
        card.setVersion(card.getVersion()+1);
        cardRepository.save(card);
    }
}
