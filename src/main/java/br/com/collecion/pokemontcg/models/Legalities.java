package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Legalities{
    private String unlimited;
    private String standard;
    private String expanded;
}
