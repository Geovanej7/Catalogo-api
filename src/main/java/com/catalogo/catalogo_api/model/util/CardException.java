package com.catalogo.catalogo_api.model.util;

public class CardException extends RuntimeException {
    
    public CardException(String message) {
        super(message);
    }

    public static CardException notFound(Long id) {
        return new CardException(String.format("Card with id %d not found.", id));
    }
}
