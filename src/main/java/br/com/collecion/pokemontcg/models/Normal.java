package br.com.collecion.pokemontcg.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Normal {
    private double low;
    private double mid;
    private double high;
    private double market;
    private double directLow;
}
