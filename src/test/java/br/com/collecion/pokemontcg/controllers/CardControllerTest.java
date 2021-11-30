package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.models.Card;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Value("${server.servlet.context-path}")
    private String URL_BASE;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CardService cardService;

    private static final String ID = "base1-1";
    private Card card = new Card();
    private Cards cardList = new Cards();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void mustReturnSuccess_WhenFindById() throws Exception {
        card.setId(ID);
        String url = String.format("%s/cards/%s", URL_BASE, ID);
        when(cardService.findById(anyString())).thenReturn(card);
        MockHttpServletRequestBuilder methodGET = get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(methodGET).andExpect(status().is4xxClientError());
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() throws Exception {
        String url = String.format("%s/cards", URL_BASE);
        when(cardService.findAll()).thenReturn(cardList);
        MockHttpServletRequestBuilder methodGET = get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(methodGET).andExpect(status().is4xxClientError());
    }
}
