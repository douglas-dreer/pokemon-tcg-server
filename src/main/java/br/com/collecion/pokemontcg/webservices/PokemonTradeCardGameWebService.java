package br.com.collecion.pokemontcg.webservices;

import br.com.collecion.pokemontcg.exceptions.HandleException;
import br.com.collecion.pokemontcg.models.Cards;
import br.com.collecion.pokemontcg.models.Data;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Collections;

@Service
public class PokemonTradeCardGameWebService {
    private final String URL_BASE = "https://api.pokemontcg.io/v2";

    private final String API = "289b3ca2-dcf1-47de-aa28-27937ac3812a";

    private final String SET_BASE = "base1";

    private static HttpHeaders httpHeaders;
    private HttpEntity params;

    private RestTemplate restTemplate = new RestTemplate();

    public PokemonTradeCardGameWebService() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-Api-Key", API);
    }

    private Object execute(String url, HttpMethod method, Type type) throws Exception{
        String result = "";
        try {
            params = new HttpEntity(httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(
                    url, method, params, String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new Exception(response.getStatusCode().toString());
            }
            result = response.getBody();
        } catch (Exception e) {
            HandleException.errorHandling(e.getLocalizedMessage());
        }
        return new Gson().fromJson(result, type);
    }

    public Data findById(String id) throws Exception {
        String url = String.format("%s/cards/%s", URL_BASE, id);
        return (Data) execute(url, HttpMethod.GET, Data.class);
    }

    public Cards findAll() throws Exception {
        String url = String.format("%s/cards?q:set.id=%s", URL_BASE, SET_BASE);
        return (Cards) execute(url, HttpMethod.GET, Cards.class);
    }
}
