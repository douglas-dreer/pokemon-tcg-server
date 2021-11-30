package br.com.collecion.pokemontcg.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resistance {
    private String type;
    private String value;
}