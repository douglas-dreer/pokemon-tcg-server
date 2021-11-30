package br.com.collecion.pokemontcg.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AbilityTest implements Models{
    private static final String name = "name";
    private static final String text = "text";
    private static final String type = "type";

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Ability ability = new Ability(name, text, type);
        check(ability);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Ability ability = new Ability();
        ability.setName(name);
        ability.setText(text);
        ability.setType(type);
        check(ability);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Ability ability = Ability.builder()
                .name(name)
                .type(type)
                .text(text)
        .build();
        check(ability);
    }

    private void check(Ability item){
        Assertions.assertNotNull(item, createMsg("Ability"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(type, item.getType(), createMsg("type"));
        assertEquals(text, item.getText(), createMsg("text"));
    }
}
