package br.com.collecion.pokemontcg.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cards {
    private List<Card> data;
    private int page;
    private int pageSize;
    private int count;
    private int totalCount;
}
