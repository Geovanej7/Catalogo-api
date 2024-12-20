package com.catalogo.catalogo_api.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import com.catalogo.catalogo_api.model.Card;
import com.catalogo.catalogo_api.service.CardService;
import com.catalogo.catalogo_api.util.UploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/controller/card")
@CrossOrigin
public class CardController {
    
    @Autowired
    private CardService cardService;

    @Operation(summary = "Create a new card.", description = "Service to save a new card.")
    @PostMapping("/{adminId}")
    public ResponseEntity<Card> create(@PathVariable("adminId")Long adminId,
            @RequestParam("card") String cardRequestJson,
            @RequestParam(value = "image", required = false) MultipartFile image){

         try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardRequest request = objectMapper.readValue(cardRequestJson, CardRequest.class);

            Card newCard = request.build();

            if(UploadUtil.UploadImagem(image)){
                newCard.setImage(image.getOriginalFilename());
            }

            Card card = cardService.create(adminId, newCard);
            return new ResponseEntity<>(card, HttpStatus.CREATED);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    @Operation(summary = "Get all cards.", description = "Service to retrieve all cards.")
    @GetMapping
    public List<Card> findAll(){
        return cardService.findAll();
    }

    @Operation(summary = "Get a card by ID.", description = "Service to retrieve a card by its ID.")
    @GetMapping("/{id}")
    public Card findById(@PathVariable Long id){
        return cardService.findById(id);
    }

    @Operation(summary = "Update a card.", description = "Service to update an existing card.")
    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable("id")Long id,
    @RequestParam("card") String cardRequestJson,
    @RequestParam(value = "image", required = false) MultipartFile image){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardRequest request = objectMapper.readValue(cardRequestJson, CardRequest.class);

            Card newCard = request.build();

            if(UploadUtil.UploadImagem(image)){
                newCard.setImage(image.getOriginalFilename());
            }

           cardService.update(id, newCard);
           return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Delete a card.", description = "Service to delete a card by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
