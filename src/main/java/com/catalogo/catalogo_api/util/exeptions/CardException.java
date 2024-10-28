package com.catalogo.catalogo_api.util.exeptions;

public class CardException extends RuntimeException {
    
    public CardException(String message) {
        super(message);
    }

    public static CardException notFound(Long id) {
        return new CardException(String.format("Card with id %d not found.", id));
    }
    
    public static CardException invalidValue() {
        return new CardException(String.format("Card with invalid value."));
    }

}
