package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReverseHolofoilTest implements Models {
    private static final int low = 1;
    private static final double mid = 2.0;
    private static final int high = 3;
    private static final double market = 10.0;
    private static final double directLow = 100.0;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        ReverseHolofoil reverseHolofoil = new ReverseHolofoil(low, mid, high, market, directLow);
        check(reverseHolofoil);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        ReverseHolofoil reverseHolofoil = new ReverseHolofoil();
        reverseHolofoil.setLow(low);
        reverseHolofoil.setMid(mid);
        reverseHolofoil.setHigh(high);
        reverseHolofoil.setMarket(market);
        reverseHolofoil.setDirectLow(directLow);

        check(reverseHolofoil);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        ReverseHolofoil reverseHolofoil = ReverseHolofoil.builder()
                .low(low).mid(mid).high(high)
                .market(market).directLow(directLow)
                .build();
        check(reverseHolofoil);
    }

    private void check(ReverseHolofoil item){
        assertNotNull(item, createMsg("ReverseHolofoil"));
        assertEquals(low, item.getLow(), createMsg("low"));
        assertEquals(mid, item.getMid(), createMsg("mid"));
        assertEquals(high, item.getHigh(), createMsg("high"));
        assertEquals(market, item.getMarket(), createMsg("market"));
        assertEquals(directLow, item.getDirectLow(), createMsg("directLow"));
    }
}