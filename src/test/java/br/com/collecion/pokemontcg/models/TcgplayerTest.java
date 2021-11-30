package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TcgplayerTest implements Models {
    public static final String url = "url";
    public static final String updatedAt = "updatedAt";
    public static final Prices prices = new Prices();

    @BeforeEach
    public void setup() {
        prices.setLowPrice(1.0);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Tcgplayer tcgplayer = new Tcgplayer(url, updatedAt, prices);
        check(tcgplayer);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Tcgplayer tcgplayer = new Tcgplayer();
        tcgplayer.setUrl(url);
        tcgplayer.setUpdatedAt(updatedAt);
        tcgplayer.setPrices(prices);
        check(tcgplayer);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Tcgplayer tcgplayer = Tcgplayer.builder()
                .url(url)
                .updatedAt(updatedAt)
                .prices(prices)
                .build();
        check(tcgplayer);
    }

    private void check(Tcgplayer item){
        assertNotNull(item, createMsg("Set"));
        assertEquals(url, item.getUrl(), createMsg("url"));
        assertEquals(updatedAt, item.getUpdatedAt(), createMsg("updatedAt"));
        assertNotNull(prices);
        assertEquals(prices, item.getPrices(), createMsg("prices"));
    }
}
