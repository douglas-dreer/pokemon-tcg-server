package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReverseHolofoil {
    private int low;
    private double mid;
    private int high;
    private double market;
    private double directLow;
}
