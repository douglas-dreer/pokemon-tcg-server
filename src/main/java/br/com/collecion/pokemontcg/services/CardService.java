package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.models.Card;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.webservices.PokemonTradeCardGameWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private PokemonTradeCardGameWebService webService;

    public Card findById(String id) throws Exception {
        return webService.findById(id).getData();
    }

    public Cards findAll() throws Exception {
        return webService.findAll();
    }
}
