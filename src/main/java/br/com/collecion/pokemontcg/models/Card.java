package br.com.collecion.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "data")

public class Card {
    private String id;
    private String name;
    private String supertype;
    private List<String> subtypes;
    private String level;
    private String hp;
    private List<String> types;
    private String evolvesFrom;
    private List<Ability> abilities;
    private List<Attack> attacks;
    private List<Weakness> weaknesses;
    private List<String> retreatCost;
    private int convertedRetreatCost;
    private Set set;
    private String number;
    private String artist;
    private String rarity;
    private String flavorText;
    private List<Integer> nationalPokedexNumbers;
    private Legalities legalities;
    private Images images;
    private Tcgplayer tcgplayer;
    private List<String> evolvesTo;
    private List<Resistance> resistances;
}

