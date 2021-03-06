package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NormalTest implements Models {
    private static final double low = 1.0;
    private static final double mid = 2.0;
    private static final double high = 3.0;
    private static final double market = 10.0;
    private static final double directLow = 100.0;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Normal normal = new Normal(low, mid, high, market, directLow);
        check(normal);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Normal normal = new Normal();
        normal.setLow(low);
        normal.setMid(mid);
        normal.setHigh(high);
        normal.setMarket(market);
        normal.setDirectLow(directLow);

        check(normal);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Normal normal = Normal.builder()
                .low(low).mid(mid).high(high)
                .market(market).directLow(directLow)
                .build();
        check(normal);
    }

    private void check(Normal item){
        assertNotNull(item, createMsg("Normal"));
        assertEquals(low, item.getLow(), createMsg("low"));
        assertEquals(mid, item.getMid(), createMsg("mid"));
        assertEquals(high, item.getHigh(), createMsg("high"));
        assertEquals(market, item.getMarket(), createMsg("market"));
        assertEquals(directLow, item.getDirectLow(), createMsg("directLow"));
    }
}
