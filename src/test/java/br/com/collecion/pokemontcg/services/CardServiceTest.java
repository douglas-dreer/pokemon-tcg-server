package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.models.Card;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.models.Data;
import br.com.collecion.pokemontcg.webservices.PokemonTradeCardGameWebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CardServiceTest {
    @InjectMocks
    private CardService service;

    @Mock
    private PokemonTradeCardGameWebService webService;

    private static final String ID = "base1-1";
    private Data dataResult = new Data();
    private Card cardResult = new Card();
    private Cards cardsResult = new Cards();

    @BeforeEach
    void setup() {
        cardResult.setId(ID);
        dataResult.setData(cardResult);
        cardsResult.setCount(1);
        cardsResult.setData(Collections.singletonList(cardResult));
        cardsResult.setPage(1);
        cardsResult.setTotalCount(1);
    }

    @Test
    void mustReturnSuccess_WhenFindById() throws Exception {
        when(webService.findById(anyString())).thenReturn(dataResult);

        Card result = service.findById(ID);

        assertNotNull(result);
        verify(webService, atLeastOnce()).findById(ID);
    }

    @Test
    void mustReturnSuccess_WhenFindAll() throws Exception {
        when(webService.findAll()).thenReturn(cardsResult);

        Cards cards = service.findAll();

        assertNotNull(cards);
        assertFalse(cards.getData().isEmpty());
        verify(webService, atLeastOnce()).findAll();
    }
}
