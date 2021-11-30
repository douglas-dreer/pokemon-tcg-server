package br.com.collecion.pokemontcg.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AbilityTest implements Models{
    private Card card = new Card();

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Data data = new Data(card);
        check(data);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Data data = new Data();
        data.setData(card);
        check(data);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Data data = Data.builder().data(card).build();
        check(data);
    }

    private void check(Data item){
        Assertions.assertNotNull(item, createMsg("Card"));
        Assertions.assertEquals(card, item.getData(), createMsg("card"));
    }
}
