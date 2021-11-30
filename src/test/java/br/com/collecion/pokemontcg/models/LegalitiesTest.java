package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LegalitiesTest implements Models {

    private final static String unlimited = "unlimited";
    private final static String standard = "standard";
    private final static String expanded = "expanded";

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Legalities legalities = new Legalities(unlimited, standard, expanded);
        check(legalities);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Legalities weakness = new Legalities();
        weakness.setUnlimited(unlimited);
        weakness.setStandard(standard);
        weakness.setExpanded(expanded);
        check(weakness);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Legalities weakness = Legalities.builder()
                .unlimited(unlimited)
                .standard(standard)
                .expanded(expanded)
                .build();
        check(weakness);
    }

    private void check(Legalities item){
        assertNotNull(item, createMsg("Legalities"));
        assertEquals(unlimited, item.getUnlimited(), createMsg("unlimited"));
        assertEquals(expanded, item.getExpanded(), createMsg("standard"));
        assertEquals(standard, item.getStandard(), createMsg("expanded"));
    }
}