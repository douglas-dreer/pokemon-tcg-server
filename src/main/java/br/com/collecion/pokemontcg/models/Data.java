package br.com.collecion.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonRootName(value = "data")
public class Data {
    private Card data;
}
