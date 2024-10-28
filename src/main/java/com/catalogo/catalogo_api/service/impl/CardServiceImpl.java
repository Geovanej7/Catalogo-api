package com.catalogo.catalogo_api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import com.catalogo.catalogo_api.model.Admin;
import com.catalogo.catalogo_api.model.Card;
import com.catalogo.catalogo_api.repository.AdminRepository;
import com.catalogo.catalogo_api.repository.CardRepository;
import com.catalogo.catalogo_api.service.CardService;
import com.catalogo.catalogo_api.util.exeptions.AdminException;
import com.catalogo.catalogo_api.util.exeptions.CardException;

import jakarta.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Card create(Long adminId,Card card) {

        Admin admin = adminRepository.findById(adminId).get();
        List<Card> cards = admin.getCards();

        card.setAdmin(admin);
        card.setEnabled(Boolean.TRUE);
        card.setVersion(1L);
        card.setCreationDate(LocalDate.now());
        cardRepository.save(card);

        if(cards == null){
            cards = new ArrayList<Card>();
        }

        cards.add(card);
        admin.setCards(cards);
        admin.setVersion(admin.getVersion()+1);
        adminRepository.save(admin);

        return card;
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

        Admin admin = adminRepository.findById(card.getAdmin().getId()).orElseThrow(() -> AdminException.notFound(id));
        admin.getCards().remove(card);
        admin.setVersion(admin.getVersion()+1);
        adminRepository.save(admin);
    }
}
