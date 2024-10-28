package com.catalogo.catalogo_api.service;
import java.util.List;
import com.catalogo.catalogo_api.model.Card;

public interface CardService {

    Card create(Long adminId, Card cardToCreate);

    Card findById(Long id);

    List<Card> findAll();

    void update(Long id, Card newCard);

    void delete(Long id);
}
