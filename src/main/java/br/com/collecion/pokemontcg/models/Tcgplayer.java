package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tcgplayer {
    private String url;
    private String updatedAt;
    private Prices prices;
}
