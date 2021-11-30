package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeaknessTest implements Models {

    private static final String type = "fire";
    private static final String value = "1.0";


    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Weakness weakness = new Weakness(type, value);
        check(weakness);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Weakness weakness = new Weakness();
        weakness.setType(type);
        weakness.setValue(value);
        check(weakness);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Weakness weakness = Weakness.builder()
                .type(type)
                .value(value)
                .build();
        check(weakness);

    }
    private void check(Weakness item){
        assertNotNull(item, createMsg("Weakness"));
        assertEquals(type, item.getType(), createMsg("fire"));
        assertEquals(value, item.getValue(), createMsg("1.0"));
    }
}