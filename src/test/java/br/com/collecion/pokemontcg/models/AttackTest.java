package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AttackTest implements Models{
    private static final String name = "name";
    private static final List<String> cost = new ArrayList<>();
    private static final int convertedEnergyCost = 1;
    private static final String damage = "type";
    private static final String text = "text";

    @BeforeEach
    public void setup(){
        cost.add("1");
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Attack attack = new Attack(name, cost, convertedEnergyCost, damage, text);
        check(attack);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Attack attack = new Attack();
        attack.setName(name);
        attack.setCost(cost);
        attack.setConvertedEnergyCost(convertedEnergyCost);
        attack.setDamage(damage);
        attack.setText(text);
        check(attack);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Attack attack = Attack.builder()
                .name(name)
                .cost(cost)
                .convertedEnergyCost(convertedEnergyCost)
                .damage(damage)
                .text(text)
                .build();
        check(attack);
    }

    private void check(Attack item){
        assertNotNull(item, createMsg("Attack"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(cost, item.getCost(), createMsg("cost"));
        assertEquals(text, item.getText(), createMsg("text"));
        assertEquals(convertedEnergyCost, item.getConvertedEnergyCost(), createMsg("convertedEnergyCost"));
        assertFalse(item.getCost().isEmpty());
        assertEquals(damage, item.getDamage(), createMsg("damage"));
    }
}

