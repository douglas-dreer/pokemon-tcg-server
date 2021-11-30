package br.com.collecion.pokemontcg.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Attack {
    private String name;
    private List<String> cost;
    private int convertedEnergyCost;
    private String damage;
    private String text;
}
