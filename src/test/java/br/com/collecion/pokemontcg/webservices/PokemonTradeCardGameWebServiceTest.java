package br.com.collecion.pokemontcg.webservices;

import br.com.collecion.pokemontcg.models.Card;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.models.Data;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PokemonTradeCardGameWebServiceTest {
    @InjectMocks
    private PokemonTradeCardGameWebService service;

    @Mock
    private RestTemplate restTemplate;

    private final Gson gson = new Gson();
    private String jsonResponse;
    private String jsonResponseList;

    private static final String ID = "base1-1";
    private final Data data = new Data();
    private final Card card = new Card();
    private final Cards cardList = new Cards();

    @Before
    public void setup() {
        card.setId("base1-1");
        data.setData(card);

        cardList.setData(Collections.singletonList(card));
        cardList.setPage(1);
        cardList.setPageSize(50);
        cardList.setCount(10);
        cardList.setTotalCount(100);

        jsonResponse = gson.toJson(data);
        jsonResponseList = gson.toJson(cardList);
    }

    @Test
    public void mustReturnSuccess_WhenFindById() throws Exception {
        when(restTemplate.exchange(
                anyString(), any(HttpMethod.class),
                any(), ArgumentMatchers.<Class<String>>any()))
                .thenReturn(ResponseEntity.ok(jsonResponse));

        Data result = service.findById(ID);

        assertNotNull(result);
    }

    @Test
    public void mustReturnExceptionNotFound_WhenFindById() throws Exception {
        when(restTemplate.exchange(
                anyString(), any(HttpMethod.class),
                any(), ArgumentMatchers.<Class<String>>any()))
                .thenReturn(ResponseEntity.notFound().build());

        Data result = service.findById(ID);
        assertNull(result);
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() throws Exception {
        when(restTemplate.exchange(
                anyString(), any(HttpMethod.class),
                any(), ArgumentMatchers.<Class<String>>any()))
                .thenReturn(ResponseEntity.ok(jsonResponseList));

        Cards result = service.findAll();

        assertNotNull(result);
        assertEquals(cardList.getCount(), result.getCount());
    }
}

