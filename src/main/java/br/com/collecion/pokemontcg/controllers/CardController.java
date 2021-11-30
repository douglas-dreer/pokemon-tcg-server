package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.models.Card;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cards")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping(value = "", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Cards> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Card> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }


}
