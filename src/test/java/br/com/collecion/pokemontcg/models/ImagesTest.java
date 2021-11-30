package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ImagesTest implements Models {

    private final static String symbol = "symbol";
    private final static String logo = "logo";
    private final static String small = "small";
    private final static String large = "large";

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Images images = new Images(symbol, logo, small, large);
        check(images);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Images images = new Images();
        images.setSymbol(symbol);
        images.setLogo(logo);
        images.setSmall(small);
        images.setLarge(large);
        check(images);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Images images = Images.builder()
                .symbol(symbol)
                .logo(logo)
                .small(small)
                .large(large)
                .build();
        check(images);
    }

    private void check(Images item){
        assertNotNull(item, createMsg("Legalities"));
        assertEquals(symbol, item.getSymbol(), createMsg("symbol"));
        assertEquals(logo, item.getLogo(), createMsg("logo"));
        assertEquals(small, item.getSmall(), createMsg("small"));
        assertEquals(large, item.getLarge(), createMsg("large"));
    }
}