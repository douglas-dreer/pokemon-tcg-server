package br.com.collecion.pokemontcg.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resistance {
    public String type;
    public String value;
}