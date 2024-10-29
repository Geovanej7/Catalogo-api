package com.catalogo.catalogo_api.controller.card;

import com.catalogo.catalogo_api.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    private String title;

    private Double price;

    private String description;

    private String image;

    public Card build(){
        
        return Card.builder()
        .title(title)
        .price(price)
        .description(description)
        .image(image)
        .build();
    }
}
