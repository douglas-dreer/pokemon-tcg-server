package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResistanceTest implements Models{
    private static final String type = "type";
    private static final String value = "value";

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Resistance resistencia = new Resistance(type, value);
        check(resistencia);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Resistance resistencia = new Resistance();
        resistencia.setType(type);
        resistencia.setValue(value);

        check(resistencia);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Resistance resistencia = Resistance.builder()
                .type(type)
                .value(value)
                .build();
        check(resistencia);
    }

    private void check(Resistance item){
        Assertions.assertNotNull(item, createMsg("Resistance"));
        assertEquals(type, item.getType(), createMsg("type"));
        assertEquals(value, item.getValue(), createMsg("value"));
    }
}
