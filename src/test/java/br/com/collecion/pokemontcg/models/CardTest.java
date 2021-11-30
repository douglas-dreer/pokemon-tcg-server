package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardTest implements Models {
    private static final String id = "id";
    private static final String name = "name";
    private static final String supertype = "supertype";
    private static final List<String> subtypes = new ArrayList<>();
    private static final String level = "level";
    private static final String hp = "hp";
    private static final List<String> types = new ArrayList<>();
    private static final String evolvesFrom = "evolvesFrom" ;
    private static final List<Ability> abilities = new ArrayList<>();
    private static final List<Attack> attacks = new ArrayList<>();
    private static final List<Weakness> weaknesses = new ArrayList<>();
    private static final List<String> retreatCost = new ArrayList<>();
    private static final int convertedRetreatCost = 10;
    private static final Set set = new Set("id", "name", "series", 1, 10, new Legalities("unlimited", "standard", "expanded"), "ptcgoCode", "releaseDate", "updatedAt", new Images());
    private static final String number = "number";
    private static final String artist = "artist";
    private static final String rarity = "rarity";
    private static final String flavorText = "flavorText";
    private static final List<Integer> nationalPokedexNumbers = new ArrayList<>();
    private static final Legalities legalities = new Legalities("unlimited", "standard", "expanded");
    private static final Images images = new Images("symbol", "logo", "large", "small");
    private static final Tcgplayer tcgplayer = new Tcgplayer("url", "updateAt", new Prices());
    private static final List<String> evolvesTo = new ArrayList<>();
    private static final List<Resistance> resistances = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Ability ability = new Ability("name", "text", "value");
        Attack attack = new Attack("name", Collections.singletonList("cost"), 1, "damage", "text");
        Weakness weakness = new Weakness("type", "value");
        Resistance resistance = new Resistance("type", "value");

        subtypes.add("subtype");
        types.add("type");
        abilities.add(ability);
        attacks.add(attack);
        weaknesses.add(weakness);
        retreatCost.add("retreatCost");
        nationalPokedexNumbers.add(151);
        evolvesTo.add("evolvesTo");
        resistances.add(resistance);
    }
    
    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {       
        Card card = new Card(
                id, name, supertype, subtypes,
                level, hp, types, evolvesFrom,
                abilities, attacks, weaknesses,
                retreatCost, convertedRetreatCost,
                set, number, artist, rarity, flavorText,
                nationalPokedexNumbers, legalities, images,
                tcgplayer, evolvesTo, resistances
        );
        check(card);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Card card = new Card();
        card.setId(id);
        card.setName(name);
        card.setSupertype(supertype);
        card.setSubtypes(subtypes);
        card.setLevel(level);
        card.setHp(hp);
        card.setTypes(types);
        card.setEvolvesFrom(evolvesFrom);
        card.setAbilities(abilities);
        card.setAttacks(attacks);
        card.setWeaknesses(weaknesses);
        card.setRetreatCost(retreatCost);
        card.setConvertedRetreatCost(convertedRetreatCost);
        card.setSet(set);
        card.setNumber(number);
        card.setArtist(artist);
        card.setRarity(rarity);
        card.setFlavorText(flavorText);
        card.setNationalPokedexNumbers(nationalPokedexNumbers);
        card.setLegalities(legalities);
        card.setImages(images);
        card.setTcgplayer(tcgplayer);
        card.setEvolvesTo(evolvesTo);
        card.setResistances(resistances);

        check(card);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Card card = Card.builder()
                .id(id).name(name).supertype(supertype).subtypes(subtypes)
                .level(level).hp(hp).types(types).evolvesFrom(evolvesFrom)
                .abilities(abilities).attacks(attacks).weaknesses(weaknesses)
                .retreatCost(retreatCost).convertedRetreatCost(convertedRetreatCost)
                .set(set).number(number).artist(artist).rarity(rarity).flavorText(flavorText)
                .nationalPokedexNumbers(nationalPokedexNumbers).legalities(legalities).images(images)
                .tcgplayer(tcgplayer).evolvesTo(evolvesTo).resistances(resistances)
            .build();
        check(card);
    }

    private void check(Card item) {
        assertNotNull(item, createMsg("Card"));

        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(supertype, item.getSupertype(), createMsg("supertype"));

        assertFalse(item.getSubtypes().isEmpty(), createMsg("subtypes"));
        assertEquals(subtypes, item.getSubtypes(), createMsg("subtypes"));

        assertEquals(level, item.getLevel(), createMsg("level"));
        assertEquals(hp, item.getHp(), createMsg("hp"));

        assertFalse(item.getTypes().isEmpty(), createMsg("types"));
        assertEquals(types, item.getTypes(), createMsg("types"));

        assertEquals(evolvesFrom, item.getEvolvesFrom(), createMsg("evolvesFrom"));

        assertFalse(item.getAbilities().isEmpty(), createMsg("abilities"));
        assertEquals(abilities, item.getAbilities(), createMsg("abilities"));

        assertFalse(item.getAttacks().isEmpty(), createMsg("attacks"));
        assertEquals(attacks, item.getAttacks(), createMsg("attacks"));

        assertFalse(item.getWeaknesses().isEmpty(), createMsg("weaknesses"));
        assertEquals(weaknesses, item.getWeaknesses(), createMsg("weaknesses"));

        assertFalse(item.getRetreatCost().isEmpty(), createMsg("retreatCost"));
        assertEquals(retreatCost, item.getRetreatCost(), createMsg("retreatCost"));

        assertEquals(convertedRetreatCost, item.getConvertedRetreatCost(), createMsg("convertedRetreatCost"));

        assertNotNull(item.getSet(), createMsg("set"));
        assertEquals(set, item.getSet(), createMsg("set"));

        assertEquals(number, item.getNumber(), createMsg("number"));
        assertEquals(artist, item.getArtist(), createMsg("artist"));
        assertEquals(rarity, item.getRarity(), createMsg("rarity"));
        assertEquals(flavorText, item.getFlavorText(), createMsg("flavorText"));

        assertFalse(item.getNationalPokedexNumbers().isEmpty(), createMsg("nationalPokedexNumbers"));
        assertEquals(nationalPokedexNumbers, item.getNationalPokedexNumbers(), createMsg("nationalPokedexNumbers"));

        assertNotNull(item.getLegalities(), createMsg("legalities"));
        assertEquals(legalities, item.getLegalities(), createMsg("legalities"));

        assertNotNull(item.getImages(), createMsg("images"));
        assertEquals(images, item.getImages(), createMsg("images"));

        assertNotNull(item.getTcgplayer(), createMsg("tcgplayer"));
        assertEquals(tcgplayer, item.getTcgplayer(), createMsg("tcgplayer"));

        assertFalse(item.getEvolvesTo().isEmpty(), createMsg("evolvesTo"));
        assertEquals(evolvesTo, item.getEvolvesTo(), createMsg("evolvesTo"));

        assertFalse(item.getResistances().isEmpty(), createMsg("resistances"));
        assertEquals(resistances, item.getResistances(), createMsg("resistances"));
    }
}
