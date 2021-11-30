package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Holofoil {
    private double low;
    private double mid;
    private double high;
    private double market;
    private double directLow;
}
