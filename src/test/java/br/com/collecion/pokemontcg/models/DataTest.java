package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest implements Models{
    private static final Card card = new Card();

    @BeforeEach
    public void setup() {
        card.setId("1");
    }

    @Override
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Data data = new Data(card);
        check(data);
    }

    @Override
    public void mustReturnSuccess_WhenCreateBySetters() {
        Data data = new Data();
        data.setData(card);
        check(data);
    }

    @Override
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Data data = Data.builder()
                .data(card)
                .build();
        check(data);
    }

    private void check(Data item){
        assertNotNull(item, createMsg("Data"));
        assertEquals(card, item.getData(), createMsg("card"));
        assertNotNull(item.getData());
    }
}
