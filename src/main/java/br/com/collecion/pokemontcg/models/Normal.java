package br.com.collecion.pokemontcg.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Normal {
    public double low;
    public double mid;
    public double high;
    public double market;
    public double directLow;
}
