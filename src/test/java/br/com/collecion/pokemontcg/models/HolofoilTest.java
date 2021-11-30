package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HolofoilTest implements Models {
    private static final int low = 1;
    private static final double mid = 2.0;
    private static final int high = 3;
    private static final double market = 10.0;
    private static final double directLow = 100.0;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Holofoil holofoil = new Holofoil(low, mid, high, market, directLow);
        check(holofoil);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Holofoil holofoil = new Holofoil();
        holofoil.setLow(low);
        holofoil.setMid(mid);
        holofoil.setHigh(high);
        holofoil.setMarket(market);
        holofoil.setDirectLow(directLow);

        check(holofoil);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Holofoil holofoil = Holofoil.builder()
                .low(low).mid(mid).high(high)
                .market(market).directLow(directLow)
                .build();
        check(holofoil);
    }

    private void check(Holofoil item){
        assertNotNull(item, createMsg("holofoil"));
        assertEquals(low, item.getLow(), createMsg("low"));
        assertEquals(mid, item.getMid(), createMsg("mid"));
        assertEquals(high, item.getHigh(), createMsg("high"));
        assertEquals(market, item.getMarket(), createMsg("market"));
        assertEquals(directLow, item.getDirectLow(), createMsg("directLow"));
    }
}
