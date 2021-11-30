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
    public String id;
    public String name;
    public String supertype;
    public List<String> subtypes;
    public String level;
    public String hp;
    public List<String> types;
    public String evolvesFrom;
    public List<Ability> abilities;
    public List<Attack> attacks;
    public List<Weakness> weaknesses;
    public List<String> retreatCost;
    public int convertedRetreatCost;
    public Set set;
    public String number;
    public String artist;
    public String rarity;
    public String flavorText;
    public List<Integer> nationalPokedexNumbers;
    public Legalities legalities;
    public Images images;
    public Tcgplayer tcgplayer;
    public List<String> evolvesTo;
    public List<Resistance> resistances;
}

