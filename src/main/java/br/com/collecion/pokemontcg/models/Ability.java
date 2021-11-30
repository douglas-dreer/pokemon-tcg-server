package br.com.collecion.pokemontcg.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    private String name;
    private String text;
    private String type;
}