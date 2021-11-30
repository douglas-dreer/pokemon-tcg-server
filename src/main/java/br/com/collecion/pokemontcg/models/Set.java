package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Set {
    public String id;
    private String name;
    private String series;
    private int printedTotal;
    private int total;
    private Legalities legalities;
    private String ptcgoCode;
    private String releaseDate;
    private String updatedAt;
    private Images images;
}
