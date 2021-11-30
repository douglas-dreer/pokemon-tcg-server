package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Legalities{
    public String unlimited;
    public String standard;
    public String expanded;
}
