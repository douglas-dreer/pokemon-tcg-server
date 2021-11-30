package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Images {
    private String symbol;
    private String logo;
    private String small;
    private String large;
}
